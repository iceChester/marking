package com.iwyu.marking.service;

import com.iwyu.marking.dto.StudentTaskDTO;
import com.iwyu.marking.entity.StudentTask;
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
public interface StudentTaskService extends IService<StudentTask> {

    //学生获取作业列表
    List<StudentTaskDTO> studentTasks(Integer offerId,String account);

    StudentTask getTask(Integer taskId,String account);
}
