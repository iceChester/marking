package com.iwyu.marking.controller;


import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.iwyu.marking.dto.LoginDTO;
import com.iwyu.marking.entity.Student;
import com.iwyu.marking.entity.Teacher;
import com.iwyu.marking.entity.User;
//import com.iwyu.marking.service.ShiroService;
import com.iwyu.marking.mapper.UserMapper;
import com.iwyu.marking.service.ShiroService;
import com.iwyu.marking.service.StudentService;
import com.iwyu.marking.service.TeacherService;
import com.iwyu.marking.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName ShiroAction
 * @Description shiro控制层
 * @Author XiaoMao
 * @Date 2021/4/1 21:24
 * @Version 1.0
 **/

@RestController
public class ShiroController {

    private final ShiroService shiroService;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private StudentService studentService;



    public ShiroController(ShiroService shiroService) {
        this.shiroService = shiroService;
    }


    /**
     * 登录
     */
    @ApiOperation(value = "登陆", notes = "参数:用户名 密码")
    @PostMapping("/userLogin")
    public Map<String, Object> login(@RequestBody @Validated LoginDTO loginDTO, BindingResult bindingResult) {
        Map<String, Object> result = new HashMap<>();
        if (bindingResult.hasErrors()) {
            result.put("status", 400);
            result.put("msg", bindingResult.getFieldError().getDefaultMessage());
            return result;
        }

        String account = loginDTO.getAccount();
        String password = loginDTO.getPassword();
        //用户信息
        User user = shiroService.findByAccount(account);
        //账号不存在、密码错误
        if (user == null || !user.getPassword().equals(password)) {
            result.put("status", 400);
            result.put("msg", "账号或密码有误");
        } else {
            //生成token，并保存到数据库
            Integer roleId ;
            String userName = new String();
            result = shiroService.createToken(user.getId());
            result.put("userId",user.getId());
            result.put("account",account);
            System.out.println(account);
            result.put("role",user.getRole());
            if(user.getRole().equals("teacher")){
                QueryWrapper<Teacher> teacherQueryWrapper = new QueryWrapper<>();
                teacherQueryWrapper.eq("account",user.getAccount());
                Teacher teacher = teacherService.getOne(teacherQueryWrapper);
                roleId = teacher.getTeacherId();
                userName = teacher.getTeacherName();
            }else if(user.getRole().equals("student")){
                QueryWrapper<Student> studentQueryWrapper = new QueryWrapper<>();
                studentQueryWrapper.eq("account",user.getAccount());
                Student student = studentService.getOne(studentQueryWrapper);
                roleId = student.getStudentId();
                userName = student.getStudentName();
            }else {
                roleId = user.getId();
                userName = user.getAccount();
            }
            result.put("roleId",roleId);
            result.put("userName",userName);
            result.put("status", 200);
            result.put("msg", "登陆成功");
        }
        return result;
    }

    /**
     * 退出
     */
    @ApiOperation(value = "登出", notes = "参数:token")
    @PostMapping("/userLogout")
    public Map<String, Object> logout(@RequestHeader("token")String token) {
        Map<String, Object> result = new HashMap<>();
        shiroService.logout(token);
        result.put("status", 200);
        result.put("msg", "您已安全退出系统");
        return result;
    }
}