package com.vicmob.controller.minaapi;

import beans.Result;
import beans.ResultCode;
import com.vicmob.entity.BcDecoration;
import com.vicmob.service.minaapi.BcDecorationService;
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

/**
 * 官网相关业务控制层
 * @author ricky
 * @date 2019-02-13
 */
@Api(value = "官网相关业务控制层",description = "官网相关业务控制层")
@RestController
@RequestMapping(value = "mina/BcDecoration")
public class BcDecoratioController {
    @Resource
    private BcDecorationService bcDecorationService;

    @ApiOperation(value = "通过minaId查询官网信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "minaStr", value = "小程序id", dataType = "string",required = true),
            @ApiImplicitParam(name = "appId", value = "appId", dataType = "string",required = true)
    })
    @PostMapping(value = "/selectByMinaId")
    public Result selectByMinaId(HttpServletRequest request) {
        //获取minaId
        String minaStr = request.getParameter("minaStr");
        String appId = request.getParameter("appId");
        Integer minaId = Integer.valueOf(DecryptUtils.getMinaId(minaStr,appId));
        ResultCode state = ResultCode.ERROR;
        BcDecoration bcDecoration = null;
        try {
            bcDecoration=bcDecorationService.selectByMinaId(minaId);
            if(bcDecoration!=null) {
                state = ResultCode.SUCCESS;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(state.code().equals(ResultCode.SUCCESS.code()) ) {
            return Result.success(bcDecoration);
        }else{
            return Result.failure(state);
        }
    }
}
