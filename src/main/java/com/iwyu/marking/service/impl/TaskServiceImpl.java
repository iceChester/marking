package com.iwyu.marking.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.iwyu.marking.entity.StudentTask;
import com.iwyu.marking.entity.Task;
import com.iwyu.marking.mapper.TaskMapper;
import com.iwyu.marking.service.StudentService;
import com.iwyu.marking.service.StudentTaskService;
import com.iwyu.marking.service.TaskService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Chester
 * @since 2021-02-06
 */
@Service
public class TaskServiceImpl extends ServiceImpl<TaskMapper, Task> implements TaskService {

    @Resource
    protected TaskMapper taskMapper;

    @Resource
    protected StudentTaskService studentTaskService;

    @Override
    public List<Task> findByOfferId(int offerId) {
        return taskMapper.findByOfferId(offerId);
    }

    @Override
    public List<Task> findCollectingTask(int offerId,int taskType) {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        QueryWrapper<Task> taskQueryWrapper = new QueryWrapper<>();
        taskQueryWrapper.ge("deadline",sdf.format(date));
        taskQueryWrapper.eq("task_type",taskType);
        taskQueryWrapper.eq("offer_id",offerId);
        return taskMapper.selectList(taskQueryWrapper);
    }

    @Override
    public List<Task> findDeadlineTask(int offerId,int taskType) {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        QueryWrapper<Task> taskQueryWrapper = new QueryWrapper<>();
        taskQueryWrapper.eq("task_type",taskType);
        taskQueryWrapper.lt("deadline",sdf.format(date));
        taskQueryWrapper.eq("offer_id",offerId);
        return taskMapper.selectList(taskQueryWrapper);
    }

    @Override
    public List<Task> studentCollectingTask(int offerId, String account,int taskType) {
        List<Task> taskList = this.findCollectingTask(offerId,taskType);
        List<Task> collectingTaskList = new ArrayList<>();
        for (Task task :taskList) {
            QueryWrapper<StudentTask> studentTaskQueryWrapper = new QueryWrapper<>();
            studentTaskQueryWrapper.eq("account",account);
            studentTaskQueryWrapper.eq("task_id",task.getTaskId());
            StudentTask studentTask = studentTaskService.getOne(studentTaskQueryWrapper);
            if(studentTask==null){
                collectingTaskList.add(task);
            }
        }
        return collectingTaskList;
    }

    @Override
    public List<Task> studentAccomplishTask(int offerId, String account,int taskType) {
        List<Task> taskList = this.findCollectingTask(offerId,taskType);
        List<Task> accomplishTaskList = new ArrayList<>();
        for (Task task :taskList) {
            QueryWrapper<StudentTask> studentTaskQueryWrapper = new QueryWrapper<>();
            studentTaskQueryWrapper.eq("account",account);
            studentTaskQueryWrapper.eq("task_id",task.getTaskId());
            StudentTask studentTask = studentTaskService.getOne(studentTaskQueryWrapper);
            if(studentTask!=null){
                accomplishTaskList.add(task);
            }
        }
        return accomplishTaskList;
    }

    @Override
    public List<Task> studentOverdueTask(int offerId, String account,int taskType) {
        List<Task> taskList = this.findDeadlineTask(offerId,taskType);
        List<Task> overdueTaskList = new ArrayList<>();
        for (Task task :taskList) {
            QueryWrapper<StudentTask> studentTaskQueryWrapper = new QueryWrapper<>();
            studentTaskQueryWrapper.eq("account",account);
            studentTaskQueryWrapper.eq("task_id",task.getTaskId());
            StudentTask studentTask = studentTaskService.getOne(studentTaskQueryWrapper);
            if(studentTask==null){
                overdueTaskList.add(task);
            }
        }
        return overdueTaskList;
    }
}
