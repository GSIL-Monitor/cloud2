package com.vicmob.controller.minaapi;

import beans.Result;
import beans.ResultCode;
import com.vicmob.entity.BcAutomaticRecovery;
import com.vicmob.service.minaapi.BcAutomaticRecoveryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import utils.DecryptUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * 自动回复相关业务控制层
 * @author ricky
 * @date 2019-02-13
 */
@Api(value = "自动回复相关业务控制层",description = "自动回复相关业务控制层")
@RestController
@RequestMapping(value = "mina/automaticRecovery")
public class BcAutomaticRecoveryMinaController {

    @Resource
    private BcAutomaticRecoveryService bcAutomaticRecoveryService;

    @ApiOperation(value = "获取自动回复列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "minaStr", value = "小程序id", dataType = "string",required = true),
            @ApiImplicitParam(name = "appId", value = "appId", dataType = "string",required = true)
    })
    @PostMapping(value = "/getAutomaticRecovery")
    public Result getAutomaticRecovery(HttpServletRequest request) {
        ResultCode state = ResultCode.ERROR;
        //获取minaId
        String minaStr = request.getParameter("minaStr");
        String appId = request.getParameter("appId");
        Integer minaId = Integer.valueOf(DecryptUtils.getMinaId(minaStr,appId));
        List<BcAutomaticRecovery> bcAutomaticRecoveryList = new ArrayList<>();
        try {
            if(minaId != null) {
                bcAutomaticRecoveryList = bcAutomaticRecoveryService.getByMinaId(minaId);
                state = ResultCode.SUCCESS;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(state.code().equals(ResultCode.SUCCESS.code()) ) {
            return Result.success(bcAutomaticRecoveryList);
        }else{
            return Result.failure(state);
        }
    }

    @ApiOperation(value = "根据自动回复主键id获取答案")
    @ApiImplicitParam(name = "replyId", value = "主键id", dataType = "string",required = true)
    @PostMapping(value = "/getRecovery")
    public Result getRecovery(HttpServletRequest request) {
        ResultCode state = ResultCode.ERROR;
        //获取自动回复主键id
        String replyId = request.getParameter("replyId");
        String msg = "";
        BcAutomaticRecovery bcAutomaticRecovery = new BcAutomaticRecovery();
        try {
            if(replyId != null && replyId != "") {
                bcAutomaticRecovery = bcAutomaticRecoveryService.get(Integer.valueOf(replyId));
                if(bcAutomaticRecovery != null) {
                    state = ResultCode.SUCCESS;
                }
            }else {
                state = ResultCode.PARAM_IS_BLANK;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(state.code().equals(ResultCode.SUCCESS.code()) ) {
            return Result.success(bcAutomaticRecovery.getAnswer());
        }else{
            return Result.failure(state);
        }
    }

}
