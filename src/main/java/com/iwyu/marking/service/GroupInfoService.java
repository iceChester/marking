package com.iwyu.marking.service;

import com.iwyu.marking.dto.GroupInfoDTO;
import com.iwyu.marking.entity.GroupInfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Chester
 * @since 2021-04-06
 */
public interface GroupInfoService extends IService<GroupInfo> {
    //检查是否有小组
    GroupInfoDTO checkStudentGroup (Integer offerId , String account);
    //检查是否是小组成员
    GroupInfo checkMember(Integer offerId , String account);
    //获取某课程某小组的小组成员（通过开课id和小组id）
    List<GroupInfo> groupList(Integer offerId ,Integer groupId);
}
