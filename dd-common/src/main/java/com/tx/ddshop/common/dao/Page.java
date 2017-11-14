package com.tx.ddshop.common.dao;

//封装分页请求参数
public class Page {

    //当前页
    private int page;
    //每页显示条数
    private int rows;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    //获取偏移量,不需要手工设置
    public int getOffset(){

        return (page-1)*rows;
    }
}
