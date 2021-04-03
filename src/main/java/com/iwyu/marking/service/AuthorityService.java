package com.iwyu.marking.service;

import com.iwyu.marking.entity.Authority;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Chester
 * @since 2021-04-03
 */
public interface AuthorityService extends IService<Authority> {
    List<Authority>  findByRole(String role);
}
