package com.iwyu.marking.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.iwyu.marking.dto.MarkingDTO;
import com.iwyu.marking.entity.OfferCourses;
import com.iwyu.marking.entity.StudentTask;
import com.iwyu.marking.entity.Task;
import com.iwyu.marking.service.OfferCoursesService;
import com.iwyu.marking.service.TaskService;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

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
@RequestMapping("/task")
public class TaskController {

    @Resource
    protected TaskService taskService;
    @Resource
    private OfferCoursesService offerCoursesService;

    @RequestMapping("/save")
    public boolean save(@RequestBody Task task){
        System.out.println(task);
        if(taskService.save(task)){
            return true;
        }
        return false;
    }

    @RequestMapping("/findByOfferId")
    public List<Task> findByOfferId(@RequestParam("offerId") int offerId){
        return taskService.findByOfferId(offerId);
    }

    @GetMapping("/checkMarking")
    public MarkingDTO checkMarking(@RequestParam("taskId")Integer taskId, @RequestParam("account") String account){
        Task task = taskService.getById(taskId);
        MarkingDTO markingDTO = new MarkingDTO();
        String[] weight = task.getMarkingWeight().split(",");
        markingDTO.setWeight(weight);
        if(task.getMarkingType().getCode()==0){
            if(offerCoursesService.getById(task.getOfferId()).getMainTeacher().equals(account)){
                markingDTO.setPosition(1);
            }else {
                markingDTO.setPosition(4);
            }
        }else{
           String[] taskAccount =  task.getMarkingAccount().split(",");
            for (int i=0;i<taskAccount.length;i++) {
                if(taskAccount[i].equals(account)){
                    System.out.println(i);
                    markingDTO.setPosition(i+1);
                    markingDTO.setWeight(weight);
                    return markingDTO;
                }
            }
            markingDTO.setPosition(4);
        }
        return markingDTO;
    }
}

