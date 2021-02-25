package com.iwyu.marking.service.impl;

import com.iwyu.marking.dto.TeacherCourseDTO;
import com.iwyu.marking.dto.TeacherDTO;
import com.iwyu.marking.entity.OfferCourses;
import com.iwyu.marking.entity.Timetable;
import com.iwyu.marking.mapper.OfferCoursesMapper;
import com.iwyu.marking.mapper.TeacherMapper;
import com.iwyu.marking.mapper.TimetableMapper;
import com.iwyu.marking.service.TimetableService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Chester
 * @since 2021-02-25
 */
@Service
public class TimetableServiceImpl extends ServiceImpl<TimetableMapper, Timetable> implements TimetableService {

    @Autowired
    private TeacherMapper teacherMapper;
    @Autowired
    private OfferCoursesMapper offerCoursesMapper;
    @Autowired
    private TimetableMapper timetableMapper;

    @Override
    public List<TeacherCourseDTO> findMyCoures(Integer teacherId) {
        List<OfferCourses> offerCourses = offerCoursesMapper.findMyCoures(teacherId);
        List<TeacherCourseDTO> teacherCourseDTOS = new ArrayList<>();
        for (OfferCourses courses :offerCourses) {
            System.out.println(courses.getMainTeacherId());
            TeacherCourseDTO teacherCourseDTO = new TeacherCourseDTO();
            teacherCourseDTO.setMainTeacherName(teacherMapper.selectById(courses.getMainTeacherId()).getTeacherName());
            if(courses.getAssistantTeacherOne()!=null){
                teacherCourseDTO.setAssistantOneName(teacherMapper.selectById(courses.getAssistantTeacherOne()).getTeacherName());
            }
            if(courses.getAssistantTeacherTwo()!=null){
                teacherCourseDTO.setAssistantTwoName(teacherMapper.selectById(courses.getAssistantTeacherTwo()).getTeacherName());
            }
            teacherCourseDTO.setCourseName(courses.getCourseName());
            teacherCourseDTO.setClasses(courses.getClasses());
            teacherCourseDTO.setMemberCount(timetableMapper.countMember(courses.getOfferId()));
            teacherCourseDTOS.add(teacherCourseDTO);
        }
        return teacherCourseDTOS;
    }
}
