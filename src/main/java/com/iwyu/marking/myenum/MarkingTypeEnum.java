package com.iwyu.marking.myenum;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Data;
import lombok.Getter;

/**
 * Created by Chester on 6/2/2021.
 */
@Getter
public enum MarkingTypeEnum {
    MAIN_MARKING(1,"主教师评分"),
    COOPERATION_MARKING(2,"合作评分");

    @EnumValue
    private Integer code;
    private String msg;

    MarkingTypeEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
