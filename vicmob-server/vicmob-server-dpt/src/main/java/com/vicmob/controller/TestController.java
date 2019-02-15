package com.vicmob.controller;

import beans.Result;
import beans.ResultCode;
import com.vicmob.entity.MinaThird;
import com.vicmob.service.minathird.MinaThirdService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Api(value = "swagger示例",description = "swagger示例")
@RestController
@RequestMapping(value = "test")
public class TestController {

    @Resource
    private MinaThirdService minaThirdService;

    @ApiOperation(value = "获取信息")
    @ApiImplicitParam(name = "msg", value = "相关信息", dataType = "string",required = true)
    @GetMapping(value = "getMessage")
    public Result getMessage(String msg) {
        String message = msg + "this is message";
        return Result.success(message);
    }

    @GetMapping(value = "getMinaThird")
    public Result getMinaThird() {
        MinaThird minaThird = minaThirdService.getByAppId("wxe1c1eb55a0642467");
        return Result.success(minaThird);
    }

    @GetMapping(value = "testTimeOut")
    public Result testTimeOut(long time) {
        Result result = Result.success();
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            result =Result.failure(ResultCode.ERROR);
        }
        return result;
    }
}
