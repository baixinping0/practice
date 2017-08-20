package com.baixinping.cache.common.cache.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.baixinping.cache.common.cache.Cache;
import com.sun.org.apache.bcel.internal.util.ClassPath;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.Serializable;

public class RedisCache implements Cache {
	@Resource
	private RedisTemplate<Serializable, Object> redisTemplate;

	@Override
	public String  getValueFromCache(String redisKey) {
		redisTemplate.setValueSerializer(new StringRedisSerializer());
		ValueOperations<Serializable, Object> oper = redisTemplate.opsForValue();
		String value = (String) oper.get(redisKey);
		return value;
	}

	@Override
	public void setValueToCache(String redisKey, String value) {
		redisTemplate.setKeySerializer(new StringRedisSerializer());
		redisTemplate.opsForValue().set(redisKey.trim(), value);

	}
	@Override
	public String getKeys(String model) {
		return null;
	}
}