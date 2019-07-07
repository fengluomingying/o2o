package com.java.service.ServiceImpl;

import com.java.dao.ShopDao;
import com.java.dto.ImageHolder;
import com.java.dto.ShopExecution;
import com.java.entity.Shop;
import com.java.enums.ShopStateEnum;
import com.java.exception.ShopExecuteException;
import com.java.service.ShopService;
import com.java.util.ImgUtil;
import com.java.util.PageCalculator;
import com.java.util.PathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;
import java.util.Date;
import java.util.List;

@Service
public class ShopServiceImpl implements ShopService {

    @Autowired
    private ShopDao shopDao;
    @Override
    @Transactional
    public ShopExecution addShop(Shop shop, ImageHolder imageHolder) {
        if(shop == null){
            return new ShopExecution(ShopStateEnum.NULL_SHOP);
        }
        try {
            //给店铺信息赋予初值
            shop.setCreateTime(new Date());
            shop.setUpdateTime(new Date());
            //添加店铺
            int effectedNum = shopDao.insertShop(shop);
            if(effectedNum <= 0){
                throw new ShopExecuteException("添加店铺出错");
            }else{
                if(imageHolder.getImage() != null){
                    try {
                        //添加店铺图片
                        addShopImg(shop, imageHolder);
                    }catch (Exception e){
                        throw new ShopExecuteException("addShopImg error:" + e.getMessage());
                    }
                    //更新店铺的图片信息
                    effectedNum = shopDao.updateShop(shop);
                    if(effectedNum <= 0){
                        throw new ShopExecuteException("更新图片地址失败");
                    }
                }
            }
        }catch (Exception e){
            throw new RuntimeException("addShop error:" + e.getMessage());
        }
        return new ShopExecution(ShopStateEnum.CHECK, shop);
    }

    @Override
    public Shop getShopByShopId(Long shopId) {
        return shopDao.selectShop(shopId);
    }

    @Override
    @Transactional
    public ShopExecution modifyShop(Shop shop, ImageHolder imageHolder) throws ShopExecuteException {
        if(shop == null || shop.getShopId() == null){
            return new ShopExecution(ShopStateEnum.NULL_SHOP);
        }else{
            /*
             * 1。是否有图片文件需要更改，需要的话先把原先的文件删除
             * 2. 更改shop
             * */
            try {
                if(imageHolder.getImage() != null && imageHolder.getImageName() != null && !"".equals(imageHolder.getImageName())){
                    Shop shopTmp = shopDao.selectShop(shop.getShopId());
                    if(shopTmp.getShopImg() != null){
                        ImgUtil.deleteFileOrDir(shopTmp.getShopImg());
                    }
                    addShopImg(shopTmp, imageHolder);
                }

                shop.setUpdateTime(new Date());
                int effectedNum = shopDao.updateShop(shop);
                if(effectedNum <= 0){
                    return new ShopExecution(ShopStateEnum.INNER_ERROR);
                }else{
                    shop = shopDao.selectShop(shop.getShopId());
                    return new ShopExecution(ShopStateEnum.SUCCESS, shop);
                }
            }catch (Exception e){
                throw new ShopExecuteException("modifyShop error:" + e.getMessage());
            }
        }
    }

    @Override
    public ShopExecution getShopList(Shop shopCondition, int pageIndex, int pageSize) {
        int rowIndex = PageCalculator.calculateRowIndex(pageIndex, pageSize);
        List<Shop> shopList = shopDao.selectShopList(shopCondition, rowIndex, pageSize);
        ShopExecution se = new ShopExecution();
        int count = shopDao.selectCountOfShop(shopCondition);
        if( shopList != null){
            se.setShopList(shopList);
            se.setCount(count);
        }else {
            se.setState(ShopStateEnum.INNER_ERROR.getState());
        }
        return se;
    }

    //对图片进行处理后添加
    public void addShopImg(Shop shop, ImageHolder imageHolder){

        String relativePath = PathUtil.getShopImagePath(shop.getShopId());
        String destAddr = ImgUtil.generateThumbnail( imageHolder, relativePath);
        shop.setShopImg(destAddr);
    }
}
