package com.java.dto;

import com.java.entity.Shop;
import com.java.enums.ShopStateEnum;

import java.util.List;

public class ShopExecution {
//    状态标识
    private int state;
//    状态标识信息
    private String stateInfo;
//    店铺的数量
    private int count;
//    店铺
    private Shop shop;
//    店铺列表
    private List<Shop> shopList;

    public ShopExecution(){}

//    店铺操作失败时使用的构造器
    public ShopExecution(ShopStateEnum shopStateEnum){
        this.state = shopStateEnum.getState();
        this.stateInfo = shopStateEnum.getStateInfo();
    }
//    店铺操作成功时使用的构造器
    public ShopExecution(ShopStateEnum shopStateEnum, Shop shop){
        this.state = shopStateEnum.getState();
        this.stateInfo = shopStateEnum.getStateInfo();
        this.shop = shop;
    }
//    店铺操作成功时使用的构造器
    public ShopExecution(ShopStateEnum shopStateEnum, List<Shop> listShop){
        this.state = shopStateEnum.getState();
        this.stateInfo = shopStateEnum.getStateInfo();
        this.shopList = listShop;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public void setStateInfo(String stateInfo) {
        this.stateInfo = stateInfo;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public List<Shop> getShopList() {
        return shopList;
    }

    public void setShopList(List<Shop> shopList) {
        this.shopList = shopList;
    }
}
