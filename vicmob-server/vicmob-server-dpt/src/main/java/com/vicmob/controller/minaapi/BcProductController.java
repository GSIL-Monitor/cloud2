package com.vicmob.controller.minaapi;

import beans.Result;
import beans.ResultCode;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.vicmob.entity.BcProduct;
import com.vicmob.service.minaapi.BcProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import utils.DecryptUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 产品相关业务控制层
 * @author ricky
 * @date 2019-02-11
 */
@Api(value = "产品相关业务控制层",description = "产品相关业务控制层")
@RestController
@RequestMapping(value = "mina/BcProduct")
public class BcProductController {

    @Value("${pictureUrl}")
    private String PICTUREURL_PATH;

    @Resource
    private BcProductService bcProductService;

    @ApiOperation(value = "根据员工Id查询员工所推荐的商品")
    @ApiImplicitParam(name = "employeeId", value = "员工id", dataType = "Integer",required = true)
    @PostMapping(value = "/selectByEmployeeId")
    public Result selectByEmployeeId(Integer employeeId) {
        ResultCode state = ResultCode.ERROR;
        List<BcProduct> bcProducts=null;
        try {
            bcProducts=bcProductService.selectByEmployeeId(employeeId);
            if(bcProducts.size()!=0) {
                state = ResultCode.SUCCESS;
                for(BcProduct bcProduct:bcProducts) {
                    if(bcProduct.getProductCover()!=null) {
                        bcProduct.setProductCover(DecryptUtils.rebuildPic(bcProduct.getProductCover(),PICTUREURL_PATH));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(state.code().equals(ResultCode.SUCCESS.code()) ) {
            return Result.success(bcProducts);
        }else{
            return Result.failure(state);
        }
    }

    @ApiOperation(value = "通过一级分类Id查询所有商品")
    @PostMapping(value = "/selectByFirClassifyId")
    public Result selectByFirClassifyId(HttpServletRequest request) {
        String firClassifyId = request.getParameter("classifyId");
        String minaStr = request.getParameter("minaStr");
        String appId = request.getParameter("appId");
        String pageNo = request.getParameter("pageNo");
        String pageSize = request.getParameter("pageSize");
        int minaId = Integer.valueOf(DecryptUtils.getMinaId(minaStr,appId));
        ResultCode state = ResultCode.ERROR;
        if ( !StringUtils.isEmpty(pageNo)&& !StringUtils.isEmpty(pageSize)){
            System.err.println("pageNo====="+pageNo+"=======pageSize============"+pageSize);
            PageHelper.startPage(Integer.valueOf(pageNo),Integer.valueOf(pageSize));
        }
        PageInfo<BcProduct> bcProductPage = new PageInfo<>();
        try {
            BcProduct bcProduct=new BcProduct();
            if(!StringUtils.isEmpty(firClassifyId)) {
                bcProduct.setFirClassifyId(Integer.valueOf(firClassifyId));
            }
            bcProduct.setMinaId(minaId);
            bcProductPage = new PageInfo<>(bcProductService.selectByFirClassifyId(bcProduct));
            if(bcProductPage.getList().size()!=0) {
                state = ResultCode.SUCCESS;
                for(BcProduct bProduct:bcProductPage.getList()) {
                    if(bProduct.getProductCover()!=null) {
                        bProduct.setProductCover(DecryptUtils.rebuildPic(bProduct.getProductCover(),PICTUREURL_PATH));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(state.code().equals(ResultCode.SUCCESS.code()) ) {
            return Result.success(bcProductPage);
        }else{
            return Result.failure(state);
        }
    }

    @ApiOperation(value = "通过二级分类Id查询所有商品")
    @PostMapping(value = "/selectBySecClassifyId")
    public Result selectBySecClassifyId(HttpServletRequest request) {
        String secClassifyId=request.getParameter("classifyId");
        String productName=request.getParameter("productName");
        String pageNo = request.getParameter("pageNo");
        String pageSize = request.getParameter("pageSize");
        ResultCode state = ResultCode.ERROR;
        PageInfo<BcProduct> bcProductPage=null;
        if ( !StringUtils.isEmpty(pageNo)&& !StringUtils.isEmpty(pageSize)){
            PageHelper.startPage(Integer.valueOf(pageNo),Integer.valueOf(pageSize));
        }
        try {
            BcProduct bcProduct=new BcProduct();
            if(!StringUtils.isEmpty(secClassifyId)) {
                bcProduct.setSecClassifyId(Integer.valueOf(secClassifyId));
            }
            if(!StringUtils.isEmpty(productName)) {
                bcProduct.setProductName(productName.trim());
            }
            bcProductPage=new PageInfo<>(bcProductService.selectBySecClassifyId(bcProduct));
            if(bcProductPage.getList().size()!=0) {
                state = ResultCode.SUCCESS;
                for(BcProduct bProduct:bcProductPage.getList()) {
                    if(bProduct.getProductCover()!=null) {
                        bProduct.setProductCover(DecryptUtils.rebuildPic(bProduct.getProductCover(),PICTUREURL_PATH));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(state.code().equals(ResultCode.SUCCESS.code()) ) {
            return Result.success(bcProductPage);
        }else{
            return Result.failure(state);
        }
    }

    @ApiOperation(value = "通过主键查询商品信息")
    @ApiImplicitParam(name = "productId", value = "产品id", dataType = "Integer",required = true)
    @PostMapping(value = "/selectById")
    public Result selectById(Integer productId) {
        ResultCode state = ResultCode.ERROR;
        BcProduct bcProduct=null;
        try {
            bcProduct=bcProductService.selectById(productId);
            if(bcProduct!=null) {
                state = ResultCode.SUCCESS;
                if(bcProduct.getProductCover()!=null) {
                    bcProduct.setProductCover(DecryptUtils.rebuildPic(bcProduct.getProductCover(),PICTUREURL_PATH));
                }
                if(bcProduct.getProductPics()!=null) {
                    bcProduct.setProductPics(DecryptUtils.rebuildPic(bcProduct.getProductPics(),PICTUREURL_PATH));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(state.code().equals(ResultCode.SUCCESS.code()) ) {
            return Result.success(bcProduct);
        }else{
            return Result.failure(state);
        }
    }
}
