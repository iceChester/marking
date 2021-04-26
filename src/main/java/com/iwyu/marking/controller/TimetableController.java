package com.iwyu.marking.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.iwyu.marking.dto.TeacherCourseDTO;
import com.iwyu.marking.entity.Student;
import com.iwyu.marking.entity.Timetable;
import com.iwyu.marking.mapper.TimetableMapper;
import com.iwyu.marking.service.TimetableService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
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

    @Resource
    private TimetableService timetableService;


    @GetMapping("/teacherCourse")
    @RequiresPermissions("teacher")
    public List<TeacherCourseDTO> teacherCourse(@RequestParam(value = "account") String account){
        return timetableService.findMyCourse(account);
    }
    @GetMapping("/studentInfo")
    public IPage<Student> studentInfo(@RequestParam(value = "page") Long page, @RequestParam(value = "size") Long size, @RequestParam(value = "offerId") Integer offerId){
        return timetableService.studentInfo(page,size,offerId);
    }
    @GetMapping("/studentCourse")
    @RequiresPermissions("student")
    public List<TeacherCourseDTO> studentCourse(@RequestParam(value = "account") String account){
        return timetableService.findStudentCourse(account);
    }
    @DeleteMapping("/delete")
    public boolean delete(@RequestParam("offerId") Integer offerId,@RequestParam("account") String account){
        QueryWrapper<Timetable> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("account",account);
        queryWrapper.eq("offer_id",offerId);
        return timetableService.remove(queryWrapper);
    }

    @DeleteMapping("/deleteList")
    public boolean deleteList(@RequestParam("offerId") Integer offerId,@RequestParam("accountList") String accountList){
        String[] accounts = accountList.split(",");
        QueryWrapper<Timetable> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("offer_id",offerId);
        queryWrapper.in("account",accounts);
        return timetableService.remove(queryWrapper);
    }
}

