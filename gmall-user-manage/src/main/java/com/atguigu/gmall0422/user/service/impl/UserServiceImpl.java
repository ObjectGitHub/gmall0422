package com.atguigu.gmall0422.user.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.atguigu.gmall0422.bean.UserAddress;
import com.atguigu.gmall0422.bean.UserInfo;
import com.atguigu.gmall0422.config.RedisUtil;
import com.atguigu.gmall0422.service.UserService;
import com.atguigu.gmall0422.user.mapper.UserAddressMapper;
import com.atguigu.gmall0422.user.mapper.UserInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.List;
@Service
public class UserServiceImpl implements UserService {
    public String userKey_prefix="user:";
    public String userinfoKey_suffix=":info";
    public int userKey_timeOut=60*60*24;


    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private UserAddressMapper userAddressMapper;
    @Autowired
    private RedisUtil redisUtil;

    @Override
    public List<UserInfo> findAll() {
        return userInfoMapper.selectAll();
    }

    @Override
    public List<UserInfo> findByUserInfo(UserInfo userInfo) {
        return null;
    }

    @Override
    public List<UserInfo> findByloginName(String loginName) {
        return null;
    }

    @Override
    public void addUser(UserInfo userInfo) {

    }

    @Override
    public void updateUser(UserInfo userInfo) {

    }

    @Override
    public void delUser(UserInfo userInfo) {

    }

    @Override
    public List<UserAddress> findUserAddressByUserId(String id) {
        UserAddress userAddress = new UserAddress();
        userAddress.setUserId(id);
        return userAddressMapper.select(userAddress);
    }

    @Override
    public UserInfo login(UserInfo userInfo) {
        String password = DigestUtils.md5DigestAsHex(userInfo.getPasswd().getBytes());
        userInfo.setPasswd(password);
        UserInfo Info = userInfoMapper.selectOne(userInfo);
        Jedis jedis = null;
        if(Info !=null){
            try {
                //获取redis客户端
                jedis = redisUtil.getJedis();
                //设置key
                String userKey = userKey_prefix + Info.getId() + userinfoKey_suffix;
                //存入redis 并设置过期时间
                jedis.setex(userKey,userKey_timeOut,JSON.toJSONString(Info));

            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                if (jedis != null) {
                    //关闭
                    jedis.close();
                }
            }
            return Info;
        }

        return null;
    }

    @Override
    public UserInfo verify(String userId) {
        Jedis jedis = null;
        try {
            jedis = redisUtil.getJedis();
            String userKey = userKey_prefix + userId + userinfoKey_suffix;
            //获取并判断
            String userJson = jedis.get(userKey);
            if (!StringUtils.isEmpty(userJson)) {
                jedis.expire(userKey, userKey_timeOut);
                //给用户设置key的延长过期时间
                UserInfo userInfo = JSON.parseObject(userJson, UserInfo.class);
                //返回用户对象
                return userInfo;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return null;
    }
}
