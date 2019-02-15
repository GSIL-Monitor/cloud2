package com.vicmob.controller.minaapi;

import beans.Result;
import beans.ResultCode;
import com.vicmob.entity.BcInformation;
import com.vicmob.service.minaapi.BcInformationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import utils.DateUtils;
import utils.DecryptUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 企业资讯相关业务控制层
 * @author ricky
 * @date 2019-02-13
 */
@Api(value = "企业资讯相关业务控制层",description = "企业资讯相关业务控制层")
@RestController
@RequestMapping(value = "mina/BcInformation")
public class BcInformatioController {

    @Resource
    private BcInformationService bcInformationService;

    @ApiOperation("查询企业资讯信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "minaStr", value = "小程序id", dataType = "string",required = true),
            @ApiImplicitParam(name = "appId", value = "appId", dataType = "string",required = true)
    })
    @PostMapping(value = "selectInfo")
    public Result selectInfo(HttpServletRequest request) {
        String minaStr = request.getParameter("minaStr");
        String appId = request.getParameter("appId");
        int minaId = Integer.valueOf(DecryptUtils.getMinaId(minaStr,appId));
        ResultCode state = ResultCode.ERROR;
        List<BcInformation> informations = null;
        try {
            informations = bcInformationService.selectByMinaId(minaId);
            if(informations.size()!=0) {
                state=ResultCode.SUCCESS;
                for(BcInformation bcInformation:informations) {
                    bcInformation.setInfoAddDate(DateUtils.formatDateTime(bcInformation.getInfoAddTime()));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(state.code().equals(ResultCode.SUCCESS.code()) ) {
            return Result.success(informations);
        }else{
            return Result.failure(state);
        }
    }
}
