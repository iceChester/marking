package com.iwyu.marking.dto;/**
 * Created by Chester on 25/2/2021.
 */

import com.iwyu.marking.entity.OfferCourses;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName OfferCourseDTO
 * @Description
 * @Author XiaoMao
 * @Date 25/2/2021 上午10:16
 * @Version 1.0
 **/
@Data
@ApiModel
public class OfferCourseDTO implements Serializable {

    private Integer offerId;
    private Integer courseId;
    private String courseName;
    private Integer deleted;

    private String mainTeacher;

    private String assistantTeacherOne;

    private String assistantTeacherTwo;

    @ApiModelProperty(value = "班级，用逗号隔开")
    private List<String> classes;
    static public OfferCourses saveChange(OfferCourseDTO dto){
        OfferCourses courses = new OfferCourses();
        courses.setMainTeacher(dto.getMainTeacher());
        courses.setAssistantTeacherOne(dto.getAssistantTeacherOne());
        courses.setAssistantTeacherTwo(dto.getAssistantTeacherTwo());
        courses.setCourseId(dto.getCourseId());
        courses.setCourseName(dto.getCourseName());
        String temp = "";
        for (int i=0;i<dto.getClasses().size();i++) {
            temp = temp + dto.getClasses().get(i);
            if(i!=dto.getClasses().size()-1){
                temp = temp +",";
            }
        }
        courses.setClasses(temp);
        return courses;

    }
}
