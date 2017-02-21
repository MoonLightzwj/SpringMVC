package com.zwj.dao;

import com.zwj.bean.WeixinTest;

public interface IWeixinTestDao {
    int deleteByPrimaryKey(Integer id);

    int insert(WeixinTest record);

    int insertSelective(WeixinTest record);

    WeixinTest selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WeixinTest record);

    int updateByPrimaryKey(WeixinTest record);
}