package com.tx.ddshop.web;

import com.tx.ddshop.pojo.po.TbItem;
import com.tx.ddshop.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@Scope("prototype")
public class ItemAction {

    @Autowired
    private ItemService itemService;

    @ResponseBody
    @RequestMapping(value="/item/{itemId}",method = RequestMethod.GET)
    public TbItem getById(@PathVariable("itemId") long itemId){

        return itemService.getById(itemId);
    }

}
