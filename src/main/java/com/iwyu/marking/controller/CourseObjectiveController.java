package com.iwyu.marking.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.iwyu.marking.entity.Course;
import com.iwyu.marking.entity.CourseObjective;
import com.iwyu.marking.entity.OfferCourses;
import com.iwyu.marking.service.CourseObjectiveService;
import com.iwyu.marking.service.CourseService;
import com.iwyu.marking.service.OfferCoursesService;
import com.iwyu.marking.utils.FileUtil;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Chester
 * @since 2021-04-29
 */
@RestController
@RequestMapping("/courseObjective")
public class CourseObjectiveController {

    @Resource
    private CourseObjectiveService courseObjectiveService;

    @Resource
    private CourseService courseService;


    @DeleteMapping("/delete")
    public boolean delete(@RequestParam("id") Integer id){
        return courseObjectiveService.removeById(id);
    }

    @DeleteMapping("/deleteList")
    public boolean deleteList(@RequestParam("courseIdList") String courseIdList){
        String[] ids =courseIdList.split(",");
        List<Integer> courseIds = new ArrayList<>();
        for (String s :ids) {
            courseIds.add(Integer.parseInt(s));
        }
        QueryWrapper<CourseObjective> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("objective_id",courseIds);
        return courseObjectiveService.remove(queryWrapper);
    }

    @GetMapping("/findAll")
    public IPage<CourseObjective> findAll(@RequestParam("page")Long page, @RequestParam("size") Long size){
        IPage<CourseObjective> courseIPage = new Page<>(page,size);
        return courseObjectiveService.page(courseIPage);
    }

    @GetMapping("/getAll")
    public IPage<CourseObjective> findAll(@RequestParam("courseId") Integer courseId,@RequestParam("page")Long page, @RequestParam("size") Long size){
        Course course = courseService.getById(courseId);
        IPage<CourseObjective> objectiveIPage = new Page<>(page,size);
        String objective = course.getCourseObjectives();
        if(objective!=null&&objective.length()>0){
            QueryWrapper<CourseObjective> objectiveQueryWrapper = new QueryWrapper<>();
            String[] objectives = objective.split(",");
            List<Integer> objective2Int = new ArrayList<>();
            for (String s :objectives) {
                objective2Int.add(Integer.parseInt(s));
            }
            objectiveQueryWrapper.notIn("objective_id", objective2Int );
            return courseObjectiveService.page(objectiveIPage,objectiveQueryWrapper);
        }
        return courseObjectiveService.page(objectiveIPage);
    }

    @GetMapping("/getCourseObjective")
    public List<CourseObjective> getCourseObjective(@RequestParam("courseId") Integer courseId){
        Course course = courseService.getById(courseId);
        String objective = course.getCourseObjectives();
        if(objective!=null&&objective.length()>0){
            QueryWrapper<CourseObjective> objectiveQueryWrapper = new QueryWrapper<>();
            String[] objectives = objective.split(",");
            List<Integer> objective2Int = new ArrayList<>();
            for (String s :objectives) {
                objective2Int.add(Integer.parseInt(s));
            }
            objectiveQueryWrapper.in("objective_id", objective2Int );
            return courseObjectiveService.list(objectiveQueryWrapper);
        }
        return null;
    }

    @GetMapping("/likeByName")
    public List<CourseObjective> likeName(@RequestParam("keyWord") String keyWord){
        QueryWrapper<CourseObjective> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("objective_content",keyWord);
        return courseObjectiveService.list(queryWrapper);
    }

    @RequestMapping("/export")
    public void export(HttpServletResponse response){
        List<CourseObjective> courseObjectives = courseObjectiveService.list();
        //导出操作
        FileUtil.exportExcel(courseObjectives,"课程目标信息","详细",CourseObjective.class,"课程目标信息.xls",response);
    }

    @RequestMapping("/importExcel")
    public boolean importExcel(@RequestParam("file") MultipartFile file){
        List<CourseObjective> courseObjectives = FileUtil.importExcel(file,1,1,CourseObjective.class);
        return courseObjectiveService.saveBatch(courseObjectives);

    }
}

