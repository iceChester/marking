package com.iwyu.marking.service.impl;

import com.iwyu.marking.entity.Group;
import com.iwyu.marking.mapper.GroupMapper;
import com.iwyu.marking.service.GroupService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Chester
 * @since 2021-04-06
 */
@Service
public class GroupServiceImpl extends ServiceImpl<GroupMapper, Group> implements GroupService {

}
