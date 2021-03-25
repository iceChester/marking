package com.iwyu.marking.controller;


import com.iwyu.marking.dto.TeacherCourseDTO;
import com.iwyu.marking.entity.Student;
import com.iwyu.marking.mapper.TimetableMapper;
import com.iwyu.marking.service.TimetableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Chester
 * @since 2021-02-25
 */
@RestController
@RequestMapping("/timetable")
public class TimetableController {

    @Autowired
    private TimetableService timetableService;

    @GetMapping("/teacherCourse/{id}")
    public List<TeacherCourseDTO> teacherCourse(@PathVariable("id") Integer teacher_id){
        return timetableService.findMyCoures(teacher_id);
    }
    @GetMapping("/studentInfo/{offerId}")
    public List<Student> studentInfo(@PathVariable("offerId") Integer offerId){
        return timetableService.studentInfo(offerId);
    }
}

