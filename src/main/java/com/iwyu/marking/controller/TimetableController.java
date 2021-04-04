package com.iwyu.marking.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.iwyu.marking.dto.TeacherCourseDTO;
import com.iwyu.marking.entity.Student;
import com.iwyu.marking.mapper.TimetableMapper;
import com.iwyu.marking.service.TimetableService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import javax.websocket.server.PathParam;
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


    @GetMapping("/teacherCourse")
    @RequiresPermissions("teacher")
    public List<TeacherCourseDTO> teacherCourse(@RequestParam(value = "id") Integer id){
        return timetableService.findMyCoures(id);
    }
    @GetMapping("/studentInfo/{page}/{size}/{offerId}")
    public IPage<Student> studentInfo(@PathVariable("page")Long page, @PathVariable("size") Long size, @PathVariable("offerId") Integer offerId){
        return timetableService.studentInfo(page,size,offerId);
    }
}

