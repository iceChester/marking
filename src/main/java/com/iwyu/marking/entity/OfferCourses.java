package com.iwyu.marking.entity;

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
 * @since 2021-02-06
 */
@Data
  @EqualsAndHashCode(callSuper = false)
  @Accessors(chain = true)
@ApiModel(value="OfferCourses对象", description="")
public class OfferCourses implements Serializable {

    private static final long serialVersionUID=1L;

      @ApiModelProperty(value = "开设课程id")
        @TableId(value = "offer_id", type = IdType.AUTO)
      private Integer offerId;

      @ApiModelProperty(value = "课程id，外键")
      private Integer courseId;

      @ApiModelProperty(value = "主要授课教师")
      private Integer mainTeacherId;

      @ApiModelProperty(value = "助教1")
      private Integer assistantTeacherOne;

      @ApiModelProperty(value = "助教2")
      private Integer assistantTeacherTwo;

      @ApiModelProperty(value = "班级，用逗号隔开")
      private String classes;

      @ApiModelProperty(value = "课程名称")
      private String courseName;

}
