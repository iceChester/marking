package com.iwyu.marking.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.iwyu.marking.dto.GroupInfoDTO;
import com.iwyu.marking.entity.GroupInfo;
import com.iwyu.marking.entity.Groups;
import com.iwyu.marking.entity.Student;
import com.iwyu.marking.entity.Timetable;
import com.iwyu.marking.mapper.GroupInfoMapper;
import com.iwyu.marking.mapper.GroupsMapper;
import com.iwyu.marking.mapper.TimetableMapper;
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
    private TimetableMapper timetableMapper;

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
        Integer groupId = null;
        String leaderName = "";
        String groupName = "";
        List<GroupInfo> groupInfoList;
        Groups group = groupsService.checkLeader(offerId,account);
        if(groupInfo==null){
            if(group==null){
                return null;
            }else{
                //是组长
                groupId = group.getGroupId();
                groupInfoList = groupList(offerId,groupId);
                leaderName = group.getLeaderName();
                groupName = group.getGroupName();
            }
        }else {
            //是小组成员
            groupId = groupInfo.getGroupId();
            //获取小组成员
            groupInfoList = groupList(offerId,groupId);
            //获取组长和组名
            Groups groupLeader = groupsService.getById(groupId);
            leaderName = groupLeader.getLeaderName();
            groupName = groupLeader.getGroupName();
        }
        return new GroupInfoDTO(groupId,leaderName,groupName,groupInfoList);
    }

    @Override
    public List<Timetable> noGroupStudent(Integer offerId) {
        List<Timetable> noGroup = new ArrayList<>();
        QueryWrapper<Timetable> query = new QueryWrapper<>();
        query.eq("offer_id",offerId);
        List<Timetable> timetableList = timetableMapper.selectList(query);
        for (Timetable timetable :timetableList) {
            if(groupsService.checkLeader(offerId,timetable.getAccount())==null){
                if(checkMember(offerId,timetable.getAccount())==null){
                    noGroup.add(timetable);
                }
            }
        }
        return noGroup;
    }

    @Override
    public IPage<GroupInfoDTO> findAllGroupByOfferId(Long page, Long size,Integer offerId) {
        Page<GroupInfoDTO> groupInfoDTOIPage = new Page<>(page,size);
        List<GroupInfoDTO> groupInfoDTOList = new ArrayList<>();
        QueryWrapper<Groups> query = new QueryWrapper<>();
        query.eq("offer_id",offerId);
        List<Groups> groupsList = groupsService.list(query);
        int count = groupsList.size();
        //计算当前页第一条数据的下标
        int current = page.intValue();
        int currId = current>1 ? (int) ((current - 1) * size) :0;
        for (int i=0; i<size && i<count - currId;i++){
            QueryWrapper<GroupInfo> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("group_id",groupsList.get(currId+i).getGroupId());
            List<GroupInfo> groupInfoList = infoMapper.selectList(queryWrapper);
            GroupInfoDTO groupInfoDTO = new GroupInfoDTO(groupsList.get(currId+i).getGroupId(),groupsList.get(currId+i).getLeaderName(),groupsList.get(currId+i).getGroupName(),groupInfoList);
            groupInfoDTOList.add(groupInfoDTO);
        }
        groupInfoDTOIPage.setSize(size);
        groupInfoDTOIPage.setCurrent(current);
        groupInfoDTOIPage.setRecords(groupInfoDTOList);
        groupInfoDTOIPage.setTotal(count);
        groupInfoDTOIPage.setPages(count %10 == 0 ? count/10 :count/10+1);
        return groupInfoDTOIPage;
    }
}
