package com.melo.focus.mapper.basic;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.melo.focus.domain.basic.RoleBusiness;
@Mapper
public interface RoleBusinessMapper {
    int deleteByPrimaryKey(String id);

    int insert(RoleBusiness record);

    RoleBusiness selectByPrimaryKey(String id);

    List<RoleBusiness> selectAll();

    int updateByPrimaryKey(RoleBusiness record);
}