package com.tx.ddshop.service;

import com.tx.ddshop.common.dao.TreeNode;

import java.util.List;

public interface ItemCatService {

    public List<TreeNode> listItemCats(Long parentId);
}
