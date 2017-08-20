package com.baixinping.cvtepro.common.cache.cache.impl;

import java.io.Serializable;
import java.util.List;

import com.baixinping.cvtepro.common.cache.cache.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import javax.annotation.Resource;

public class RedisCache implements Cache {
	@Resource
	private RedisTemplate<Serializable, Object> redisTemplate;
     /**
      * 从redis中获取某个模块中特定的值
      * @param model 模块
      * @param redisKey key
      * @return
      */
     private String getJsonString(String model, String redisKey) {
    	 String key = getKey(model, redisKey);
		 redisTemplate.setKeySerializer(new StringRedisSerializer());
		 redisTemplate.setValueSerializer(new StringRedisSerializer());
    	 ValueOperations<Serializable, Object> oper = redisTemplate.opsForValue();
    	 String value = (String) oper.get(key);
    	 return value;
     }
     private String getKey(String model, String redisKey) {
    	 return model + "-" + redisKey;
     }
	@Override
	public <T> T getValueFromCache(String model, String redisKey, Class<T> clazz) {
		String value = getJsonString(model, redisKey);
	   	T result =  JSON.parseObject(value,  new TypeReference<T>(){});
	   	return result;
	}
	@Override
	public <T> List<T> getListFromCache(String model, String redisKey, Class<T> clazz) {
		//从redsi中获取json串
   	 	String value = getJsonString(model, redisKey);
   	 	//对json串进行类型转换
   	 	List<T> result = JSON.parseArray(value, clazz);
   	 	return result;
	}
	@Override
	public void setValueToCache(String model, String redisKey, Object value) {
		 String key = getKey(model, redisKey);
		 redisTemplate.setKeySerializer(new StringRedisSerializer());
		 redisTemplate.setValueSerializer(new StringRedisSerializer());
    	 redisTemplate.opsForValue().set(key, JSON.toJSONString(value));
	}
 }