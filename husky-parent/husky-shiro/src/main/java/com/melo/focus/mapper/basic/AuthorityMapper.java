package com.melo.focus.mapper.basic;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.melo.focus.domain.basic.Authority;
@Mapper
public interface AuthorityMapper {
    int deleteByPrimaryKey(String id);

    int insert(Authority record);

    Authority selectByPrimaryKey(String id);

    List<Authority> selectAll();

    int updateByPrimaryKey(Authority record);
}