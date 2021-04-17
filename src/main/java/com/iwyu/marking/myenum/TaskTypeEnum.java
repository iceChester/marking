package com.iwyu.marking.myenum;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Data;
import lombok.Getter;

/**
 * Created by Chester on 6/2/2021.
 */
@Getter
public enum TaskTypeEnum {
    PRIVATE (0,"个人作业"),
    GROUP(1,"小组作业");

    @EnumValue
    private Integer code;
    private String msg;

    TaskTypeEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
