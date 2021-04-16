package com.iwyu.marking.dto;

import com.iwyu.marking.entity.StudentTask;
import com.iwyu.marking.entity.Task;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName StudentTaskDTO
 * @Description
 * @Author XiaoMao
 * @Date 2021/4/11 10:39
 * @Version 1.0
 **/
@Data
public class StudentTaskDTO implements Serializable {
    @ApiModelProperty(value = "作业id")
    private Integer taskId;

    @ApiModelProperty(value = "标题")
    private String title;

    @ApiModelProperty(value = " 具体内容，细节")
    private String detail;

    @ApiModelProperty(value = "截至时间")
    private String deadline;

    @ApiModelProperty(value = " 作业文件名")
    private String fileName;

    @ApiModelProperty(value = " 提交日期")
    private String submitDate;

    @ApiModelProperty(value = "分数一")
    private Float scoreOne;

    @ApiModelProperty(value = " 分数二")
    private Float scoreTwo;

    @ApiModelProperty(value = " 分数三")
    private Float scoreThree;

    @ApiModelProperty(value = " 总分")
    private Float scoreTotal;

    public static StudentTaskDTO setStudentTaskDTO(Task task, StudentTask studentTask){
        StudentTaskDTO studentTaskDTO = new StudentTaskDTO();
        studentTaskDTO.setTaskId(task.getTaskId());
        studentTaskDTO.setTitle(task.getTitle());
        studentTaskDTO.setDeadline(task.getDeadline());
        studentTaskDTO.setDetail(task.getDetail());
        if(studentTask!=null){
            studentTaskDTO.setFileName(studentTask.getFileName());
            studentTaskDTO.setScoreOne(studentTask.getScoreOne());
            studentTaskDTO.setScoreTwo(studentTask.getScoreTwo());
            studentTaskDTO.setScoreThree(studentTask.getScoreThree());
            studentTaskDTO.setScoreTotal(studentTask.getScoreTotal());
            studentTaskDTO.setSubmitDate(studentTask.getSubmitDate());
        }else{
            //没有交作业
        }
        return  studentTaskDTO;
    }
}
