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

    @ApiModelProperty(value = "主要授课教师")
    private Integer mainTeacherId;

    @ApiModelProperty(value = "助教1")
    private Integer assistantTeacherOne;

    @ApiModelProperty(value = "助教2")
    private Integer assistantTeacherTwo;

    @ApiModelProperty(value = "班级，用逗号隔开")
    private List<String> classes;
    static public OfferCourses saveChange(OfferCourseDTO dto){
        OfferCourses courses = new OfferCourses();
        courses.setMainTeacherId(dto.getMainTeacherId());
        courses.setAssistantTeacherOne(dto.getAssistantTeacherOne());
        courses.setAssistantTeacherTwo(dto.getAssistantTeacherTwo());
        courses.setCourseId(dto.getCourseId());
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
