package com.iwyu.marking.service.impl;

import com.iwyu.marking.entity.Student;
import com.iwyu.marking.mapper.StudentMapper;
import com.iwyu.marking.service.StudentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
