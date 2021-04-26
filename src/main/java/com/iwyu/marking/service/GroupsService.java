package com.iwyu.marking.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.iwyu.marking.entity.GroupInfo;
import com.iwyu.marking.entity.Groups;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Chester
 * @since 2021-04-06
 */
public interface GroupsService extends IService<Groups> {

    //查询是否是组长
    Groups checkLeader(Integer offerId, String account);

    //获取某一门课程的所有小组（根据课程id获取）
    List<Groups> CourseGroupList(Integer offerId);
}
