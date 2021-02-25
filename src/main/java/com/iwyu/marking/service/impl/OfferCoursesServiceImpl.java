package com.iwyu.marking.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.iwyu.marking.dto.OfferCourseDTO;
import com.iwyu.marking.entity.OfferCourses;
import com.iwyu.marking.entity.Student;
import com.iwyu.marking.mapper.CourseMapper;
import com.iwyu.marking.mapper.OfferCoursesMapper;
import com.iwyu.marking.mapper.StudentMapper;
import com.iwyu.marking.service.OfferCoursesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
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
public class OfferCoursesServiceImpl extends ServiceImpl<OfferCoursesMapper, OfferCourses> implements OfferCoursesService {

    @Autowired
    private OfferCoursesMapper offerMapper;

    @Autowired
    private StudentMapper studentMapper;

    //后期把事务加上
    @Override
    public boolean saveOffer(OfferCourseDTO dto) {
//        QueryWrapper<Student>
        return false;
    }
}
