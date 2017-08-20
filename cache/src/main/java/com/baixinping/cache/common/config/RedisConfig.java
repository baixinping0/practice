package com.baixinping.cache.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
public class RedisConfig {
    @Value("${redisLocalhost}")
    String ip;
    @Value("${redisPort}")
    int port;
    @Value("${cacheClass}")
    String cacheClass;

    @Bean
    public JedisPoolConfig getJedisPoolConfig(){
        return new JedisPoolConfig();
    }
    @Bean
    public JedisConnectionFactory getJedisConnectionFactory(){
        JedisConnectionFactory factory = new JedisConnectionFactory(getJedisPoolConfig());
        factory.setHostName(ip);
        factory.setPort(port);
        return factory;
    }
    @Bean
    public RedisTemplate getRedisTemplate(){
        RedisTemplate template = new RedisTemplate();
        template.setConnectionFactory(getJedisConnectionFactory());
        template.setDefaultSerializer(new GenericJackson2JsonRedisSerializer());;
        return template;
    }



}
