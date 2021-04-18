package com.iwyu.marking.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName MarkingDTO
 * @Description
 * @Author XiaoMao
 * @Date 2021/4/18 22:59
 * @Version 1.0
 **/
@Data
public class MarkingDTO implements Serializable {
    private Integer position;
    private String[] weight;
}
