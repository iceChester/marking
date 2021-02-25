package com.iwyu.marking.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.iwyu.marking.entity.Course;
import com.iwyu.marking.service.CourseService;
import io.swagger.models.auth.In;
import jdk.nashorn.internal.objects.annotations.Getter;
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
 * @since 2021-02-06
 */

@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping("/findAll/{page}/{size}")
    public IPage<Course> findAll(@PathVariable("page")Long page,@PathVariable("size") Long size){
        //分页下标从1开始,不用额外减一
        IPage<Course> courseIPage = new Page<>(page,size);
        return courseService.page(courseIPage);
    }

    @GetMapping("/findById/{courseId}")
    public Course findById(@PathVariable("courseId") Integer id){
        Course course = courseService.getById(id);
        return course;
    }
}

