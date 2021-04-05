package com.iwyu.marking.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.iwyu.marking.dto.TeacherCourseDTO;
import com.iwyu.marking.entity.OfferCourses;
import com.iwyu.marking.entity.Student;
import com.iwyu.marking.entity.Timetable;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Chester
 * @since 2021-02-25
 */
public interface TimetableService extends IService<Timetable> {

    //查询教师课程
    List<TeacherCourseDTO> findMyCourse(Integer teacherId);
    //查询课程学生名单
    IPage<Student> studentInfo(Long page, Long size, Integer offerId);
}
