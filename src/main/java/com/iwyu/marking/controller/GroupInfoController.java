package com.iwyu.marking.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.iwyu.marking.dto.GroupInfoDTO;
import com.iwyu.marking.entity.GroupInfo;
import com.iwyu.marking.entity.Student;
import com.iwyu.marking.entity.Timetable;
import com.iwyu.marking.service.GroupInfoService;
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
        for (GroupInfo groupInfo :groupInfoList) {
            System.out.println(groupInfo.getMemberName());
        }
        return groupInfoService.saveBatch(groupInfoList);
    }
    @GetMapping("/courseGroups")
    public IPage<GroupInfoDTO> courseGroups(@RequestParam(value = "page") Long page, @RequestParam(value = "size") Long size, @RequestParam("offerId") Integer offerId){
        return groupInfoService.findAllGroupByOfferId(page,size,offerId);
    }

    @DeleteMapping("/removeMember")
    public boolean removeMember(@RequestParam(value = "groupId") Integer groupId,@RequestParam(value = "account")String account){
        QueryWrapper<GroupInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("group_id",groupId);
        queryWrapper.eq("member_account",account);
        return groupInfoService.remove(queryWrapper);
    }
}

