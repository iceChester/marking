package com.iwyu.marking.myenum;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Data;

/**
 * Created by Chester on 6/2/2021.
 */
@Data
public enum TaskTypeEnum {
    PRIVATE (1,"个人作业"),
    GROUP(2,"小组作业");

    @EnumValue
    private Integer code;
    private String msg;

    TaskTypeEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
