package com.iwyu.marking.service.impl;

import com.iwyu.marking.entity.User;
import com.iwyu.marking.mapper.UserMapper;
import com.iwyu.marking.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Chester
 * @since 2021-02-06
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
