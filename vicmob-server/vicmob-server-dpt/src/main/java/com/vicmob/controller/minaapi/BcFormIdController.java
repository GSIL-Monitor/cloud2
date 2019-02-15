package com.vicmob.controller.minaapi;

import beans.Result;
import beans.ResultCode;
import com.vicmob.entity.BcMinaForm;
import com.vicmob.service.mina.BcMinaFormService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 小程序formId收集业务控制层
 * @author ricky
 * @date 2019-02-13
 */
@Api(value = "小程序formId收集业务控制层",description = "小程序formId收集业务控制层")
@RestController
@RequestMapping(value = "mina/formId")
public class BcFormIdController {
    @Resource
    private BcMinaFormService bcMinaFormService;

    @ApiOperation("保存formId")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "formId",value = "formId",dataType = "Integer",required = true),
            @ApiImplicitParam(name = "customerId", value = "客户id", dataType = "Integer",required = true)
    })
    @PostMapping(value = "selectEmpInfo")
    public Result saveFormId (BcMinaForm minaForm) {
        // 初始化接口状态
        ResultCode state = ResultCode.SUCCESS;
        try {
            // 保存
            bcMinaFormService.add(minaForm);
        } catch (Exception e) {
            state = ResultCode.ERROR;
        }
        if(state.code().equals(ResultCode.SUCCESS.code()) ) {
            return Result.success();
        }else{
            return Result.failure(state);
        }
    }
}
