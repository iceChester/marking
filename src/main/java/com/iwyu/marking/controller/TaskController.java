package com.iwyu.marking.controller;


import com.iwyu.marking.entity.Task;
import com.iwyu.marking.service.TaskService;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
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

}

