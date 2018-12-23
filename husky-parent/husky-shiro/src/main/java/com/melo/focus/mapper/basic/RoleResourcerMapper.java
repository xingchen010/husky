package com.melo.focus.mapper.basic;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.melo.focus.domain.basic.RoleResourcer;
@Mapper
public interface RoleResourcerMapper {
    int deleteByPrimaryKey(String id);

    int insert(RoleResourcer record);

    RoleResourcer selectByPrimaryKey(String id);

    List<RoleResourcer> selectAll();

    int updateByPrimaryKey(RoleResourcer record);
}