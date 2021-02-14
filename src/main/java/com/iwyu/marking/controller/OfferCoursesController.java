package com.iwyu.marking.controller;


import com.iwyu.marking.entity.OfferCourses;
import com.iwyu.marking.service.OfferCoursesService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>
 *  前端控制器：开设课程
 * </p>
 *
 * @author Chester
 * @since 2021-02-06
 */
@Controller
@RequestMapping("/offerCourses")
@Api(value = "开设课程前端控制器")
public class OfferCoursesController {

    @Autowired
    private OfferCoursesService offerCoursesService;

    @ApiOperation(value = "教师开课")
    @PostMapping(value = "/offerCourse")
    public String offerCourse (Model model, @RequestBody OfferCourses course){
       if(offerCoursesService.save(course)){
           model.addAttribute("msg","添加成功");
       }else {
           model.addAttribute("msg","添加失败");
       }
        return "coursePage";
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


}

