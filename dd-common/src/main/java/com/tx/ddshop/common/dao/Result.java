package com.tx.ddshop.common.dao;

import java.util.List;

//封装分页请求响应类
public class Result<T> {

    //符合条件的总数
    private int total;
    //每页符合条件的集合
    private List<T> rows;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }
}
