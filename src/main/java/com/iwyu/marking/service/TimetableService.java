package com.iwyu.marking.service;

import com.iwyu.marking.dto.TeacherCourseDTO;
import com.iwyu.marking.entity.OfferCourses;
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
    List<TeacherCourseDTO> findMyCoures(Integer teacherId);
}
