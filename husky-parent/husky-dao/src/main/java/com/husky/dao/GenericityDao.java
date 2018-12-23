package com.husky.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;

import com.husky.exception.QueryException;

/**
 * 
 * @author swq
 * @date 2018年12月1日
 * @des TODO
 */
public class GenericityDao  implements BaseDao {

	
	private SqlSessionTemplate sessionTemplate;
	
	public void setSessionTemplate(SqlSessionTemplate sessionTemplate) {
		this.sessionTemplate = sessionTemplate;
	}
	
	

	public SqlSessionTemplate getSessionTemplate() {
		return sessionTemplate;
	}



	@Override
	public <T>T queryOne(String nameSpace, String param) throws Exception {
		List<T> result = sessionTemplate.selectList(nameSpace, param);
		if(result.size()>1) {
			throw new QueryException("返回多个结果");
		}
		if(result.size() == 0) {
			return null;
		}
		return result.get(0);
	}

	@Override
	public <T>T queryOne(String nameSpace, Map<String,Object> param) throws QueryException {
		List<T> result = sessionTemplate.selectList(nameSpace, param);
		if(result.size()>1) {
			throw new QueryException("返回多个结果");
		}
		if(result.size() == 0) {
			return null;
		}
		return result.get(0);
	}

	@Override
	public  <T>T  queryOne(String nameSpace, Object param) throws QueryException {
		List<T> result = sessionTemplate.selectList(nameSpace, param);
		if(result.size()>1) {
			throw new QueryException("返回多个结果");
		}
		if(result.size() == 0) {
			return null;
		}
		return result.get(0);
	}

	@Override
	public Long updateOne(String nameSpace, Map param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long updateOne(String nameSpace, Object param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long insertOne(String nameSpace, Map param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long insertOne(String nameSpace, Object param) {
		int insert = sessionTemplate.insert(nameSpace, param);
		return (long) insert;
	}

	@Override
	public Long deleteOne(String nameSpace, String param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long deleteOne(String nameSpace, Map param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long deleteOne(String nameSpace, Object param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> List<T> queryList(String nameSpace, String param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public  List queryList(String nameSpace, Map param) {
		return null;
	}

	@Override
	public <T> List<T> queryList(String nameSpace, Object param) {
		return sessionTemplate.selectList(nameSpace, param);
	}

	@Override
	public Long batchUpdate(String nameSpace, List param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long batchInsert(String nameSpace, List param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long batchDelete(String nameSpace, List param) {
		// TODO Auto-generated method stub
		return null;
	}
}
