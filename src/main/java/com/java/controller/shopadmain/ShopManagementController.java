package com.java.controller.shopadmain;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.java.dto.ImageHolder;
import com.java.dto.ShopExecution;
import com.java.entity.Area;
import com.java.entity.PersonInfo;
import com.java.entity.Shop;
import com.java.entity.ShopCategory;
import com.java.enums.ShopStateEnum;
import com.java.exception.ShopExecuteException;
import com.java.service.AreaService;
import com.java.service.ShopCategoryService;
import com.java.service.ShopService;
import com.java.util.CodeUtil;
import com.java.util.HttpServletRequestUtil;
import com.java.util.ImgUtil;
import com.java.util.PathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/shopadmin")
public class ShopManagementController {
    @Autowired
    private ShopService shopService;
    @Autowired
    private ShopCategoryService shopCategoryService;
    @Autowired
    private AreaService areaService;

    @RequestMapping(value = "/getshopinitinfo", method = RequestMethod.GET)
    @ResponseBody
    private Map<String, Object> getShopInitInfo(){
        Map<String, Object> modelMap = new HashMap<>();
        List<ShopCategory> shopCategoryList = new ArrayList<>();
        List<Area> areaList = new ArrayList<>();
        try {
            shopCategoryList = shopCategoryService.getShopCategoryList(new ShopCategory());
            areaList = areaService.getAreaList();
            modelMap.put("shopCategoryList", shopCategoryList);
            modelMap.put("areaList", areaList);
            modelMap.put("result",true);
        }catch (Exception e){
            modelMap.put("result", false);
            modelMap.put("errMsg", e.getMessage());
        }
        return modelMap;
    }
    @RequestMapping(value = "/registershop", method = RequestMethod.POST)
    @ResponseBody
    private Map<String, Object> registerShop(HttpServletRequest request){
        //1.接受并转化相应的参数，包括店铺信息跟图片信息
        Map<String, Object> modelMap = new HashMap<String, Object>();
        if (!CodeUtil.checkVerifyCode(request)){
            modelMap.put("result",false);
            modelMap.put("errMsg", "输入了错误的验证码");
            return modelMap;
        }
        String shopStr = HttpServletRequestUtil.getString(request, "shopStr");
        ObjectMapper objectMapper = new ObjectMapper();
        Shop shop = null;
        try {
            shop = objectMapper.readValue(shopStr, Shop.class);
        } catch (IOException e) {
            modelMap.put("result",false);
            modelMap.put("errMsg", e.getMessage());
            return modelMap;
        }
        //处理图片
        CommonsMultipartFile shopImg = null;
        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(
                request.getSession().getServletContext()
        );
        if(commonsMultipartResolver.isMultipart(request)){
            MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
            shopImg = (CommonsMultipartFile) multipartHttpServletRequest.getFile("shopImg");
        }else {
            modelMap.put("result",false);
            modelMap.put("error","上传图片不能为空");
            return modelMap;
        }
        //2.注册店铺
        if(shop != null && shopImg != null){
            PersonInfo owner = (PersonInfo) request.getSession().getAttribute("user");
            ImageHolder imageHolder = new ImageHolder();
            shop.setPersonInfo(owner);
            ShopExecution shopExecution = null;
            try {
                imageHolder.setImage(shopImg.getInputStream());
                imageHolder.setImageName(shopImg.getOriginalFilename());
                shopExecution = shopService.addShop(shop, imageHolder);
                if(ShopStateEnum.CHECK.getState() == shopExecution.getState()){
                    modelMap.put("result",true);
                    List<Shop> shopList = (List<Shop>) request.getSession().getAttribute("shopList");
                    if(shopList == null || shopList.size() == 0){
                        shopList = new ArrayList<>();
                    }
                    shopList.add(shopExecution.getShop());
                    request.setAttribute("shopList", shopList);
                }else {
                    modelMap.put("result",false);
                    modelMap.put("errMsg",shopExecution.getStateInfo());
                }
            } catch (IOException e) {
                modelMap.put("result",false);
                modelMap.put("errMsg",shopExecution.getStateInfo());
            }
            return modelMap;
        }else{
            modelMap.put("result",false);
            modelMap.put("errMsg","请输入店铺信息");
            return modelMap;
        }
    }

    @RequestMapping(value = "/getshopbyshopid", method = RequestMethod.GET)
    @ResponseBody
    private Map<String, Object> getShopByShopId(HttpServletRequest request){
        Map<String, Object> modelMap = new HashMap<>();
        List<Area> areaList = new ArrayList<>();
        Long shopId = HttpServletRequestUtil.getLong(request, "shopId");
        if(shopId != -1){
            try {
                Shop shop = shopService.getShopByShopId(shopId);
                areaList = areaService.getAreaList();
                modelMap.put("shop", shop);
                modelMap.put("areaList",areaList);
                modelMap.put("result", true);
            }catch (Exception e){
                modelMap.put("result", false);
                modelMap.put("errMsg", e.toString());
            }

        }else{
            modelMap.put("result",false);
            modelMap.put("errMsg","shopId 为空");
        }
        return modelMap;
    }

    @RequestMapping(value = "/modifyshop", method = RequestMethod.POST)
    @ResponseBody
    private Map<String, Object> modifyShop(HttpServletRequest request) throws IOException {
        Map<String, Object> modelMap = new HashMap<>();
        //1、验证码
        if(!CodeUtil.checkVerifyCode(request)){
            modelMap.put("result", false);
            modelMap.put("errMsg", "输入的验证码有误！");
            return modelMap;
        }
        String shopStr = HttpServletRequestUtil.getString(request, "shopStr");
        ObjectMapper objectMapper = new ObjectMapper();
        Shop shop =null;
        try {
            shop = objectMapper.readValue(shopStr, Shop.class);
        } catch (IOException e) {
            modelMap.put("result",false);
            modelMap.put("errMsg",e.getMessage());
            return modelMap;
        }

        //处理图片
        CommonsMultipartFile shopImg = null;
        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        if(commonsMultipartResolver.isMultipart(request)){
            MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
            shopImg = (CommonsMultipartFile) multipartHttpServletRequest.getFile("shopImg");
        }

        //修改店铺
        if(shop != null && shop.getShopId() != null){
            ShopExecution shopExecution;
            ImageHolder imageHolder = new ImageHolder();
            try {
                if(shopImg == null){
                    shopExecution = shopService.modifyShop(shop,null);
                }else{
                    imageHolder.setImage(shopImg.getInputStream());
                    imageHolder.setImageName(shopImg.getOriginalFilename());
                    shopExecution = shopService.modifyShop(shop, imageHolder);
                }
                if(shopExecution.getState() == ShopStateEnum.SUCCESS.getState()){
                    modelMap.put("result",true);
                }else {
                    modelMap.put("result",false);
                    modelMap.put("errMsg",shopExecution.getStateInfo());
                }
            } catch (ShopExecuteException e) {
                modelMap.put("result",false);
                modelMap.put("errMsg",e.getMessage());
            }
            return modelMap;
        }else {
            modelMap.put("result",false);
            modelMap.put("errMsg","请输入店铺信息");
            return modelMap;
        }


    }

    @RequestMapping(value = "getshoplist", method = RequestMethod.GET)
    @ResponseBody
    private Map<String, Object> getShopList(HttpServletRequest request){
        Map<String, Object> modelMap = new HashMap<String, Object>();
        Shop shopCondition = new Shop();
        PersonInfo user = new PersonInfo();
        user.setUserId(1l);
        user.setUserName("test");
        request.getSession().setAttribute("user", user);
        PersonInfo owner = (PersonInfo) request.getSession().getAttribute("user");
        shopCondition.setPersonInfo(owner);
        try {
            ShopExecution se = shopService.getShopList(shopCondition, 0, 100);
            modelMap.put("shopList", se.getShopList());
            modelMap.put("user", user);
            modelMap.put("result", true);
        }catch (Exception e){
            modelMap.put("result",false);
            modelMap.put("errMsg", e.getMessage());
        }
        return modelMap;
    }

    @RequestMapping(value = "getshopmanagementinfo", method = RequestMethod.GET)
    @ResponseBody
    private Map<String, Object> getShopManagementInfo(HttpServletRequest request){
        Map<String, Object> modelMap = new HashMap<String, Object>();
        long shopId = HttpServletRequestUtil.getLong(request,"shopId");
        if (shopId <= 0){
            Object currentShopObj = request.getSession().getAttribute("currentShop");
            if (currentShopObj == null){
                modelMap.put("redirect",true);
                modelMap.put("url", "/o2o/shopadmin/shoplist");
            }else {
                Shop currentShop = (Shop) currentShopObj;
                modelMap.put("redirect", false);
                modelMap.put("shopId",currentShop.getShopId());
            }
        }else {
            Shop currentShop = new Shop();
            currentShop.setShopId(shopId);
            request.getSession().setAttribute("currentShop",currentShop);
            modelMap.put("redirect",false);
            modelMap.put("shopId", shopId);
        }
        return modelMap;
    }

//    private static void inputStreamToFile(InputStream is, File file){
//        FileOutputStream fos = null;
//        try {
//            fos = new FileOutputStream(file);
//            int len = 0;
//            byte[] b = new byte[1024];
//            while ((len = is.read(b)) != -1){
//                fos.write(b, 0, len);
//            }
//        } catch (Exception e) {
//            throw new RuntimeException("调用函数时产生异常:" +e.getMessage());
//        }finally {
//            try {
//                if (is != null){
//                    is.close();
//                }
//                if(fos != null){
//                    fos.close();
//                }
//            }catch (Exception e){
//                throw new RuntimeException("关闭io时产生异常:"+e.getMessage());
//            }
//        }
//    }
}
