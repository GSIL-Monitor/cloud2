package com.vicmob.controller.minaapi;

import beans.Result;
import beans.ResultCode;
import beans.StateCode;
import com.vicmob.entity.BcCustomer;
import com.vicmob.entity.BcCustomerEmployee;
import com.vicmob.service.minaapi.BcCustomerEmployeeService;
import com.vicmob.service.minaapi.BcCustomerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;

/**
 * 员工客户相关业务控制层
 * @author ricky
 * @date 2019-02-13
 */
@Api(value = "员工客户相关业务控制层",description = "员工客户相关业务控制层")
@RestController
@RequestMapping(value = "mina/BcCustomerEmployee")
public class BcCustomerEmployeeController {

    @Resource
    private BcCustomerEmployeeService bcCustomerEmployeeService;

    @Resource
    private BcCustomerService bcCustomerService;



    @ApiOperation(value = "客户对名片进行屏蔽和解除屏蔽操作")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "customerId", value = "客户id", dataType = "Integer",required = true),
            @ApiImplicitParam(name = "employeeId", value = "员工Id", dataType = "Integer",required = true),
            @ApiImplicitParam(name = "custIsShield", value = "操作类型", dataType = "Integer",required = true)
    })
    @PostMapping(value = "/shiledOperate")
    public Result shiledOperate(Integer customerId, Integer employeeId, Integer custIsShield) {
        BcCustomerEmployee bcCustomerEmployee=new BcCustomerEmployee();
        if(customerId != null) {
            bcCustomerEmployee.setCustomerId(customerId);
        }
        if(employeeId != null) {
            bcCustomerEmployee.setEmployeeId(employeeId);
        }
        if(custIsShield != null) {
            bcCustomerEmployee.setCustIsShield(custIsShield);
        }
        ResultCode state = ResultCode.ERROR;
        try {
            int result=bcCustomerEmployeeService.updateCustIsShield(bcCustomerEmployee);
            if(result>0) {
                state=ResultCode.SUCCESS;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(state.code().equals(ResultCode.SUCCESS.code()) ) {
            return Result.success();
        }else{
            return Result.failure(state);
        }
    }

    @ApiOperation(value = "客户对当前员工是否点过赞")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "customerId", value = "客户id", dataType = "Integer",required = true),
            @ApiImplicitParam(name = "employeeId", value = "员工Id", dataType = "Integer",required = true)
    })
    @PostMapping(value = "/praiseStatus")
    public Result praiseStatus(Integer customerId,Integer employeeId) {
        int status= StateCode.PraiseStatusEnum.NOT_PRAISE.getCode();
        try {
            BcCustomerEmployee bcCustomerEmployee=bcCustomerEmployeeService.selectByEmpIdAndCusId(employeeId, customerId);
            if(bcCustomerEmployee!=null&&bcCustomerEmployee.getCustIsLike()==StateCode.PraiseStatusEnum.PRAISE.getCode()) {
                status=StateCode.PraiseStatusEnum.PRAISE.getCode();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(status==StateCode.PraiseStatusEnum.NOT_PRAISE.getCode()) {
            return Result.createBySuccessCodeMessage(status, "未点赞");
        }else {
            return Result.createBySuccessCodeMessage(status, "已经点赞");
        }
    }

    @ApiOperation(value = "插入员工客户关系")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "customerId", value = "客户id", dataType = "Integer",required = true),
            @ApiImplicitParam(name = "employeeId", value = "员工Id", dataType = "Integer",required = true)
    })
    @PostMapping(value = "/insertRelationship")
    public Result insertRelationship(Integer customerId,Integer employeeId) {
        ResultCode state = ResultCode.ERROR;
        try {
            BcCustomerEmployee bcCustomerEmployee = bcCustomerEmployeeService.selectByEmpIdAndCusId(employeeId, customerId);
            BcCustomer bcCustomer = bcCustomerService.selectByPrimaryKey(customerId);
            if(bcCustomerEmployee == null) {
                bcCustomerEmployee=new BcCustomerEmployee();
                bcCustomerEmployee.setEmployeeId(employeeId);
                bcCustomerEmployee.setCustomerId(customerId);
                bcCustomerEmployee.setAddTime(new Date());
                bcCustomerEmployee.setActivityTime(new Date());
                if(bcCustomer != null) {
                    //性别（1：男；2：女）
                    if(bcCustomer.getSex() == 0) {
                        bcCustomerEmployee.setGender(1);
                    }else {
                        bcCustomerEmployee.setGender(bcCustomer.getSex());
                    }
                }
                int result = bcCustomerEmployeeService.insertSelective(bcCustomerEmployee);
                if(result > 0) {
                    state=ResultCode.SUCCESS;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(state.code().equals(ResultCode.SUCCESS.code()) ) {
            return Result.success();
        }else{
            return Result.failure(state);
        }
    }

    @ApiOperation(value = "update客户授权手机号")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "customerId", value = "客户id", dataType = "Integer",required = true),
            @ApiImplicitParam(name = "employeeId", value = "员工Id", dataType = "Integer",required = true)
    })
    @PostMapping(value = "/updateAuthorizationPhone")
    public Result updateAuthorizationPhone(Integer customerId,Integer employeeId) {
        ResultCode state = ResultCode.ERROR;
        try {
            //根据客户id获取手机号
            BcCustomer bcCustomer = bcCustomerService.selectByPrimaryKey(customerId);
            if(bcCustomer != null && bcCustomer.getPhone() != null) {
                BcCustomerEmployee bcCustomerEmployee = new BcCustomerEmployee();
                bcCustomerEmployee.setEmployeeId(employeeId);
                bcCustomerEmployee.setCustomerId(customerId);
                bcCustomerEmployee.setAuthorizationPhone(bcCustomer.getPhone());
                int result = bcCustomerEmployeeService.updateByEmpAndCust(bcCustomerEmployee);
                if(result > 0) {
                    state=ResultCode.SUCCESS;
                }
            }else {
                //数据未找到
                state = ResultCode.RESULE_DATA_NONE;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(state.code().equals(ResultCode.SUCCESS.code()) ) {
            return Result.success();
        }else{
            return Result.failure(state);
        }
    }

    @ApiOperation(value = "get是否授权")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "customerId", value = "客户id", dataType = "Integer",required = true),
            @ApiImplicitParam(name = "employeeId", value = "员工Id", dataType = "Integer",required = true)
    })
    @PostMapping(value = "/getAuthorizedTelephone")
    public Result getAuthorizedTelephone(Integer customerId,Integer employeeId) {
        ResultCode state = ResultCode.ERROR;
        try {
            BcCustomerEmployee bcCustomerEmployee = bcCustomerEmployeeService.selectByEmpIdAndCusId(employeeId,customerId);
            if(bcCustomerEmployee != null && bcCustomerEmployee.getAuthorizationPhone() != null && bcCustomerEmployee.getAuthorizationPhone() != "") {
                state=ResultCode.SUCCESS;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(state.code().equals(ResultCode.SUCCESS.code()) ) {
            return Result.success();
        }else{
            return Result.failure(state);
        }
    }

    @ApiOperation(value = "get平台是否授权")
    @ApiImplicitParam(name = "customerId", value = "客户id", dataType = "Integer",required = true)
    @PostMapping(value = "/getPhone")
    public Result getPhone(Integer customerId) {
        ResultCode state = ResultCode.ERROR;
        try {
            BcCustomer bcCustomer = bcCustomerService.selectByPrimaryKey(customerId);
            if(bcCustomer != null && bcCustomer.getPhone() != null && bcCustomer.getPhone() != "") {
                state=ResultCode.SUCCESS;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(state.code().equals(ResultCode.SUCCESS.code()) ) {
            return Result.success();
        }else{
            return Result.failure(state);
        }
    }
}
