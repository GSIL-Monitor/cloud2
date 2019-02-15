package com.vicmob.controller.minaapi;

import beans.Result;
import beans.ResultCode;
import com.vicmob.entity.BcEmployeeImpressionlabel;
import com.vicmob.service.minaapi.BcEmployeeImpressionlabelService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 员工印象标签相关业务控制层
 * @author ricky
 * @date 2019-01-31
 */
@Api(value = "员工印象标签相关业务控制层",description = "员工印象标签相关业务控制层")
@RestController
@RequestMapping(value = "mina/BcEmployeeImpressionlabel")
public class BcEmployeeImpressionlabelController {
    @Resource
    private BcEmployeeImpressionlabelService bcEmployeeImpressionlabelService;

    @ApiOperation(value = "根据员工Id查询印象标签")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "customerId", value = "客户id", dataType = "Integer",required = true),
            @ApiImplicitParam(name = "employeeId", value = "员工id", dataType = "Integer",required = true)
    })
    @PostMapping(value = "/selectByEmployeeId")
    public Result selectByEmployeeId(Integer employeeId, Integer customerId) {
        ResultCode state = ResultCode.ERROR;
        List<BcEmployeeImpressionlabel> bcEmployeeImpressionlabels=null;
        try {
            bcEmployeeImpressionlabels=bcEmployeeImpressionlabelService.selectByEmployeeIdForMina(customerId,employeeId);
            if(bcEmployeeImpressionlabelService!=null) {
                state = ResultCode.SUCCESS;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(state.code().equals(ResultCode.SUCCESS.code()) ) {
            return Result.success(bcEmployeeImpressionlabels);
        }else{
            return Result.failure(state);
        }
    }
}
