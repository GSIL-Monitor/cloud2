package com.vicmob.controller.minaapi;

import beans.Result;
import beans.ResultCode;
import com.vicmob.entity.BcCustomer;
import com.vicmob.entity.BcMinaInfo;
import com.vicmob.service.mina.BcMinaInfoService;
import com.vicmob.service.minaapi.BcCustomerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONObject;
import org.apache.commons.codec.binary.Base64;
import org.jeewx.api.core.common.WxstoreUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import utils.AesUtils;
import utils.DecryptUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 小程序粉丝信息业务控制层
 * @author ricky
 * @date 2019-02-14
 */
@Api(value = "小程序粉丝信息业务控制层",description = "小程序粉丝信息业务控制层")
@RestController
@RequestMapping(value = "mina/automaticRecovery")
public class BcCustomerController {

    @Resource
    private BcMinaInfoService minaInfoService;

    @Resource
    private BcCustomerService customerService;

    @ApiOperation(value = "获取粉丝信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "minaStr", value = "小程序id", dataType = "string",required = true),
            @ApiImplicitParam(name = "appId", value = "appId", dataType = "string",required = true),
            @ApiImplicitParam(name = "code", value = "code", dataType = "string",required = true)
    })
    @PostMapping(value = "/getInfo")
    public Result getFansInfo(HttpServletRequest request) {
        // 初始化客户ID
        Integer customerId = 0;
        // 初始化返回提示语
        String msg = "";
        // 初始化接口状态
        ResultCode state = ResultCode.SUCCESS;
        // 获取minaId
        //获取minaId
        String minaStr = request.getParameter("minaStr");
        String appId = request.getParameter("appId");
        Integer minaId = Integer.valueOf(DecryptUtils.getMinaId(minaStr,appId));
        // 获取code
        String code = request.getParameter("code");
        // code为空不操作
        if (!StringUtils.isEmpty(code)) {
            // 获取小程序信息
            BcMinaInfo minaInfo = minaInfoService.get(String.valueOf(minaId));
            // appsecret为空不操作
            if(!StringUtils.isEmpty(minaInfo.getAppSecret())){
                try {
                    // 请求微信接口获取用户基本信息
                    String url = "https://api.weixin.qq.com/sns/jscode2session?appid="+minaInfo.getAppId()
                            +"&secret="+minaInfo.getAppSecret()+"&js_code="+code+"&grant_type=authorization_code";
                    JSONObject json_object = WxstoreUtils.httpRequest(url, "GET", null);
                    System.out.println("json_object===="+json_object);
                    // 得到微信返回的两个值(openid,session_key)
                    if(json_object.has("openid")) {
                        String openId = json_object.getString("openid");
                        customerId =getCustomerId(openId,minaId);
                    } else {
                        state = ResultCode.ERROR;
                        msg = "get openId failed";
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    state = ResultCode.ERROR;
                    msg = "system error";
                }
            } else {
                state = ResultCode.ERROR;
                msg = "no appsecret";
            }
        } else {
            state = ResultCode.ERROR;
            msg = "no code";
        }
        // 返回结果
        if(state.code().equals(ResultCode.SUCCESS.code()) ) {
            return Result.success(customerId);
        }else{
            return Result.createByErrorMessage(msg);
        }

    }

    /**
     * 获取客户ID
     * @param openId 微信openId
     * @param minaId 小程序ID
     * @return Integer 客户ID
     */
    public Integer getCustomerId(String openId,Integer minaId) {
        // 判断粉丝存在不存在
        BcCustomer customer = customerService.getByOpenAndMina(openId, minaId);
        // 粉丝存在
        if(!StringUtils.isEmpty(customer)){
            return customer.getCustomerId();
        } else {
            // 粉丝不存在，插入一条新的粉丝信息
            customer = new BcCustomer();
            customer.setMinaId(minaId);
            customer.setOpenId(openId);
            customerService.insert(customer);
            return customer.getCustomerId();
        }
    }

    @ApiOperation(value = "更新客户信息")
    @PostMapping(value = "/updateInfo")
    public Result updateInfo(BcCustomer customer) {
        // 初始化返回提示语
        String msg = "";
        // 初始化接口状态
        ResultCode state = ResultCode.SUCCESS;
        try {
            // 更新客户信息
            customerService.update(customer);
        } catch (Exception e) {
            e.printStackTrace();
            state = ResultCode.ERROR;
            msg = "system error";
        }
        // 返回结果
        if(state.code().equals(ResultCode.SUCCESS.code()) ) {
            return Result.success();
        }else{
            return Result.createByErrorMessage(msg);
        }
    }

    @ApiOperation(value = "更新客户状态")
    @PostMapping(value = "/changeState")
    public Result changeState (BcCustomer customer) {
        // 初始化接口状态
        ResultCode state = ResultCode.SUCCESS;
        // 初始化接口信息
        String msg = "";
        // 判空
        if (!StringUtils.isEmpty(customer) && !StringUtils.isEmpty(customer.getCustomerId())) {
            if (StringUtils.isEmpty(customer.getChatState()) && StringUtils.isEmpty(customer.getMessageState())) {
                state = ResultCode.ERROR;
                msg = "no change property";
            } else {
                // 更新客户状态
                try {
                    customerService.updateCustomer(customer);
                } catch (Exception e) {
                    e.printStackTrace();
                    state = ResultCode.ERROR;
                    msg = "save failed";
                }
            }
        } else {
            state = ResultCode.ERROR;
            msg = "no employeeId";
        }
        // 返回结果
        if(state.code().equals(ResultCode.SUCCESS.code()) ) {
            return Result.success();
        }else{
            return Result.createByErrorMessage(msg);
        }
    }

    @ApiOperation(value = "获取微信用户手机号")
    @PostMapping(value = "/getCusPhone")
    public Result getCusPhone(HttpServletRequest request){
        //完整用户信息
        String encryptedData=request.getParameter("encryptedData");
        String code=request.getParameter("code");
        //加密算法的初始向量
        String iv=request.getParameter("iv");
        //获取minaId
        String minaStr = request.getParameter("minaStr");
        String appId = request.getParameter("appId");
        Integer minaId = Integer.valueOf(DecryptUtils.getMinaId(minaStr,appId));
        String appsecret=customerService.getAppsecret(minaId);
        //获取fansId更新bc_customer表
        String customerId = request.getParameter("customerId");
        //得到session_key
        String url="https://api.weixin.qq.com/sns/jscode2session?grant_type=authorization_code&js_code="+code+"&appid="+appId+"&secret="+appsecret;
        JSONObject json_object = WxstoreUtils.httpRequest(url, "POST", null);
        JSONObject object = JSONObject.fromObject(json_object);
        String session_key=object.getString("session_key");
        ResultCode state = ResultCode.ERROR;
        String phoneNumber = "";
        try {
            byte[] resultByte  = AesUtils.decrypt(Base64.decodeBase64(encryptedData),
                    Base64.decodeBase64(session_key),
                    Base64.decodeBase64(iv));
            if(null != resultByte && resultByte.length > 0){
                String userInfo = new String(resultByte, "UTF-8");
                state=ResultCode.SUCCESS;
                JSONObject obj = JSONObject.fromObject(userInfo);
                phoneNumber=obj.getString("phoneNumber");
                BcCustomer bcCustomer = new BcCustomer();
                bcCustomer.setCustomerId(Integer.valueOf(customerId));
                bcCustomer.setPhone(phoneNumber);
                customerService.updateByPrimaryKeySelective(bcCustomer);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        // 返回结果
        if(state.code().equals(ResultCode.SUCCESS.code()) ) {
            return Result.createBySuccess("解密成功",phoneNumber);
        }else{
            return Result.createByErrorMessage("解密失败");
        }
    }
}
