package com.atguigu.gmall0422.config;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisUtil {

    //创建一个JedisPool连接池，在连接池中获取Jedis
    private JedisPool jedisPool;

    //初始化JedisPool
    public void intiJedis(String host,int port,int timeOut){
        //配置连接池的参数
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        // 设置连接池最大核心数
        jedisPoolConfig.setMaxTotal(200);
        //设置等待时间
        jedisPoolConfig.setMaxWaitMillis(10 * 1000);
        //最少剩余数
        jedisPoolConfig.setMinIdle(10);
        //排队等待
        jedisPoolConfig.setBlockWhenExhausted(true);
        //设置当用户获取到jedis 时，做自检看当前获取到的jedis 是否可以使用！
        jedisPoolConfig.setTestOnBorrow(true);
        jedisPool = new JedisPool(jedisPoolConfig, host, port, timeOut);
    }

    //获取jedis
    public Jedis getJedis(){
        return jedisPool.getResource();
    }
}
