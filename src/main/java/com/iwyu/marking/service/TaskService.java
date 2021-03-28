package com.iwyu.marking.service;

import com.iwyu.marking.entity.Task;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Chester
 * @since 2021-02-06
 */
public interface TaskService extends IService<Task> {

    //查找某课程的所有作业
    List<Task> findByOfferId(int offerId);
}
