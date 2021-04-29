package com.iwyu.marking.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * </p>
 *
 * @author Chester
 * @since 2021-04-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "CourseObjective对象", description = "")
public class CourseObjective implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "课程目标主键")
    @TableId(value = "objective_id", type = IdType.AUTO)
    @Excel(name = "课程目标编号",orderNum = "0")
    private Integer objectiveId;

    @ApiModelProperty(value = "课程目标内容")
    @Excel(name = "课程目标内容",orderNum = "0")
    private String objectiveContent;


}
