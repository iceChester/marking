package com.iwyu.marking.service.impl;

/**
 * @ClassName ShiroServiceImpl
 * @Description
 * @Author XiaoMao
 * @Date 2021/4/3 15:19
 * @Version 1.0
 **/

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.iwyu.marking.entity.User;
import com.iwyu.marking.mapper.UserMapper;
import com.iwyu.marking.service.ShiroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class ShiroServiceImpl implements ShiroService {


    @Autowired
    private UserMapper userMapper;
    @Autowired
    private SysTokenRepository sysTokenRepository;

    /**
     * 根据username查找用户
     *
     * @param username
     * @return User
     */
    @Override
    public User findByUsername(String account) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("account",account);
        User user = userMapper.selectOne(queryWrapper);
        return user;
    }

    //12小时后过期
    private final static int EXPIRE = 3600 * 12;

    @Override
    /**
     * 生成一个token
     *@param  [userId]
     *@return Result
     */
    public Map<String, Object> createToken(Integer userId) {
        Map<String, Object> result = new HashMap<>();
        //生成一个token
        String token = TokenGenerator.generateValue();
        //当前时间
        Date now = new Date();
        //过期时间
        Date expireTime = new Date(now.getTime() + EXPIRE * 1000);
        //判断是否生成过token
        SysToken tokenEntity = sysTokenRepository.findByUserId(userId);
        if (tokenEntity == null) {
            tokenEntity = new SysToken();
            tokenEntity.setUserId(userId);
            tokenEntity.setToken(token);
            tokenEntity.setUpdateTime(now);
            tokenEntity.setExpireTime(expireTime);
            //保存token
            sysTokenRepository.save(tokenEntity);
        } else {
            tokenEntity.setToken(token);
            tokenEntity.setUpdateTime(now);
            tokenEntity.setExpireTime(expireTime);
            //更新token
            sysTokenRepository.save(tokenEntity);
        }
        result.put("token", token);
        result.put("expire", EXPIRE);
        return result;
    }

    @Override
    public void logout(String token) {
        SysToken byToken = findByToken(token);
        //生成一个token
        token = TokenGenerator.generateValue();
        //修改token
        SysToken tokenEntity = new SysToken();
        tokenEntity.setUserId(byToken.getUserId());
        tokenEntity.setToken(token);
        sysTokenRepository.save(tokenEntity);
    }

    @Override
    public SysToken findByToken(String accessToken) {
        return sysTokenRepository.findByToken(accessToken);

    }

    @Override
    public User findByUserId(Integer userId) {
        return userRepository.findByUserId(userId);
    }
}