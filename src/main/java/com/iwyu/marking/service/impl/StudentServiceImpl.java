package com.iwyu.marking.service.impl;

import com.iwyu.marking.dto.ClassesDTO;
import com.iwyu.marking.entity.Student;
import com.iwyu.marking.mapper.StudentMapper;
import com.iwyu.marking.service.StudentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {

    @Autowired
    StudentMapper studentMapper;

    @Override
    public List<ClassesDTO> findClassName(String name) {
        name = "%"+name+"%";
        List<Student> students =studentMapper.getClasses(name);
        List<ClassesDTO> classesDTOS = ClassesDTO.chang(students);
        return classesDTOS;
    }
}
