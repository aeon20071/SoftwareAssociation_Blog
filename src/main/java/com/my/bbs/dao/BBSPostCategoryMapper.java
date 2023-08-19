package com.my.bbs.dao;

import com.my.bbs.entity.BBSPostCategory;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface BBSPostCategoryMapper {
    int deleteByPrimaryKey(Integer categoryId);

    int insert(BBSPostCategory record);

    int insertSelective(BBSPostCategory record);

    BBSPostCategory selectByPrimaryKey(Integer categoryId);

    int updateByPrimaryKeySelective(BBSPostCategory record);

    int updateByPrimaryKey(BBSPostCategory record);

    List<BBSPostCategory> getBBSPostCategories();
}