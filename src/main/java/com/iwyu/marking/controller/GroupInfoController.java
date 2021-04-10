package com.iwyu.marking.controller;


import com.iwyu.marking.dto.GroupInfoDTO;
import com.iwyu.marking.entity.GroupInfo;
import com.iwyu.marking.entity.Student;
import com.iwyu.marking.entity.Timetable;
import com.iwyu.marking.service.GroupInfoService;
import io.swagger.models.auth.In;
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
@RequestMapping("/groupInfo")
public class GroupInfoController {

    @Resource
    private GroupInfoService groupInfoService;

    @GetMapping("/checkStudentGroup")
    public GroupInfoDTO checkStudentGroup(@RequestParam("offerId") Integer offerId ,@RequestParam("account") String account){
        return groupInfoService.checkStudentGroup(offerId,account);
    }

    @GetMapping("/noGroupStudent")
    public List<Timetable> noGroupStudent(@RequestParam("offerId") Integer offerId){
        return groupInfoService.noGroupStudent(offerId);
    }

    @PostMapping("/save")
    public boolean save(@RequestBody List<GroupInfo> groupInfoList){
        return groupInfoService.saveBatch(groupInfoList);
    }
}

