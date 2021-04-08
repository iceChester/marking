package com.iwyu.marking.controller;



import com.iwyu.marking.entity.Groups;
import com.iwyu.marking.service.GroupsService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Chester
 * @since 2021-04-06
 */
@RestController
@RequestMapping("/groups")
public class GroupsController {

    @Resource
    private GroupsService groupsService;

    @RequestMapping("/create")
    public Integer create(@RequestBody Groups group){
//        group.setDeleted(0);
        System.out.println(group);
        if(groupsService.save(group)){
            return group.getGroupId();
        }
        return  null;
    }
}

