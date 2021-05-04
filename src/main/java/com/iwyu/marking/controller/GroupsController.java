package com.iwyu.marking.controller;



import com.iwyu.marking.entity.Groups;
import com.iwyu.marking.service.GroupsService;
import org.springframework.boot.context.event.EventPublishingRunListener;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

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

    @PostMapping("/create")
    public Integer create(@RequestBody Groups group){
        if(groupsService.save(group)){
            return group.getGroupId();
        }
        return  0;
    }
    @RequestMapping("/checkLeader")
    public boolean checkLeader(@RequestParam("offerId") Integer offerId,@RequestParam("account") String account){
        Groups groups = groupsService.checkLeader(offerId,account);
        if(groups==null){
            return false;
        }
        return true;
    }
}

