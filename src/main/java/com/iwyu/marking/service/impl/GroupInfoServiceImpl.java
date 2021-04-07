package com.iwyu.marking.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.iwyu.marking.dto.GroupInfoDTO;
import com.iwyu.marking.entity.Group;
import com.iwyu.marking.entity.GroupInfo;
import com.iwyu.marking.mapper.GroupInfoMapper;
import com.iwyu.marking.mapper.GroupMapper;
import com.iwyu.marking.service.GroupInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
    private GroupMapper groupMapper;

    @Override
    public GroupInfoDTO checkStudentGroup(Integer offerId, String account) {
        GroupInfoDTO groupInfoDTO = new GroupInfoDTO();
        QueryWrapper<GroupInfo> groupInfoQueryWrapper = new QueryWrapper<>();
        groupInfoQueryWrapper.eq("offer_id",offerId);
        groupInfoQueryWrapper.eq("member_account",account);
        GroupInfo groupInfo = infoMapper.selectOne(groupInfoQueryWrapper);
        if(groupInfo==null){
            QueryWrapper<Group> groupQueryWrapper = new QueryWrapper<>();
            groupQueryWrapper.eq("offer_id",offerId);
            groupQueryWrapper.eq("leader_account",account);
            Group group = groupMapper.selectOne(groupQueryWrapper);
            if(group==null){
                return null;
            }else{
                groupInfoDTO.setGroupName(group.getGroupName());
                groupInfoDTO.setLeaderName(group.getLeaderName());
                QueryWrapper<GroupInfo> infoQuery = new QueryWrapper<>();
                infoQuery.eq("offer_id",offerId);
                infoQuery.eq("group_id",group.getGroupId());
                List<GroupInfo> groupInfoList = infoMapper.selectList(infoQuery);
                List<String> memberAccount = new ArrayList<>();
                List<String> memberName = new ArrayList<>();
                for (GroupInfo info :groupInfoList) {
                    memberAccount.add(info.getMemberAccount());
                    memberName.add(info.getMemberName());
                }
                groupInfoDTO.setMemberAccount(memberAccount);
                groupInfoDTO.setMemberName(memberName);
            }
        }else {
            QueryWrapper<Group> groupQuery = new QueryWrapper<>();
            groupQuery.eq("offer_id",offerId);
            groupQuery.eq("group_id",groupInfo.getGroupId());
            Group group = groupMapper.selectOne(groupQuery);
            groupInfoDTO.setGroupName(group.getGroupName());
            groupInfoDTO.setLeaderName(group.getLeaderName());
            QueryWrapper<GroupInfo> InfoWrapper = new QueryWrapper<>();
            InfoWrapper.eq("offer_id",offerId);
            InfoWrapper.eq("group_id",groupInfo.getGroupId());
            List<GroupInfo> groupInfoList = infoMapper.selectList(InfoWrapper);
            List<String> memberAccount = new ArrayList<>();
            List<String> memberName = new ArrayList<>();
            for (GroupInfo info :groupInfoList) {
                memberAccount.add(info.getMemberAccount());
                memberName.add(info.getMemberName());
            }
            groupInfoDTO.setMemberAccount(memberAccount);
            groupInfoDTO.setMemberName(memberName);
        }
        return groupInfoDTO;
    }
}
