package com.iwyu.marking.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.stereotype.Repository;

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
@ApiModel(value="Course对象", description="")
public class Course implements Serializable {

    private static final long serialVersionUID=1L;

      @ApiModelProperty(value = "课程id")
        @TableId(value = "course_id", type = IdType.AUTO)
      private Integer courseId;

      @ApiModelProperty(value = "课程名称")
      private String courseName;

      @ApiModelProperty(value = "假删除标识")
      @TableField(fill = FieldFill.INSERT)
      @TableLogic
      private Integer deleted;


}
