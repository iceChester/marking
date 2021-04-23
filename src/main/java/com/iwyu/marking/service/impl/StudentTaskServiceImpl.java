package com.iwyu.marking.service.impl;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.ZipUtil;
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
import java.io.File;
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
    public List<StudentTaskDTO> studentTasks(Integer offerId, String account,int taskType) {
        List<StudentTaskDTO> studentTaskDTOList = new ArrayList<>();
        List<Task> taskList = taskService.findDeadlineTask(offerId,taskType);
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

    @Override
    public File compressAllTaskFile(Integer taskId,Integer groupId) throws Exception {
        String rootPath = "D:/TEST/vue/marking/public/studentTask/";
        Task task = taskService.getById(taskId);
        Integer offerId = task.getOfferId();
        String zipName = task.getTitle() + ".zip";
        String zipPath = rootPath;
        if(groupId!=-1){
            zipPath =  zipPath + offerId.toString() + "/小组作业/"+taskId.toString();
            rootPath = rootPath + offerId.toString() + "/小组作业/压缩作业/" + taskId.toString()+"/ALL/";
        }else {
            zipPath = zipPath + offerId.toString() + "/个人作业/"+taskId.toString();
            rootPath = rootPath + offerId.toString() + "/个人作业/压缩作业/" + taskId.toString()+"/ALL/";
        }

        File fileDir = new File(rootPath);
        if (!fileDir.exists() && !fileDir.isDirectory()) {
            fileDir.mkdirs();
            //将aaa目录以及其目录下的所有文件目录打包到d:/bbb/目录下的ccc.zip文件中
//            ZipUtil.zip("d:/aaa", "d:/bbb/ccc.zip", true);
            File file = ZipUtil.zip(zipPath, rootPath+zipName, true);
            return file;
        }else {
            return FileUtil.file(rootPath+zipName);
        }
    }

    @Override
    public File compressOneTaskFile(Integer taskId, String account,Integer groupId) throws Exception {
        String rootPath = "D:/TEST/vue/marking/public/studentTask/";
        Task task = taskService.getById(taskId);
        Integer offerId = task.getOfferId();
        String zipName = account + ".zip";
        String zipPath = rootPath;
        //源路径
        if(groupId!=-1){
            zipPath = zipPath + offerId.toString() + "/小组作业/"+taskId.toString()+"/"+groupId;
            //压缩目的地
            rootPath = rootPath + offerId.toString() + "/小组作业/压缩作业/" + taskId.toString()+"/Single/";
        }else {
            zipPath = zipPath + offerId.toString() + "/个人作业/"+taskId.toString()+"/"+account;
            //压缩目的地
            rootPath = rootPath + offerId.toString() + "/个人作业/压缩作业/" + taskId.toString()+"/Single/";
        }

        File fileDir = new File(rootPath);
        File fileExist = new File(rootPath+zipName);
        if (!fileDir.exists() && !fileDir.isDirectory()) {
            fileDir.mkdirs();
            //将aaa目录以及其目录下的所有文件目录打包到d:/bbb/目录下的ccc.zip文件中
//            ZipUtil.zip("d:/aaa", "d:/bbb/ccc.zip", true);
            File file = ZipUtil.zip(zipPath, rootPath+zipName, true);
            return file;
        }else if(!fileExist.exists()){
            File file = ZipUtil.zip(zipPath, rootPath+zipName, true);
            return file;
        }else{
            return FileUtil.file(rootPath+zipName);
        }
    }
}
