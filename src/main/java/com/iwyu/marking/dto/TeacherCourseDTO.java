package com.iwyu.marking.dto;/**
 * Created by Chester on 25/2/2021.
 */

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName TeacherCourseDTO
 * @Description
 * @Author XiaoMao
 * @Date 25/2/2021 下午9:11
 * @Version 1.0
 **/
@Data
public class TeacherCourseDTO implements Serializable {
    private String courseName;
    private String mainTeacherName;
    private String assistantOneName;
    private String assistantTwoName;
    private String classes;
    private Integer memberCount;
}
