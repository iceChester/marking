package com.iwyu.marking.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
 * @since 2021-02-06
 */
@Data
  @EqualsAndHashCode(callSuper = false)
  @Accessors(chain = true)
@ApiModel(value="Student对象", description="")
public class Student implements Serializable {

    private static final long serialVersionUID=1L;

      @ApiModelProperty(value = "序号")
      @TableId(value = "student_id", type = IdType.AUTO)
      private Integer studentId;

      @ApiModelProperty(value = "班级")
      private String className;

      @ApiModelProperty(value = "姓名")
      private String studentName;

      @ApiModelProperty(value = "账号")
      private String account;


}
