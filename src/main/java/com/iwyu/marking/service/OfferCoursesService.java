package com.iwyu.marking.service;

import com.iwyu.marking.dto.OfferCourseDTO;
import com.iwyu.marking.entity.OfferCourses;
import com.baomidou.mybatisplus.extension.service.IService;
import com.iwyu.marking.entity.Teacher;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Chester
 * @since 2021-02-06
 */
public interface OfferCoursesService extends IService<OfferCourses> {
    //开课
    boolean saveOffer(OfferCourseDTO dto);
    //查询课程的所有老师
    List<Teacher> findTeacher(Integer offerId);
    //查询课程信息
    OfferCourses findCourse(Integer offerId);
}
