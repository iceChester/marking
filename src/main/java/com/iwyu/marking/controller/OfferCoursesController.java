package com.iwyu.marking.controller;


import com.iwyu.marking.dto.OfferCourseDTO;
import com.iwyu.marking.entity.OfferCourses;
import com.iwyu.marking.entity.Teacher;
import com.iwyu.marking.service.OfferCoursesService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * <p>
 *  前端控制器：开设课程
 * </p>
 *
 * @author Chester
 * @since 2021-02-06
 */
@RestController
@RequestMapping("/offerCourses")
@Api(value = "开设课程前端控制器")
public class OfferCoursesController {

    @Autowired
    private OfferCoursesService offerCoursesService;

    @ApiOperation(value = "教师开课")
    @PostMapping(value = "/offerCourse")
    public boolean offerCourse (@RequestBody OfferCourseDTO dto){

        return offerCoursesService.saveOffer(dto);
    }

    @ApiOperation(value = "修改开课的课程信息")
    @PostMapping(value = "/updateCourse")
    public String updateCourse (Model model,@RequestBody OfferCourses course){
        model.addAttribute("offerCourseId",course.getCourseId());
        if(offerCoursesService.saveOrUpdate(course)){
            model.addAttribute("msg","修改成功");
        }else {
            model.addAttribute("msg","修改失败");
        }
        return "offerCourse";
    }

    @GetMapping("/findTeacher")
    public List<Teacher> findTeacher(@RequestParam("offerId") Integer offerId){
        return offerCoursesService.findTeacher(offerId);
    }
}

