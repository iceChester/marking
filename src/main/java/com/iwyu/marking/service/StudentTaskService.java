package com.iwyu.marking.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.iwyu.marking.dto.StudentTaskDTO;
import com.iwyu.marking.entity.StudentTask;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
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

    //教师获取某一门课程的学生作业
    IPage<StudentTask> studentTaskList(Long page, Long size, Integer taskId);

    //压缩作业文件

    File compressAllTaskFile(Integer taskId) throws Exception;
}
