package com.iwyu.marking.controller;

import com.iwyu.marking.entity.Course;
import com.iwyu.marking.mapper.CourseMapper;
import com.iwyu.marking.service.CourseService;
import com.iwyu.marking.service.impl.CourseServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.shadow.com.univocity.parsers.common.input.LineSeparatorDetector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Chester on 23/2/2021.
 */
@SpringBootTest
class CourseControllerTest {
    @Autowired
    CourseService service;
    @Test
    public void findAll(){
//        CourseController courseController = new CourseController();
       List<Course> list = service.list();
        for (Course course :list) {
            System.out.println(course);
        }

    }
}