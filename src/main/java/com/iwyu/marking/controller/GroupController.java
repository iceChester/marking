package com.iwyu.marking.controller;


import com.iwyu.marking.entity.Group;
import com.iwyu.marking.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Chester
 * @since 2021-04-06
 */
@Controller
@RequestMapping("/group")
public class GroupController {

    @Resource
    private GroupService groupService;

    @RequestMapping("/create")
    public Integer create(@RequestBody Group group){
        group.setDeleted(0);
        if(groupService.save(group)){
            return group.getGroupId();
        }
        return  null;
    }
}

