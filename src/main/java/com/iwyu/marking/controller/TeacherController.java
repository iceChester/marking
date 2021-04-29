package com.iwyu.marking.controller;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.iwyu.marking.dto.TeacherDTO;
import com.iwyu.marking.entity.Student;
import com.iwyu.marking.entity.StudentTask;
import com.iwyu.marking.entity.Teacher;
import com.iwyu.marking.entity.User;
import com.iwyu.marking.service.TeacherService;
import com.iwyu.marking.service.UserService;
import com.iwyu.marking.utils.FileUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;
import org.w3c.dom.stylesheets.LinkStyle;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
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

    @Resource
    private TeacherService teacherService;
    @Resource
    private UserService userService;

    @GetMapping("/likeName")
    public List<TeacherDTO> likeName(@RequestParam("name") String name) {
        QueryWrapper<Teacher> wrapper = new QueryWrapper<>();
        wrapper.like(StringUtils.isNotBlank(name),"teacher_name", name);
        List<Teacher> list = teacherService.list(wrapper);
        if (list != null) {
            return TeacherDTO.change(list);
        }
        return null;
    }

    @GetMapping("/findAll")
    public IPage<Teacher> findAll(@RequestParam(value = "page") Long page, @RequestParam(value = "size") Long size){
        Page<Teacher> teacherPage = new Page<>(page,size);
        return teacherService.page(teacherPage);
    }

    @DeleteMapping("/delete")
    public boolean delete(@RequestParam("id") Integer id){
        return teacherService.removeById(id);
    }

    @DeleteMapping("/deleteList")
    public boolean deleteList(@RequestParam("teacherList") String teacherList){
        String[] teacher = teacherList.split(",");
        QueryWrapper<Teacher> teacherQueryWrapper = new QueryWrapper<>();
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.in("account",teacher);
        teacherQueryWrapper.in("account",teacher);
        teacherService.remove(teacherQueryWrapper);
        return userService.remove(userQueryWrapper);
    }

    @GetMapping("/likeByName")
    public List<Teacher> likeByName(@RequestParam("keyWord") String keyWord){
        QueryWrapper<Teacher> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(keyWord),"teacher_name",keyWord);
        return teacherService.list(queryWrapper);
    }
    @RequestMapping("/importExcel")
    public boolean importExcel(@RequestParam("file") MultipartFile file){
        List<Teacher> teacherList = FileUtil.importExcel(file,1,1,Teacher.class);
        List<User> userList = new ArrayList<>();
        for (Teacher teacher :teacherList) {
            User user = new User();
            user.setAccount(teacher.getAccount());
            user.setAuthority("1");
            user.setPassword("123");
            user.setRole("teacher");
            userList.add(user);
        }
        try {
            userService.saveBatch(userList);
        }catch (Exception e){
            return false;
        }
        return teacherService.saveBatch(teacherList);
    }
    @RequestMapping("/export")
    public void export(HttpServletResponse response){
        List<Teacher> studentTaskList = teacherService.list();
        //导出操作
        FileUtil.exportExcel(studentTaskList,"教师信息","详细",Teacher.class,"教师信息.xls",response);
    }
}

