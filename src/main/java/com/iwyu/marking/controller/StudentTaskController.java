package com.iwyu.marking.controller;


import cn.hutool.core.io.FileTypeUtil;
import com.iwyu.marking.dto.StudentTaskDTO;
import com.iwyu.marking.entity.StudentTask;
import com.iwyu.marking.service.StudentTaskService;
import com.iwyu.marking.service.TaskService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
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

    @GetMapping("/checkMyTask")
    public List<StudentTaskDTO> studentTasks(@RequestParam("offerId") Integer offerId, @RequestParam("account") String account) {
        return studentTaskService.studentTasks(offerId, account);
    }

    @PostMapping("/uploadTask")
    public boolean uploadFile(@RequestParam("file") MultipartFile[] file, @RequestParam("taskId") Integer taskId,@RequestParam("account") String account) {
        //添加工具类没有定义的文件类型
        FileTypeUtil.putFileType("D0CF11E0", "doc");
        String rootPath = "D:/attachment/";
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
                                FILE_ZIP.equalsIgnoreCase(fileType)||FILE_RAR.equalsIgnoreCase(fileType)) {
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

}

