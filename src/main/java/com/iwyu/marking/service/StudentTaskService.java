package com.iwyu.marking.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.iwyu.marking.dto.GroupTasksDTO;
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
    List<StudentTaskDTO> studentTasks(Integer offerId,String account,int taskType);


    StudentTask getTask(Integer taskId,String account);

    //教师获取某一门课程的学生作业
    IPage<StudentTask> studentTaskList(Long page, Long size, Integer taskId);

    List<GroupTasksDTO> groupTaskList(Integer taskId,Integer offerId);

    //压缩全部作业文件并获取作业压缩文件
    File compressAllTaskFile(Integer taskId,Integer groupId) throws Exception;
    //压缩个人作业并获取
    File compressOneTaskFile(Integer taskId,String account,Integer groupId) throws Exception;
}
