package com.iwyu.marking.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.iwyu.marking.entity.GroupInfo;
import com.iwyu.marking.entity.Groups;

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
}
