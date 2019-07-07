package com.java.controller.shopadmain;

import com.java.dto.ProductCategoryExecution;
import com.java.dto.Result;
import com.java.entity.ProductCategory;
import com.java.entity.Shop;
import com.java.enums.ProductCategoryStateEnum;
import com.java.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("shopadmin")
public class ProductCategoryManagementController {
    @Autowired
    ProductCategoryService productCategoryService;

    @RequestMapping(value = "/getproductcategorylist", method = RequestMethod.GET)
    @ResponseBody
    private Result<List<ProductCategory>> getProductCategoryList(HttpServletRequest request){
        Shop currentShop = (Shop) request.getSession().getAttribute("currentShop");
        if(currentShop != null && currentShop.getShopId() > 0){
            List<ProductCategory> productCategoryList = productCategoryService.getProductCategoryList(currentShop.getShopId());
            return new Result<List<ProductCategory>>(true, productCategoryList);
        }else {
            ProductCategoryStateEnum ps = ProductCategoryStateEnum.INNER_ERROR;
            return new Result<List<ProductCategory>>(false, ps.getStateInfo(),ps.getState());
        }

    }

    @RequestMapping(value = "batchaddproductcategory")
    @ResponseBody
    private Map<String, Object> batchAddProductCategory(@RequestBody List<ProductCategory> productCategoryList, HttpServletRequest request){
        Map<String, Object> modelMap = new HashMap<>();
        Shop currentShop = (Shop) request.getSession().getAttribute("currentShop");
        for(ProductCategory pc : productCategoryList){
            pc.setShop(currentShop);
            pc.setCreateTime(new Date());
        }
        if(productCategoryList != null && productCategoryList.size() > 0){
            try {
                ProductCategoryExecution pe = productCategoryService.batchAddProductCategory(productCategoryList);
                if(pe.getState() == ProductCategoryStateEnum.SUCCESS.getState()){
                    modelMap.put("result", true);
                }else {
                    modelMap.put("result", false);
                    modelMap.put("errMsg",pe.getStateInfo());
                }
            }catch (Exception e){
                modelMap.put("result", false);
                modelMap.put("errMsg", e.getMessage());
            }
        }else {
            modelMap.put("result", false);
            modelMap.put("errMsg", "请至少输入一个商品类别");
        }
        return modelMap;
    }

    @RequestMapping(value = "removeproductcategory", method = RequestMethod.POST)
    @ResponseBody
    private Map<String, Object> removeProductCategory(Long productCategoryId, HttpServletRequest request){
        Map<String, Object> modelMap = new HashMap<>();
        if(productCategoryId != null && productCategoryId > 0){
            try {
                Shop currentShop = (Shop) request.getSession().getAttribute("currentShop");
                ProductCategoryExecution pe = productCategoryService.removeProductCategory(productCategoryId, currentShop.getShopId());
                if(pe.getState() == ProductCategoryStateEnum.SUCCESS.getState()){
                    modelMap.put("result", true);
                }else {
                    modelMap.put("result", false);
                    modelMap.put("errMsg", pe.getStateInfo());
                }
            }catch (Exception e){
                modelMap.put("result", false);
                modelMap.put("errMsg", e.getMessage());
                return modelMap;
            }
        }else {
            modelMap.put("result", false);
            modelMap.put("errMsg", "请至少选择一个商品类别！");
        }
        return modelMap;
    }
}
