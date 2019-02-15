  package com.vicmob.controller.minaapi;

import beans.Result;
import beans.ResultCode;
import beans.StateCode;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.vicmob.entity.BcProduct;
import com.vicmob.entity.BcProductClassify;
import com.vicmob.service.minaapi.BcProductClassifyService;
import com.vicmob.service.minaapi.BcProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import utils.DecryptUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * 产品分类相关业务控制层
 * @author ricky
 * @date 2019-02-11
 */
@Api(value = "产品分类相关业务控制层",description = "产品分类相关业务控制层")
@RestController
@RequestMapping(value = "mina/BcProductClassify")
public class BcProductClassifyController {

    @Value("${pictureUrl}")
    private String PICTUREURL_PATH;

    @Resource
    private BcProductClassifyService bcProductClassifyService;

    @Resource
    private BcProductService bcProductService;

    @ApiOperation(value = "查询所有一级分类标签")
    @PostMapping(value = "/selectFirClassify")
    public Result selectFirClassify(HttpServletRequest request) {
        String minaStr = request.getParameter("minaStr");
        String appId = request.getParameter("appId");
        int minaId = Integer.valueOf(DecryptUtils.getMinaId(minaStr,appId));
        ResultCode state = ResultCode.ERROR;
        List<BcProductClassify> bcProductClassifiePage = new ArrayList<>();
        try {
            BcProductClassify bcProductClassify=new BcProductClassify();
            bcProductClassify.setMinaId(minaId);
            bcProductClassifiePage=bcProductClassifyService.selectFirstClsaaifyByMinaId(bcProductClassify);
            if(bcProductClassifiePage.size() != 0) {
                state = ResultCode.SUCCESS;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(state.code().equals(ResultCode.SUCCESS.code()) ) {
            return Result.success(bcProductClassifiePage);
        }else{
            return Result.failure(state);
        }
    }

    @ApiOperation(value = "查询二级分类标签或者一级分类下的商品")
    @PostMapping(value = "/selectSecClassify")
    public Result selectSecClassify(HttpServletRequest request) {
        int state = ResultCode.ERROR.code();
        PageInfo<BcProductClassify> bcProductClassifiePage = null;
        PageInfo<BcProduct> bcProductPage=null;
        String classifyId=request.getParameter("classifyId");
        String minaStr = request.getParameter("minaStr");
        String appId = request.getParameter("appId");
        String pageNo = request.getParameter("pageNo");
        String pageSize = request.getParameter("pageSize");
        if ( !StringUtils.isEmpty(pageNo)&& !StringUtils.isEmpty(pageSize)){
            PageHelper.startPage(Integer.valueOf(pageNo),Integer.valueOf(pageSize));
        }
        int minaId = Integer.valueOf(DecryptUtils.getMinaId(minaStr,appId));
        try {
            BcProductClassify bcProductClassify=new BcProductClassify();
            bcProductClassify.setClassifyId(Integer.valueOf(classifyId));
            bcProductClassifiePage = new PageInfo<>(bcProductClassifyService.selectByClassifyId(bcProductClassify));
            //有二级分类
            if(bcProductClassifiePage.getList().size()!=0) {
                state=StateCode.ClassifyEnum.CLASSIFY.getCode();
                for(BcProductClassify bProductClassify:bcProductClassifiePage.getList()) {
                    if(bProductClassify.getClassifyPic()!=null) {
                        bProductClassify.setClassifyPic(DecryptUtils.rebuildPic(bProductClassify.getClassifyPic(),PICTUREURL_PATH));
                    }
                }
            }else {
                //查询产品
                BcProduct bcProduct=new BcProduct();
                bcProduct.setFirClassifyId(Integer.valueOf(classifyId));
                bcProduct.setMinaId(minaId);
                bcProductPage=new PageInfo<>(bcProductService.selectByFirClassifyId(bcProduct));
                if(bcProductPage.getList().size()!=0) {
                    state=StateCode.ClassifyEnum.PRODUCT.getCode();
                    for(BcProduct bProduct:bcProductPage.getList()) {
                        if(bProduct.getProductCover()!=null) {
                            bProduct.setProductCover(DecryptUtils.rebuildPic(bProduct.getProductCover(),PICTUREURL_PATH));
                        }
                        if(bcProduct.getProductPics()!=null) {
                            bcProduct.setProductPics(DecryptUtils.rebuildPic(bcProduct.getProductPics(),PICTUREURL_PATH));
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(Integer.valueOf(state).equals(ResultCode.ERROR.code()) ) {
            return Result.failure(ResultCode.ERROR);
        }else if(Integer.valueOf(state).equals(StateCode.ClassifyEnum.CLASSIFY.getCode())){
            return Result.createBySuccess(StateCode.ClassifyEnum.CLASSIFY.getCode(),bcProductClassifiePage);
        }else {
            return Result.createBySuccess(StateCode.ClassifyEnum.PRODUCT.getCode(),bcProductPage);
        }
    }

}
