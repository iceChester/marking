package com.iwyu.marking.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.iwyu.marking.entity.Course;
import com.iwyu.marking.entity.Student;
import com.iwyu.marking.service.CourseService;
import com.iwyu.marking.service.TeacherService;
import com.iwyu.marking.utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
        IPage<Course> courseIPage = new Page<>(page,size);
        return courseService.page(courseIPage);
    }

    @GetMapping("/findById")
    public Course findById(@RequestParam("courseId") Integer id){
        Course course = courseService.getById(id);
        return course;
    }

    @DeleteMapping("/delete")
    public boolean delete(@RequestParam("id") Integer id){
        return courseService.removeById(id);
    }

    @DeleteMapping("/deleteList")
    public boolean deleteList(@RequestParam("courseIdList") String courseIdList){
        String[] ids =courseIdList.split(",");
        List<Integer> courseIds = new ArrayList<>();
        for (String s :ids) {
            courseIds.add(Integer.parseInt(s));
        }
        QueryWrapper<Course> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("course_id",courseIds);
        return courseService.remove(queryWrapper);
    }

    @DeleteMapping("/deleteObjective")
    public  boolean deleteObjective(@RequestParam("courseId") Integer courseId,@RequestParam("objectiveId") Integer objectiveId){
        Course oldCourse = courseService.getById(courseId);
        //移除某个课程指标id
        String[] objectives = oldCourse.getCourseObjectives().split(",");
        List<String> objectiveList = Arrays.asList(objectives);
        List<String> arrList = new ArrayList<String>(objectiveList);
        arrList.remove(Integer.toString(objectiveId));
        String str = arrList.stream().collect(Collectors.joining(","));
        oldCourse.setCourseObjectives(str);
        return courseService.updateById(oldCourse);
    }

    @PostMapping("/addObjective")
    public boolean addObjective(@RequestBody Course course){
        Course oldCourse = courseService.getById(course.getCourseId());
        String courseObjectives = "";
        if(oldCourse.getCourseObjectives()!=null&&oldCourse.getCourseObjectives().length()>0){
            courseObjectives = oldCourse.getCourseObjectives() + ",";
        }
        courseObjectives = courseObjectives + course.getCourseObjectives();
        oldCourse.setCourseObjectives(courseObjectives);
        return courseService.updateById(oldCourse);
    }

    @RequestMapping("/importExcel")
    public boolean importExcel(@RequestParam("file") MultipartFile file){
        List<Course> courseList = FileUtil.importExcel(file,1,1,Course.class);
        return courseService.saveBatch(courseList);

    }

    @RequestMapping("/export")
    public void export(HttpServletResponse response){
        List<Course> studentTaskList = courseService.list();
        //导出操作
        FileUtil.exportExcel(studentTaskList,"课程信息","详细",Course.class,"课程信息.xls",response);
    }
}

