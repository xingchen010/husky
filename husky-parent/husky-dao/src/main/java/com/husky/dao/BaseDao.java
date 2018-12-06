package com.husky.dao;

import java.util.List;
import java.util.Map;

import com.husky.exception.QueryException;

public interface BaseDao {// 利用泛型打造通用数据库访问方法:增、删、查、改。

	<T>T queryOne(String nameSpace, String param) throws Exception;
	<T>T queryOne(String nameSpace, Map<String,Object> param) throws QueryException;
	<T>T queryOne(String nameSpace, Object param) throws QueryException;

	Long updateOne(String nameSpace, Map<String,Object> param);
	Long updateOne(String nameSpace, Object param);

	Long insertOne(String nameSpace, Map<String,Object> param);
	Long insertOne(String nameSpace, Object param);

	Long deleteOne(String nameSpace, String param);
	Long deleteOne(String nameSpace, Map<String,Object> param);
	Long deleteOne(String nameSpace, Object param);
	
	<T>List<T> queryList(String nameSpace, String param);
	<T>List<T> queryList(String nameSpace, Map<String,Object> param);
	<T>List<T> queryList(String nameSpace, Object param);
	
	<T> Long batchUpdate(String nameSpace, List<T> param);
	
	<T> Long batchInsert(String nameSpace, List<T> param);
	
	<T> Long batchDelete(String nameSpace, List<T> param);
	
	
}