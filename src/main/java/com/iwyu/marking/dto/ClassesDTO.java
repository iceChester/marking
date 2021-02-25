package com.iwyu.marking.dto;/**
 * Created by Chester on 25/2/2021.
 */

import com.iwyu.marking.entity.Student;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName ClassesDTO
 * @Description
 * @Author XiaoMao
 * @Date 25/2/2021 下午3:30
 * @Version 1.0
 **/
@Data
public class ClassesDTO {
    private Integer key;
    private String className;
    static public List<ClassesDTO> chang(List<Student> students){
        List<ClassesDTO> classesDTOS = new ArrayList<>();
        for (Student student :students) {
            ClassesDTO classesDTO = new ClassesDTO();
            classesDTO.setKey(student.getStudentId());
            classesDTO.setClassName(student.getClassName());
            classesDTOS.add(classesDTO);
        }
        return classesDTOS;
    }
}
