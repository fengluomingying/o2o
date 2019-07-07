package com.java.enums;

public enum ProductStateEnum {
    CHECK(0, "审核中"), SUCCESS(1,"操作成功"),EMPTY(-1001,"商品为空");

    private int state;
    private String stateInfo;

    private ProductStateEnum(int state, String stateInfo){
        this.state = state;
        this.stateInfo = stateInfo;
    }

    public static ProductStateEnum valueOf(int state){
        for (ProductStateEnum productStateEnum : values()){
            if(productStateEnum.getState() == state){
                return productStateEnum;
            }
        }
        return null;
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
}
