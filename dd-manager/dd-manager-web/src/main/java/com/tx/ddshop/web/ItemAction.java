package com.tx.ddshop.web;

import com.tx.ddshop.common.dao.Order;
import com.tx.ddshop.common.dao.Page;
import com.tx.ddshop.common.dao.Result;
import com.tx.ddshop.pojo.po.TbItem;
import com.tx.ddshop.pojo.vo.TbItemCustom;
import com.tx.ddshop.pojo.vo.TbItemQuery;
import com.tx.ddshop.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Scope("prototype")
public class ItemAction {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ItemService itemService;

    @ResponseBody
    @RequestMapping(value="/item/{itemId}",method = RequestMethod.GET)
    public TbItem getById(@PathVariable("itemId") long itemId){

        return itemService.getById(itemId);
    }

    @RequestMapping("/items")
    @ResponseBody
    public Result<TbItemCustom> listItemByPage(Page page, Order order, TbItemQuery tbItemQuery){
        Result<TbItemCustom> list = null;
        try {
            list=itemService.listItemByPage(page,order,tbItemQuery);
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            e.printStackTrace();
        }
        return list;
    }

    @RequestMapping("/items/batch")
    @ResponseBody
    public int updatebatch(@RequestParam("ids[]") List<Long> ids,@RequestParam("flag")int flag){
        int i=0;
        try{
            i = itemService.updateBatch(ids,flag);
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            e.printStackTrace();
        }
        return i;
    }

    @ResponseBody
    @RequestMapping("/item")
    public int saveItem(TbItem tbItem,String content){
        int i=0;
        try{
            i = itemService.saveItem(tbItem,content);

        }catch (Exception e){
            logger.error(e.getMessage(),e);
            e.printStackTrace();
        }

        return i;
    }



}
