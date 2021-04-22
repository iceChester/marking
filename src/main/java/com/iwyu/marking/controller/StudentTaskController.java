package com.iwyu.marking.controller;


import cn.hutool.core.io.FileTypeUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.iwyu.marking.dto.StudentTaskDTO;
import com.iwyu.marking.entity.Student;
import com.iwyu.marking.entity.StudentTask;
import com.iwyu.marking.entity.Task;
import com.iwyu.marking.service.StudentService;
import com.iwyu.marking.service.StudentTaskService;
import com.iwyu.marking.service.TaskService;

import com.iwyu.marking.service.TimetableService;
import com.iwyu.marking.utils.FileUtil;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Chester
 * @since 2021-02-06
 */
@RestController
@RequestMapping("/studentTask")
public class StudentTaskController {
    private static final String FILE_ZIP=".zip";
    private static final String FILE_RAR=".rar";
    private static final String FILE_XLS=".xls";
    private static final String FILE_XLSX=".xlsx";
    private static final String FILE_JPG=".jpg";
    private static final String FILE_JPEG=".jpeg";
    private static final String FILE_PNG=".png";
    private static final String FILE_BMP=".bmp";
    private static final String FILE_MP4=".mp4";
    private static final String FILE_AVI=".avi";
    private static final String FILE_FLV=".flv";
    private static final String FILE_MP3=".mp3";
    private static final String FILE_PDF=".pdf";
    private static final String FILE_PPT=".ppt";
    private static final String FILE_PPTX=".pptx";
    private static final String FILE_DOC=".doc";
    private static final String FILE_DOCX=".docx";

    @Resource
    private StudentTaskService studentTaskService;

    @Resource
    private TaskService taskService;

    @Resource
    private StudentService studentService;

    @Resource
    private TimetableService timetableService;

    @GetMapping("/checkMyTask")
    public List<StudentTaskDTO> studentTasks(@RequestParam("offerId") Integer offerId, @RequestParam("account") String account) {
        return studentTaskService.studentTasks(offerId, account);
    }


    @PostMapping("/saveScore")
    public boolean saveScore(@RequestBody StudentTask studentTask){
        return studentTaskService.saveOrUpdate(studentTask);
    }

    @GetMapping("/courseTaskList")
    public IPage<StudentTask> courseTaskList(@RequestParam(value = "page") Long page, @RequestParam(value = "size") Long size, @RequestParam("taskId") Integer taskId){
        return studentTaskService.studentTaskList(page,size,taskId);
    }

    @PostMapping("/uploadTask")
    public boolean uploadFile(@RequestParam("file") MultipartFile[] file, @RequestParam("studentTask") String json) {
        StudentTask idAndAccount = JSONUtil.toBean(json,StudentTask.class);
        Integer taskId = idAndAccount.getTaskId();
        String account = idAndAccount.getAccount();
        //添加工具类没有定义的文件类型
        FileTypeUtil.putFileType("D0CF11E0", "doc");
        String rootPath = "D:/TEST/vue/marking/public/studentTask/";
        Integer offerId = taskService.getById(taskId).getOfferId();
        rootPath = rootPath + offerId.toString() + "/" + "个人作业" + "/" + taskId.toString()+ "/" + account + "/";
        File fileDir = new File(rootPath);
        if (!fileDir.exists() && !fileDir.isDirectory()) {
            fileDir.mkdirs();
        }
        try {
            if (file != null && file.length > 0) {
                String imgFileName = "";
                String videoFileName = "";
                String normalFileName = "";
                StudentTask studentTask = studentTaskService.getTask(taskId,account);
                if(studentTask!=null){
                    imgFileName = studentTask.getImgFile();
                    videoFileName = studentTask.getVideoFile();
                    normalFileName = studentTask.getFileName();
                }else {
                    studentTask = new StudentTask();
                    studentTask.setAccount(account);
                    studentTask.setTaskId(taskId);
                }
                for (int i = 0; i < file.length; i++) {
                    try {
                        //判断文件类型是否正确
                        String originalFilename = file[i].getOriginalFilename();
                        String fileType = originalFilename.substring(originalFilename.lastIndexOf("."));
                        if (FILE_XLS.equalsIgnoreCase(fileType) || FILE_XLSX.equalsIgnoreCase(fileType)||
                                FILE_JPG.equalsIgnoreCase(fileType)||FILE_PNG.equalsIgnoreCase(fileType)||
                                FILE_BMP.equalsIgnoreCase(fileType)||FILE_MP4.equalsIgnoreCase(fileType)||
                                FILE_AVI.equalsIgnoreCase(fileType)||FILE_FLV.equalsIgnoreCase(fileType)||
                                FILE_MP3.equalsIgnoreCase(fileType)||FILE_PDF.equalsIgnoreCase(fileType)||
                                FILE_PPT.equalsIgnoreCase(fileType)||FILE_PPTX.equalsIgnoreCase(fileType)||
                                FILE_DOC.equalsIgnoreCase(fileType)||FILE_DOCX.equalsIgnoreCase(fileType)||
                                FILE_ZIP.equalsIgnoreCase(fileType)||FILE_RAR.equalsIgnoreCase(fileType)||
                                FILE_JPEG.equalsIgnoreCase(fileType)) {
                            //         获取源文件前缀
                            String fileNamePrefix = originalFilename.substring(0,originalFilename.lastIndexOf("."));
                            //获取源文件后缀
                            String fileNameSuffix = originalFilename.substring(originalFilename.lastIndexOf("."));
                            //         将源文件前缀之后加上时间戳避免重名
                            String newFileNamePrefix = fileNamePrefix+new Date().getTime();
                            //         得到上传后新文件的文件名
                            String newFileName = newFileNamePrefix+fileNameSuffix;
                            //以原来的名称命名,覆盖掉旧的，这里也可以使用UUID之类的方式命名，这里就没有处理了
                            //         创建一个新的File对象用于存放上传的文件
                            File fileNew = new File(rootPath+File.separator+newFileName);
                            String type;
                            type = FileTypeUtil.getType(file[i].getInputStream());
                            System.out.println(file[i].getInputStream());
                            if(type==null){
                                return false;
                            }
                            //已经通过验证文件后缀初步通过
                            //根据首部字节判断文件类型
                            if(FILE_JPG.contains(type)||FILE_PNG.contains(type)||
                                    FILE_BMP.contains(type)){
                                file[i].transferTo(fileNew);
                                imgFileName = imgFileName + newFileName + ",";
                            }else if(FILE_MP4.contains(type)|| FILE_AVI.contains(type)||
                                    FILE_FLV.contains(type)){
                                file[i].transferTo(fileNew);
                                videoFileName = videoFileName + newFileName + ",";
                            }else if (FILE_XLS.contains(type) || FILE_XLSX.contains(type)||
                                    FILE_MP3.contains(type)||FILE_PDF.contains(type)||
                                    FILE_PPT.contains(type)||FILE_PPTX.contains(type)||
                                    FILE_DOC.contains(type)||FILE_DOCX.contains(type)||
                                    FILE_ZIP.contains(type)||FILE_RAR.contains(type)) {
                                file[i].transferTo(fileNew);
                                normalFileName = normalFileName + newFileName + ",";
                            }else{

                            }
                        }else {
                            return false;
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                studentTask.setFileName(normalFileName);
                studentTask.setImgFile(imgFileName);
                studentTask.setVideoFile(videoFileName);
                studentTask.setSubmitDate(new Date().toString());
                studentTaskService.saveOrUpdate(studentTask);
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //前端可以通过状态码，判断文件是否上传成功
        return false;
    }


    @RequestMapping("/export")
    public void export(HttpServletResponse response, @RequestParam("taskId")Integer taskId){
        QueryWrapper<StudentTask> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("task_id",taskId);
        List<StudentTask> studentTaskList = studentTaskService.list(queryWrapper);
        for (StudentTask studentTask :studentTaskList) {
            QueryWrapper<Student> studentQueryWrapper = new QueryWrapper<>();
            studentQueryWrapper.eq("account",studentTask.getAccount());
            Student student = studentService.getOne(studentQueryWrapper);
            studentTask.setClassName(student.getClassName());
            studentTask.setStudentName(student.getStudentName());
        }
        //导出操作
        FileUtil.exportExcel(studentTaskList,"成绩单","课程成绩",StudentTask.class,"xxx.xls",response);
    }

    //'0~20', '20~40', '40~60', '60~80', '80~100',"未批改"
    @GetMapping("/barData")
    public List<Integer> barData(@RequestParam("taskId")Integer taskId){
        Integer one = 0;
        Integer two = 0;
        Integer three = 0;
        Integer four = 0;
        Integer five = 0;
        Integer six = 0;
        QueryWrapper<StudentTask> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("task_id",taskId);
        List<StudentTask> studentTaskList = studentTaskService.list(queryWrapper);
        for (StudentTask studentTask :studentTaskList) {
            if(studentTask.getScoreTotal()==null){
                six++;
            }
            else if(studentTask.getScoreTotal()<20){
                one++;
            }else if(studentTask.getScoreTotal()<40){
                two++;
            }else if(studentTask.getScoreTotal()<60){
                three++;
            }else if(studentTask.getScoreTotal()<80){
                four++;
            }else if(studentTask.getScoreTotal()<=100){
                five++;
            }
        }
        List<Integer> data = new ArrayList<>();
        data.add(one);
        data.add(two);
        data.add(three);
        data.add(four);
        data.add(five);
        data.add(six);
        return data;
    }
    @GetMapping("/pieData")
    public List<Integer> pieData(@RequestParam("taskId")Integer taskId,@RequestParam("offerId")Integer offerId){
        List<Integer> data = new ArrayList<>();
        Integer accomplish = 0;
        Integer unfinished = 0;
        List<String> accountList = timetableService.getMemberAccount(offerId);
        QueryWrapper<StudentTask> studentTaskQueryWrapper = new QueryWrapper<>();
        studentTaskQueryWrapper.in("account",accountList);
        studentTaskQueryWrapper.eq("task_id",taskId);
        List<StudentTask> studentTaskList = studentTaskService.list(studentTaskQueryWrapper);
        accomplish = studentTaskList.size();
        unfinished = accountList.size() - accomplish;
        data.add(accomplish);
        data.add(unfinished);
        return data;
    }

    @GetMapping("/downloadAllTask")
    public void downloadAllTask(HttpServletResponse response, @RequestParam("taskId")Integer taskId){
        try {
            OutputStream out = response.getOutputStream();
            InputStream inputStream = cn.hutool.core.io.FileUtil.getInputStream(studentTaskService.compressAllTaskFile(taskId));
            response.setContentType("APPLICATION/OCTET-STREAM");
            response.setHeader("Content-Disposition","attachment; filename=task.zip");
            // 循环取出流中的数据
            byte[] b = new byte[100];
            int len;
            while ((len = inputStream.read(b)) !=-1) {
                out.write(b, 0, len);
            }
            inputStream.close();
            out.close();
        } catch (IOException e) {
//            throw new NormalException(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @GetMapping("/downloadOneTask")
    public void downloadOneTask(HttpServletResponse response, @RequestParam("taskId")Integer taskId,@RequestParam("account") String account){
        try {
            OutputStream out = response.getOutputStream();
            InputStream inputStream = cn.hutool.core.io.FileUtil.getInputStream(studentTaskService.compressOneTaskFile(taskId,account));
            response.setContentType("APPLICATION/OCTET-STREAM");
            response.setHeader("Content-Disposition","attachment; filename=task.zip");
            // 循环取出流中的数据
            byte[] b = new byte[100];
            int len;
            while ((len = inputStream.read(b)) !=-1) {
                out.write(b, 0, len);
            }
            inputStream.close();
            out.close();
        } catch (IOException e) {
//            throw new NormalException(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

