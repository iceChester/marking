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

    //查询某一门课程正在收集的作业
    List<Task> findCollectingTask(int offerId,int taskType);
    List<Task> findDeadlineTask(int offerId,int taskType);
    //查询学生未截至且未完成的作业
    List<Task> studentCollectingTask(int offerId,String account,int taskType);
    //查询学生未截至但已完成的作业
    List<Task> studentAccomplishTask(int offerId,String account,int taskType);
    //查询学生已截至但未完成的作业
    List<Task> studentOverdueTask(int offerId,String account,int taskType);
}
