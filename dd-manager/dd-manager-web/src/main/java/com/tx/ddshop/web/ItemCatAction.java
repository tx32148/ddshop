package com.tx.ddshop.web;

import com.tx.ddshop.common.dao.TreeNode;
import com.tx.ddshop.service.ItemCatService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

@Controller
public class ItemCatAction {

    private org.slf4j.Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ItemCatService itemCatService;

    @ResponseBody
    @RequestMapping("/itemCats")
    public List<TreeNode> listItemCatsByPid(@RequestParam("parentId") Long parentid){

        List<TreeNode> treeNodeList =null;

        try {

            treeNodeList=itemCatService.listItemCats(parentid);

        }catch (Exception e){
            logger.error(e.getMessage(),e);
            e.printStackTrace();
        }
        return  treeNodeList;
    }
}
