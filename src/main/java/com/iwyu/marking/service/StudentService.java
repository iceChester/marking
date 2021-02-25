package com.iwyu.marking.service;

import com.iwyu.marking.dto.ClassesDTO;
import com.iwyu.marking.entity.Student;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Chester
 * @since 2021-02-06
 */
public interface StudentService extends IService<Student> {

    List<ClassesDTO> findClassName(String name);
}
