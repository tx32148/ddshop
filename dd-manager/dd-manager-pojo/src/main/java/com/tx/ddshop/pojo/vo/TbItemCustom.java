package com.tx.ddshop.pojo.vo;

import com.tx.ddshop.pojo.po.TbItem;

//自定义商品显示类
public class TbItemCustom extends TbItem {

    private String catName;

    private String statusName;

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }
}
