package com.iwyu.marking.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.iwyu.marking.entity.Course;
import com.iwyu.marking.service.CourseService;
import com.iwyu.marking.service.TeacherService;
import com.iwyu.marking.utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Chester
 * @since 2021-02-06
 */

@RestController
@RequestMapping("/course")
public class CourseController {

    @Resource
    private CourseService courseService;

    @GetMapping("/findAll")
    public IPage<Course> findAll(@RequestParam("page")Long page, @RequestParam("size") Long size){
        //分页下标从1开始,不用额外减一
        System.out.println(page);
        IPage<Course> courseIPage = new Page<>(page,size);
        return courseService.page(courseIPage);
    }

    @GetMapping("/findById")
    public Course findById(@RequestParam("courseId") Integer id){
        Course course = courseService.getById(id);
        return course;
    }

    @RequestMapping("/importExcel")
    public boolean importExcel(@RequestParam("file") MultipartFile file){
        List<Course> courseList = FileUtil.importExcel(file,1,1,Course.class);
        return courseService.saveBatch(courseList);

    }
}

