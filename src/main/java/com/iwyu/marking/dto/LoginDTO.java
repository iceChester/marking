package com.iwyu.marking.dto;

/**
 * @ClassName LoginDTO
 * @Description
 * @Author XiaoMao
 * @Date 2021/4/3 15:51
 * @Version 1.0
 **/

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 登录传输类
 */
@Data
public class LoginDTO {
    @NotBlank(message = "用户名不能为空")
    private String account;
    @NotBlank(message = "密码不能为空")
    private String password;
}
