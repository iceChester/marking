package com.iwyu.marking.controller;


import com.iwyu.marking.dto.ClassesDTO;
import com.iwyu.marking.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

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
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/findClassName")
    public List<ClassesDTO> findClassName(@RequestParam("name") String name){
        return studentService.findClassName(name);
    }
}

