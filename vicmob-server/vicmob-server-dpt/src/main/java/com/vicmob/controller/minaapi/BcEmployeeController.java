package com.vicmob.controller.minaapi;

import beans.Result;
import beans.ResultCode;
import beans.StateCode;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.vicmob.entity.*;
import com.vicmob.service.minaapi.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import utils.DecryptUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * 员工相关业务控制层
 * @author ricky
 * @date 2019-01-30
 */
@Api(value = "员工相关业务控制层",description = "员工相关业务控制层")
@RestController
@RequestMapping(value = "mina/BcEmployee")
public class BcEmployeeController {

    @Value("${pictureUrl}")
    private String PICTUREURL_PATH;

    @Resource
    private BcEmployeeService bcEmployeeService;

    @Resource
    private BcEmployeeViewService bcEmployeeViewService;

    @Resource
    private BcCustomerEmployeeService bcCustomerEmployeeService;

    @Resource
    private BcImpressionlabelService bcImpressionlabelService;

    @Resource
    private BcEmployeeImpressionlabelService bcEmployeeImpressionlabelService;

    @ApiOperation(value = "通过客户Id查询该用户关注下所有员工名片列表信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "customerId", value = "客户id", dataType = "string",required = true),
            @ApiImplicitParam(name = "minaStr", value = "小程序id", dataType = "string",required = true),
            @ApiImplicitParam(name = "appId", value = "appId", dataType = "string",required = true),
            @ApiImplicitParam(name = "pageNo", value = "页数", dataType = "string",required = true),
            @ApiImplicitParam(name = "pageSize", value = "每页个数", dataType = "string",required = true)
    })
    @PostMapping(value = "/selectByCustomerId")
    public Result selectByCustomerId(HttpServletRequest request) {
        String customerId = request.getParameter("customerId");
        String minaStr = request.getParameter("minaStr");
        String appId = request.getParameter("appId");
        String pageNo = request.getParameter("pageNo");
        String pageSize = request.getParameter("pageSize");
        ResultCode state = ResultCode.ERROR;
        PageInfo<BcEmployee> employeePage = null;
        if (!StringUtils.isEmpty(minaStr) && !StringUtils.isEmpty(appId) && !StringUtils.isEmpty(pageNo)&& !StringUtils.isEmpty(pageSize)){
            int minaId = Integer.valueOf(DecryptUtils.getMinaId(minaStr,appId));
            BcEmployee bcEmployee = new BcEmployee();
            PageHelper.startPage(Integer.valueOf(pageNo),Integer.valueOf(pageSize));
            try {
                if (!StringUtils.isEmpty(customerId)) {
                    bcEmployee.setCustomerId(Integer.parseInt(customerId));
                    bcEmployee.setMinaId(minaId);
                    List<BcEmployee> employeeList = bcEmployeeService.selectByCustomerId(bcEmployee);
                    employeePage = new PageInfo<>(employeeList);
                    if (employeePage.getList().size() != 0) {
                        for (BcEmployee bEmployee : employeePage.getList()) {
                            bEmployee.setHeadPortrait(DecryptUtils.rebuildPic(bEmployee.getHeadPortrait(),PICTUREURL_PATH));
                        }
                    }
                    state = ResultCode.SUCCESS;
                }else {
                    state = ResultCode.PARAM_IS_BLANK;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{
            state = ResultCode.PARAM_IS_BLANK;
        }
        if(state.code().equals(ResultCode.SUCCESS.code()) ) {
            return Result.success(employeePage);
        }else{
            return Result.failure(state);
        }
    }

    @ApiOperation("根据员工id及客户id查询员工信息详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "employeeId",value = "员工id",dataType = "Integer",required = true),
            @ApiImplicitParam(name = "customerId", value = "客户id", dataType = "Integer",required = true)
    })
    @PostMapping(value = "selectEmpInfo")
    public Result selectEmpInfo(Integer employeeId, Integer customerId) {
        ResultCode state = ResultCode.ERROR;
        BcEmployee bcEmployee = null;
        if(!StringUtils.isEmpty(employeeId) && !StringUtils.isEmpty(customerId)){
            List<BcEmployeeView> bcEmployeeViews = bcEmployeeViewService.selectByEmpId(employeeId);
            bcEmployee = bcEmployeeService.selectByEmpAndCus(employeeId, customerId);
            if (bcEmployee != null) {
                if (bcEmployee.getHeadPortrait() != null) {
                    bcEmployee.setHeadPortrait(DecryptUtils.rebuildPic(bcEmployee.getHeadPortrait(),PICTUREURL_PATH));
                }
                if (bcEmployee.getEmpPhotos() != null) {
                    bcEmployee.setEmpPhotos(DecryptUtils.rebuildPic(bcEmployee.getEmpPhotos(),PICTUREURL_PATH));
                }
                if(bcEmployee.getLogoPic()!=null) {
                    bcEmployee.setLogoPic(DecryptUtils.rebuildPic(bcEmployee.getLogoPic(),PICTUREURL_PATH));
                }
                bcEmployee.setBcEmployeeViews(bcEmployeeViews);
                state = ResultCode.SUCCESS;
            }
        }else{
            state = ResultCode.PARAM_IS_BLANK;
        }
        if(state.code().equals(ResultCode.SUCCESS.code()) ) {
            return Result.success(bcEmployee);
        }else{
            return Result.failure(state);
        }
    }

    @ApiOperation("根据员工id及客户id更新员工浏览量")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "employeeId",value = "员工id",dataType = "Integer",required = true),
            @ApiImplicitParam(name = "customerId", value = "客户id", dataType = "Integer",required = true)
    })
    @PostMapping(value = "updateViews")
    public Result updateViews(Integer employeeId, Integer customerId) {
        ResultCode state = ResultCode.ERROR;
        BcEmployeeView bcEmployeeView = new BcEmployeeView();
        if (!StringUtils.isEmpty(employeeId) && !StringUtils.isEmpty(customerId)){
            bcEmployeeView.setEmployeeId(employeeId);
            bcEmployeeView.setCustomerId(customerId);
            bcEmployeeView.setViewDateTime(new Date());
            try {
                BcEmployeeView bcEmployeeViews = bcEmployeeViewService.selectByEmpAndCus(bcEmployeeView);
                // 表示之前浏览过
                if (bcEmployeeViews != null) {
                    int result = bcEmployeeViewService.updateByEmpAndCus(bcEmployeeView);
                } else {
                    int result = bcEmployeeViewService.insertSelective(bcEmployeeView);
                    if (result > 0) {
                        BcEmployee bcEmployee = bcEmployeeService.selectById(employeeId);
                        // 浏览数加1
                        bcEmployee.setViews(bcEmployee.getViews() + 1);
                        int res = bcEmployeeService.updateEmployeeInfo(bcEmployee);
                        if (res > 0) {
                            state = ResultCode.SUCCESS;
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{
            state = ResultCode.PARAM_IS_BLANK;
        }
        if(state.code().equals(ResultCode.SUCCESS.code()) ) {
            return Result.success(bcEmployeeView);
        }else{
            return Result.failure(state);
        }
    }

    @ApiOperation("客户点赞操作")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "employeeId",value = "员工id",dataType = "Integer",required = true),
            @ApiImplicitParam(name = "customerId", value = "客户id", dataType = "Integer",required = true)
    })
    @PostMapping(value = "updatePraise")
    public synchronized Result updatePraise(Integer employeeId, Integer customerId) {
        int state = StateCode.PraiseStatusEnum.NOT_PRAISE.getCode();
        try {
            BcCustomerEmployee bcCustomerEmployee = bcCustomerEmployeeService.selectByEmpIdAndCusId(employeeId,customerId);
            BcEmployee bcEmployee = bcEmployeeService.selectById(employeeId);
            // 用户当前状态是点赞，那执行的就是取消点赞操作
            if (bcCustomerEmployee.getCustIsLike() == StateCode.PraiseStatusEnum.PRAISE.getCode()) {
                bcCustomerEmployee.setCustIsLike(StateCode.PraiseStatusEnum.NOT_PRAISE.getCode());
                int res = bcCustomerEmployeeService.updateCustomerEmployeeInfo(bcCustomerEmployee);
                // 更新客户员工关系表成功
                if (res > 0) {
                    bcEmployee.setPraise(bcEmployee.getPraise() - 1);
                    int result = bcEmployeeService.updateEmployeeInfo(bcEmployee);
                    if (result > 0) {
                        state = StateCode.PraiseStatusEnum.NOT_PRAISE.getCode();
                    }
                }
            } else {
                bcCustomerEmployee.setCustIsLike(StateCode.PraiseStatusEnum.PRAISE.getCode());
                int res = bcCustomerEmployeeService.updateCustomerEmployeeInfo(bcCustomerEmployee);
                // 更新客户员工关系表成功
                if (res > 0) {
                    bcEmployee.setPraise(bcEmployee.getPraise() + 1);
                    int result = bcEmployeeService.updateEmployeeInfo(bcEmployee);
                    if (result > 0) {
                        state = StateCode.PraiseStatusEnum.PRAISE.getCode();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (state == StateCode.PraiseStatusEnum.PRAISE.getCode()) {
            return Result.createBySuccessCodeMessage(state,"已经点赞状态");
        } else {
            return Result.createBySuccessCodeMessage(state,"未点赞状态");
        }
    }

    @ApiOperation("客户保存电话操作")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "employeeId",value = "员工id",dataType = "Integer",required = true),
            @ApiImplicitParam(name = "customerId", value = "客户id", dataType = "Integer",required = true)
    })
    @PostMapping(value = "updateSave")
    public Result updateSave(Integer employeeId, Integer customerId) {
        ResultCode state = ResultCode.ERROR;
        if (!StringUtils.isEmpty(employeeId) && !StringUtils.isEmpty(customerId)){
            try {
                BcCustomerEmployee bcCustomerEmployee = bcCustomerEmployeeService.selectByEmpIdAndCusId(employeeId,customerId);
                bcCustomerEmployee.setCusIsSave(1);
                int res = bcCustomerEmployeeService.updateCustomerEmployeeInfo(bcCustomerEmployee);
                // 更新客户员工关系表成功
                if (res > 0) {
                    state = ResultCode.SUCCESS;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{
            state = ResultCode.PARAM_IS_BLANK;
        }
        if(state.code().equals(ResultCode.SUCCESS.code()) ) {
            return Result.success();
        }else{
            return Result.failure(state);
        }
    }

    @ApiOperation("点击印象标签")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "employeeId",value = "员工id",dataType = "Integer",required = true),
            @ApiImplicitParam(name = "customerId", value = "客户id", dataType = "Integer",required = true),
            @ApiImplicitParam(name = "impressionLabelId", value = "印象标签主键id", dataType = "Integer",required = true)

    })
    @PostMapping(value = "impressionLabel")
    public Result impressionLabel(Integer employeeId, Integer customerId, Integer impressionLabelId) {
        ResultCode state = ResultCode.ERROR;
        try {
            BcImpressionlabel bcImpressionlabel = bcImpressionlabelService.selectInfo(impressionLabelId, customerId);
            if (bcImpressionlabel == null) {
                // 未点过
                BcEmployeeImpressionlabel bcEmployeeImpressionlabel = bcEmployeeImpressionlabelService.selectById(impressionLabelId);
                bcEmployeeImpressionlabel.setPointsNum(bcEmployeeImpressionlabel.getPointsNum() + 1);
                int result = bcEmployeeImpressionlabelService.updateEmployeeImpressionlabel(bcEmployeeImpressionlabel);
                if (result > 0) {
                    bcImpressionlabelService.insertInfo(impressionLabelId, customerId);
                    state = ResultCode.SUCCESS;
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

    @ApiOperation("根据员工Id获取小程序名称")
    @ApiImplicitParam(name = "employeeId",value = "员工id",dataType = "Integer",required = true)
    @PostMapping(value = "getNickName")
    public Result getNickName(Integer employeeId){
        ResultCode state = ResultCode.ERROR;
        String nickName="";
        try {
            nickName=bcEmployeeService.selectNameByEmpId(employeeId);
            if(nickName!=null) {
                state = ResultCode.SUCCESS;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(state.code().equals(ResultCode.SUCCESS.code()) ) {
            return Result.success(nickName);
        }else{
            return Result.failure(state);
        }
    }


}
