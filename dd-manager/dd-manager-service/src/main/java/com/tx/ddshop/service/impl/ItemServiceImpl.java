package com.tx.ddshop.service.impl;

import com.tx.ddshop.common.dao.Order;
import com.tx.ddshop.common.dao.Page;
import com.tx.ddshop.common.dao.Result;
import com.tx.ddshop.common.util.IDUtils;
import com.tx.ddshop.dao.TbItemCustomMapper;
import com.tx.ddshop.dao.TbItemDescMapper;
import com.tx.ddshop.dao.TbItemMapper;
import com.tx.ddshop.pojo.po.TbContentExample;
import com.tx.ddshop.pojo.po.TbItem;
import com.tx.ddshop.pojo.po.TbItemDesc;
import com.tx.ddshop.pojo.vo.TbItemCustom;
import com.tx.ddshop.pojo.vo.TbItemQuery;
import com.tx.ddshop.service.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ItemServiceImpl implements ItemService{

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private TbItemMapper tbItemMapper;

    @Autowired
    private TbItemDescMapper tbItemDescMapper;

    @Autowired
    private TbItemCustomMapper tbItemCustomMapper;

    @Override
    public TbItem getById(long itemId) {

        return tbItemMapper.selectByPrimaryKey(itemId);
    }

    @Override
    public Result<TbItemCustom> listItemByPage(Page page, Order order,TbItemQuery tbItemQuery) {
        Result<TbItemCustom> result=null;
        try{
            //创建一个Map封装前台传递过来的参数
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("page", page);
            map.put("order", order);
            map.put("tbItemQuery", tbItemQuery);
            //创建一个响应参数实体类
            result = new Result<TbItemCustom>();
            result.setTotal(tbItemCustomMapper.countItems(map));
            result.setRows(tbItemCustomMapper.listItemByPage(map));
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int updateBatch(List<Long> ids,int flag) {
        int i = 0;
        try{
            //包含状态
            TbItem record =new TbItem();
            record.setStatus((byte) flag);
            //创建更新模板
            TbContentExample example = new TbContentExample();
            TbContentExample.Criteria criteria = example.createCriteria();
            criteria.andIdIn(ids);
            //执行修改
            tbItemMapper.updateByExampleSelective(record,example);
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            e.printStackTrace();
        }
        return 0;
    }

    @Transactional
    @Override
    public int saveItem(TbItem tbItem, String content) {
        int i=0;
        try{
            //tb_item
            Long itemId = IDUtils.getItemId();
            tbItem.setId(itemId);
            tbItem.setStatus((byte)2);
            tbItem.setCreated(new Date());
            tbItem.setUpdated(new Date());
            i = tbItemMapper.insert(tbItem);

            //tb_item_desc
            TbItemDesc desc = new TbItemDesc();
            desc.setItemId(itemId);
            desc.setItemDesc(content);
            desc.setCreated(new Date());
            desc.setUpdated(new Date());
            i += tbItemDescMapper.insert(desc);

        }catch (Exception e){
            logger.error(e.getMessage(),e);
            e.printStackTrace();
        }

        return i;
    }
}
