package com.tx.ddshop.service.impl;

import com.tx.ddshop.common.dao.TreeNode;
import com.tx.ddshop.dao.TbItemCatMapper;
import com.tx.ddshop.pojo.po.TbItemCat;
import com.tx.ddshop.pojo.po.TbItemCatExample;
import com.tx.ddshop.service.ItemCatService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemCatServiceImpl implements ItemCatService{

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private TbItemCatMapper tbItemCatMapper;

    @Override
    public List<TreeNode> listItemCats(Long parentId) {
        List<TreeNode> resultList =null;
        try{
            //创建查询模板
            TbItemCatExample example = new TbItemCatExample();
            TbItemCatExample.Criteria criteria = example.createCriteria();
            criteria.andParentIdEqualTo(parentId);

            //执行查询
            List<TbItemCat> list = tbItemCatMapper.selectByExample(example);
            resultList= new ArrayList<TreeNode>();

            //遍历原有集合
            for (int i=0;i<list.size();i++){
                TreeNode node = new TreeNode();
                node.setId(list.get(i).getId());
                node.setText(list.get(i).getName());
                node.setState(list.get(i).getIsParent() ? "closed" : "open");
                resultList.add(node);
            }
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            e.printStackTrace();
        }
        return resultList;
    }
}
