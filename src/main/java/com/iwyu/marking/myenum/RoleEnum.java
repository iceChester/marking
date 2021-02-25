package com.iwyu.marking.myenum;/**
 * Created by Chester on 24/2/2021.
 */

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;

/**
 * @ClassName RoleEnum
 * @Description
 * @Author XiaoMao
 * @Date 24/2/2021 下午8:33
 * @Version 1.0
 **/
@Getter
public enum  RoleEnum {
    STUDENT(1,"学生"),
    TEACHER(2,"教师");

    @EnumValue
    private Integer code;
    private String msg;

    RoleEnum(Integer code,String msg){
        this.msg = msg;
        this.code = code;
    }
}
