package com.melo.focus.mapper.basic;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.melo.focus.domain.basic.Resource;
@Mapper
public interface ResourceMapper {
    int deleteByPrimaryKey(String id);

    int insert(Resource record);

    Resource selectByPrimaryKey(String id);

    List<Resource> selectAll();

    int updateByPrimaryKey(Resource record);
}