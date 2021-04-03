package com.iwyu.marking.config;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @ClassName RedisConfig
 * @Description Redis 配置
 * @Author XiaoMao
 * @Date 2021/4/1 21:14
 * @Version 1.0
 **/
@Configuration
@PropertySource(value = { "classpath:redis.properties" })
public class RedisConfig {

    /**
     * 日志管理
     */
    private Logger log = LoggerFactory.getLogger(RedisConfig.class);

    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.port}")
    private int port;

    /**
     *
     * @Title: getJedisPool
     * @Description: 获取jedisPool
     * @return
     */
    @Bean
    public JedisPool getJedisPool() {
        log.info("==>初始化jedis连接池");
        JedisPoolConfig config = new JedisPoolConfig();
        JedisPool pool = new JedisPool(config, host, port);
        return pool;
    }
}

