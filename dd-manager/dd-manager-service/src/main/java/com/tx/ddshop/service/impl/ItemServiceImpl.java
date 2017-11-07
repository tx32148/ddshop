package com.tx.ddshop.service.impl;

import com.tx.ddshop.dao.TbItemMapper;
import com.tx.ddshop.pojo.po.TbItem;
import com.tx.ddshop.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceImpl implements ItemService{

    @Autowired
    private TbItemMapper tbItemMapper;

    @Override
    public TbItem getById(long itemId) {

        return tbItemMapper.selectByPrimaryKey(itemId);
    }
}
