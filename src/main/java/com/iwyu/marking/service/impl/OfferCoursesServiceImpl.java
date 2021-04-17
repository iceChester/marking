package com.iwyu.marking.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.iwyu.marking.dto.OfferCourseDTO;
import com.iwyu.marking.entity.OfferCourses;
import com.iwyu.marking.entity.Student;
import com.iwyu.marking.entity.Teacher;
import com.iwyu.marking.entity.Timetable;
import com.iwyu.marking.mapper.CourseMapper;
import com.iwyu.marking.mapper.OfferCoursesMapper;
import com.iwyu.marking.mapper.StudentMapper;
import com.iwyu.marking.mapper.TimetableMapper;
import com.iwyu.marking.service.OfferCoursesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.iwyu.marking.service.TeacherService;
import com.iwyu.marking.service.TimetableService;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
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
public class OfferCoursesServiceImpl extends ServiceImpl<OfferCoursesMapper, OfferCourses> implements OfferCoursesService {

    @Resource
    private OfferCoursesMapper offerMapper;

    @Resource
    private StudentMapper studentMapper;

    @Resource
    private TimetableService timetableService;

    @Resource
    private TeacherService teacherService;

    //后期把事务加上,保存开课信息和添加学生要同步
    @Override
    public boolean saveOffer(OfferCourseDTO dto) {
        OfferCourses offerCourses = OfferCourseDTO.saveChange(dto);
        offerMapper.insert(offerCourses);
        Integer offerId = offerCourses.getOfferId();
        List<Timetable> timetableList = new ArrayList<>();
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("class_name",dto.getClasses());
        List<Student> studentList =  studentMapper.selectList(queryWrapper);
        for (Student student :studentList) {
            Timetable timetable = new Timetable();
            timetable.setOfferId(offerId);
            timetable.setAccount(student.getAccount());
            timetable.setStudentName(student.getStudentName());
            timetableList.add(timetable);
        }
        if(timetableService.saveBatch(timetableList)){
            return true;
        }
        return false;
    }

    @Override
    public List<Teacher> findTeacher(Integer offerId) {
        OfferCourses offerCourses = offerMapper.selectById(offerId);
        List<Teacher> teacherList = new ArrayList<>();
        QueryWrapper<Teacher> queryWrapper = new QueryWrapper<>();
        QueryWrapper<Teacher> queryWrapperOne = new QueryWrapper<>();
        QueryWrapper<Teacher> queryWrapperTwo = new QueryWrapper<>();
        queryWrapper.eq("account",offerCourses.getMainTeacher());
        Teacher teacher = teacherService.getOne(queryWrapper);
        teacherList.add(teacher);
        if(!StringUtils.isEmpty(offerCourses.getAssistantTeacherOne())){
            queryWrapperOne.eq("account",offerCourses.getAssistantTeacherOne());
            Teacher assistantOne = teacherService.getOne(queryWrapperOne);
            teacherList.add(assistantOne);
        }
        if(!StringUtils.isEmpty(offerCourses.getAssistantTeacherTwo())){
            queryWrapperTwo.eq("account",offerCourses.getAssistantTeacherTwo());
            Teacher assistantTwo = teacherService.getOne(queryWrapperTwo);
            teacherList.add(assistantTwo);
        }
        return teacherList;
    }
}
