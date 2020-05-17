package com.atguigu.gmall0422.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//做redis配置类
@Configuration
public class RedisConfig {

    //获取host、port、timeOut等参数
    @Value("${spring.redis.host:disabled}")
    private String host;

    @Value("${spring.redis.port:0}")
    private int port;

    @Value("${spring.redis.timeOut:10000}")
    private int timeOut;

    /**
     * @Bean 相当于<bean><bean/>
     * @return
     */
    @Bean
    public RedisUtil getRedisUtil(){
        if("disabled".equals(host)){
            return null;
        }
        RedisUtil redisUtil = new RedisUtil();
        redisUtil.intiJedis(host,port,timeOut);
        return redisUtil;
    }

}
