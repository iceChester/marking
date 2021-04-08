package com.iwyu.marking.controller;


import com.iwyu.marking.dto.GroupInfoDTO;
import com.iwyu.marking.service.GroupInfoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Chester
 * @since 2021-02-06
 */
@RestController
@RequestMapping("/groupInfo")
public class GroupInfoController {

    @Resource
    private GroupInfoService groupInfoService;

    @GetMapping("/checkStudentGroup")
    public GroupInfoDTO checkStudentGroup(@RequestParam("offerId") Integer offerId ,@RequestParam("account") String account){
        return groupInfoService.checkStudentGroup(offerId,account);
    }
}

