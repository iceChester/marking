package com.iwyu.marking.dto;/**
 * Created by Chester on 24/2/2021.
 */

import com.iwyu.marking.entity.Teacher;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.*;

/**
 * @ClassName teacherDTO
 * @Description
 * @Author XiaoMao
 * @Date 24/2/2021 下午8:25
 * @Version 1.0
 **/
@Data
public class TeacherDTO {

    private Integer teacherId;

    private String teacherName;

    private String account;

    static public List<TeacherDTO> change(List<Teacher> teachers){
        List<TeacherDTO> dtos = new ArrayList<>();
        for (int i=0; i<teachers.size();i++) {
            TeacherDTO teacherDTO = new TeacherDTO();
            teacherDTO.setTeacherName(teachers.get(i).getTeacherName());
            teacherDTO.setTeacherId(teachers.get(i).getTeacherId());
            teacherDTO.setAccount(teachers.get(i).getAccount());
            dtos.add(teacherDTO);
        }
        return dtos;
    }
}
