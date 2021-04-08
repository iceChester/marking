package com.iwyu.marking.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.iwyu.marking.dto.GroupInfoDTO;
import com.iwyu.marking.entity.GroupInfo;
import com.iwyu.marking.entity.Groups;
import com.iwyu.marking.mapper.GroupInfoMapper;
import com.iwyu.marking.mapper.GroupsMapper;
import com.iwyu.marking.service.GroupInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.iwyu.marking.service.GroupsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
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
public class GroupInfoServiceImpl extends ServiceImpl<GroupInfoMapper, GroupInfo> implements GroupInfoService {

    @Resource
    private GroupInfoMapper infoMapper;

    @Resource
    GroupsService groupsService;

    @Override
    public GroupInfo checkMember(Integer offerId, String account) {
        QueryWrapper<GroupInfo> groupInfoQueryWrapper = new QueryWrapper<>();
        groupInfoQueryWrapper.eq("offer_id",offerId);
        groupInfoQueryWrapper.eq("member_account",account);
        return infoMapper.selectOne(groupInfoQueryWrapper);
    }

    @Override
    public List<GroupInfo> groupList(Integer offerId, Integer groupId) {
        QueryWrapper<GroupInfo> infoQuery = new QueryWrapper<>();
        infoQuery.eq("offer_id",offerId);
        infoQuery.eq("group_id",groupId);
        return infoMapper.selectList(infoQuery);
    }

    @Override
    public GroupInfoDTO checkStudentGroup(Integer offerId, String account) {
        GroupInfo groupInfo = checkMember(offerId,account);
        String leaderName = "";
        String groupName = "";
        List<GroupInfo> groupInfoList;
        if(groupInfo==null){
            Groups group = groupsService.checkLeader(offerId,account);
            if(group==null){
                return null;
            }else{
                //是组长
                groupInfoList = groupList(offerId,group.getGroupId());
                leaderName = group.getLeaderName();
                groupName = group.getGroupName();
            }
        }else {
            //是小组成员
            Groups group = groupsService.checkLeader(offerId,account);
            groupInfoList = groupList(offerId,group.getGroupId());
            leaderName = group.getLeaderName();
            groupName = group.getGroupName();
        }
        return new GroupInfoDTO(leaderName,groupName,groupInfoList);
    }
}
