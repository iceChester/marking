package com.iwyu.marking.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.iwyu.marking.dto.StudentTaskDTO;
import com.iwyu.marking.entity.Student;
import com.iwyu.marking.entity.StudentTask;
import com.iwyu.marking.entity.Task;
import com.iwyu.marking.mapper.StudentTaskMapper;
import com.iwyu.marking.service.StudentService;
import com.iwyu.marking.service.StudentTaskService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.iwyu.marking.service.TaskService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
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
public class StudentTaskServiceImpl extends ServiceImpl<StudentTaskMapper, StudentTask> implements StudentTaskService {

    @Resource
    private StudentTaskMapper studentTaskMapper;

    @Resource
    private TaskService taskService;

    @Resource
    private StudentService studentService;


    @Override
    public List<StudentTaskDTO> studentTasks(Integer offerId, String account) {
        List<StudentTaskDTO> studentTaskDTOList = new ArrayList<>();
        List<Task> taskList = taskService.findByOfferId(offerId);
        if(taskList!=null){
            for (Task task :taskList) {
                QueryWrapper<StudentTask> query = new QueryWrapper<>();
                query.eq("task_id",task.getTaskId());
                query.eq("account",account);
                StudentTask studentTask = studentTaskMapper.selectOne(query);
                StudentTaskDTO studentTaskDTO = StudentTaskDTO.setStudentTaskDTO(task,studentTask);
                studentTaskDTOList.add(studentTaskDTO);
            }
        }

        return studentTaskDTOList;
    }

    @Override
    public StudentTask getTask(Integer taskId, String account) {
        QueryWrapper<StudentTask> query = new QueryWrapper<>();
        query.eq("task_id",taskId);
        query.eq("account",account);
        return studentTaskMapper.selectOne(query);
    }

    @Override
    public IPage<StudentTask> studentTaskList(Long page, Long size, Integer taskId) {
        IPage<StudentTask> studentTaskPage = new Page<>(page,size);
        QueryWrapper<StudentTask> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("task_id",taskId);
        studentTaskPage = studentTaskMapper.selectPage(studentTaskPage,queryWrapper);
        for (StudentTask studentTask :studentTaskPage.getRecords()) {
            QueryWrapper<Student> query = new QueryWrapper<>();
            query.eq("account",studentTask.getAccount());
            Student student = studentService.getOne(query);
            studentTask.setStudentName(student.getStudentName());
            studentTask.setClassName(student.getClassName());
        }
        return studentTaskPage;
    }
}
