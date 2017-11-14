package com.tx.ddshop.service;

import com.tx.ddshop.common.dao.Order;
import com.tx.ddshop.common.dao.Page;
import com.tx.ddshop.common.dao.Result;
import com.tx.ddshop.pojo.po.TbItem;
import com.tx.ddshop.pojo.vo.TbItemCustom;
import com.tx.ddshop.pojo.vo.TbItemQuery;

import java.util.List;

public interface ItemService {

    TbItem getById(long itemId);

    //分页
    Result<TbItemCustom> listItemByPage(Page page, Order order,TbItemQuery tbItemQuery);

    //批量修改状态
    int updateBatch(List<Long> ids,int flag);

    //新增商品
    int saveItem(TbItem tbItem, String content);
}
