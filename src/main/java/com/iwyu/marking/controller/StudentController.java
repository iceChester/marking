package com.iwyu.marking.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.iwyu.marking.dto.ClassesDTO;
import com.iwyu.marking.entity.Student;
import com.iwyu.marking.entity.Teacher;
import com.iwyu.marking.entity.User;
import com.iwyu.marking.service.StudentService;
import com.iwyu.marking.service.UserService;
import com.iwyu.marking.utils.FileUtil;

import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.sql.BatchUpdateException;
import java.util.ArrayList;
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
@RequestMapping("/student")
public class StudentController {

    @Resource
    private StudentService studentService;

    @Resource
    private UserService userService;

    @GetMapping("/findClassName")
    public List<ClassesDTO> findClassName(@RequestParam("name") String name){
        return studentService.findClassName(name);
    }
    @GetMapping("/findAll")
    public IPage<Student> findAll(@RequestParam(value = "page") Long page, @RequestParam(value = "size") Long size){
        Page<Student> studentPage = new Page<>(page,size);
        return studentService.page(studentPage);
    }
    @DeleteMapping("/delete")
    public boolean delete(@RequestParam("id") Integer id){
        return studentService.removeById(id);
    }
    @GetMapping("/likeName")
    public List<Student> likeName(@RequestParam("keyWord") String keyWord){
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(keyWord),"student_name",keyWord);
        return studentService.list(queryWrapper);
    }
    @RequestMapping("/importExcel")
    public boolean importExcel(@RequestParam("file") MultipartFile file){
        //解析excel，
        List<Student> studentList = FileUtil.importExcel(file,1,1,Student.class);
        //也可以使用MultipartFile,使用 FileUtil.importExcel(MultipartFile file, Integer titleRows, Integer headerRows, Class<T> pojoClass)导入
        System.out.println("导入数据一共【"+studentList.size()+"】行");
        List<User> userList = new ArrayList<>();
        for (Student student :studentList) {
            User user = new User();
            user.setAccount(student.getAccount());
            user.setAuthority("1");
            user.setPassword("123");
            user.setRole("student");
            userList.add(user);
        }
        try {
            userService.saveBatch(userList);
        }catch (Exception e){
            return false;
        }

        return studentService.saveBatch(studentList);
    }
    @RequestMapping("/export")
    public void export(HttpServletResponse response){
        List<Student> studentTaskList = studentService.list();
        //导出操作
        FileUtil.exportExcel(studentTaskList,"学生信息","详细",Student.class,"学生信息.xls",response);
    }
}

