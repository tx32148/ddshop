package com.tx.ddshop.dao;

import com.tx.ddshop.common.dao.Order;
import com.tx.ddshop.common.dao.Page;
import com.tx.ddshop.pojo.vo.TbItemCustom;
import com.tx.ddshop.pojo.vo.TbItemQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

//自定义商品实体数据访问层接口
public interface TbItemCustomMapper {

    //查询符合条件的商品总数
    int countItems(Map<String,Object> map);

    //查询当前页的分页集合
    List<TbItemCustom> listItemByPage(Map<String,Object> map);

}
