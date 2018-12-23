package com.husky.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

/**
 * 
 * @author swq
 * @create_date 2018年12月11日
 */
public class JsonUtil {

	public static <T> T JsonStrToBean(String jsonStr ) {
		return JSON.parseObject("jsonStr",new TypeReference<T>(){});
	}
	
	public static <T>  String BeanToJsonStr(T bean) {
		return JSON.toJSONString(bean);
	}
}
