package com.vicmob.controller.mina;

import beans.Result;
import beans.ResultCode;
import com.vicmob.entity.BcMinaInfo;
import com.vicmob.entity.BcMinaPublish;
import com.vicmob.service.mina.BcMinaInfoService;
import com.vicmob.service.web.BcMinaPublishService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * 小程序一键发布业务控制类
 * @author ziv
 * @date 2019-02-12
 */
@Slf4j
@Api(description = "一键发布业务接口")
@RestController
@RequestMapping(value = "publish")
public class BcMinaPublishController {

    @Resource
    private BcMinaInfoService minaInfoService;

    @Resource
    private BcMinaPublishService minaPublishService;

    /**
     * 用户小程序已授权
     */
    private static final int AUTHOR = 1;

    @PutMapping(value = "prePublish")
    public Result prePublish() {
        log.info("预发布开始");
        // 初始化接口状态
        ResultCode state = ResultCode.SUCCESS;
        // 初始化返回信息
        Map<String, Object> returnVal = new HashMap<>(2);

        // TODO: 获取用户信息，暂时写用户主键
        int userId = 9550;
        String userName = "shangdian";
        // 获取用户小程序信息
        BcMinaInfo minaInfo = minaInfoService.getByUserId(userId);

        // 判断用户是否授权
        if (!StringUtils.isEmpty(minaInfo) && minaInfo.getAuthorState().intValue() == AUTHOR) {
            // 用户授权，提交小程序代码
            boolean flag = minaInfoService.submitMinaCode(minaInfo.getMinaId().intValue(), userName);
            if (flag) {
                // 提交成功，查询是否已经提交过审核
                BcMinaPublish minaPublish = minaPublishService.findByMinaId(minaInfo.getMinaId().intValue());
                // 校验是否发布过
                if (StringUtils.isEmpty(minaPublish)) {
                    // 未发布
                    returnVal.put("havePublish", false);
                } else {
                    // 已发布
                    returnVal.put("havePublish", true);
                }
            } else {
                state = ResultCode.INTERFACE_OUTTER_INVOKE_ERROR;
            }
        } else {
            // 用户未授权
            state = ResultCode.USER_NOT_AUTHOR_MINA;
        }

        log.info("预发布结束");
        // 返回接口信息
        if (ResultCode.SUCCESS.code().equals(state.code())) {
            return Result.success(returnVal);
        } else {
            return Result.failure(state, returnVal);
        }
    }
}
