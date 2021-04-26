package com.iwyu.marking.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.iwyu.marking.entity.Groups;
import com.iwyu.marking.mapper.GroupsMapper;
import com.iwyu.marking.service.GroupsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Chester
 * @since 2021-04-06
 */
@Service
public class GroupsServiceImpl extends ServiceImpl<GroupsMapper, Groups> implements GroupsService {

    @Resource
    private GroupsMapper groupsMapper;

    @Override
    public Groups checkLeader(Integer offerId, String account) {
        QueryWrapper<Groups> groupsQueryWrapper = new QueryWrapper<>();
        groupsQueryWrapper.eq("offer_id",offerId);
        groupsQueryWrapper.eq("leader_account",account);
        return groupsMapper.selectOne(groupsQueryWrapper);
    }

    @Override
    public List<Groups> CourseGroupList(Integer offerId) {
        QueryWrapper<Groups> groupsQueryWrapper = new QueryWrapper<>();
        groupsQueryWrapper.eq("offer_id",offerId);
        List<Groups> groupsList = groupsMapper.selectList(groupsQueryWrapper);
        return groupsList;
    }
}
