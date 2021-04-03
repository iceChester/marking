package com.iwyu.marking.service;

import com.iwyu.marking.entity.SysToken;
import com.iwyu.marking.entity.User;

import java.util.Map;

/**
 * @InterfaceName ShiroService
 * @Description
 * @Author XiaoMao
 * @Date 2021/4/3 15:19
 * @Version 1.0
 **/

public interface ShiroService {
    User findByAccount(String account);
    Map<String, Object> createToken(Integer userId);
    void logout(String token);
    SysToken findByToken(String accessToken);
    User findByUserId(Integer userId);
}
