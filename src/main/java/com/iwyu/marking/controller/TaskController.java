package com.iwyu.marking.controller;


import com.iwyu.marking.entity.Task;
import com.iwyu.marking.service.TaskService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

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
        if(taskService.save(task)){
            return true;
        }
        return false;
    }

    @RequestMapping("/findByOfferId/{offerId}")
    public List<Task> findByOfferId(@PathVariable("offerId") int offerId){
        return taskService.findByOfferId(offerId);
    }

}

