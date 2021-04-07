package com.iwyu.marking.service;

import com.iwyu.marking.dto.GroupInfoDTO;
import com.iwyu.marking.entity.GroupInfo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Chester
 * @since 2021-04-06
 */
public interface GroupInfoService extends IService<GroupInfo> {
    GroupInfoDTO checkStudentGroup (Integer offerId , String account);
}
