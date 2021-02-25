package com.iwyu.marking.controller;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.iwyu.marking.dto.TeacherDTO;
import com.iwyu.marking.entity.Teacher;
import com.iwyu.marking.service.TeacherService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Chester
 * @since 2021-02-06
 */
@RestController
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @GetMapping("/likeName/{name}")
    public List<TeacherDTO> likeName(@PathVariable("name") String name) {
        QueryWrapper<Teacher> wrapper = new QueryWrapper<>();
        wrapper.like(StringUtils.isNotBlank(name),"teacher_name", name);
        List<Teacher> list = teacherService.list(wrapper);
        if (list != null) {
            return TeacherDTO.change(list);
        }
        return null;
    }
}

