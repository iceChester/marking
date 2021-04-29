package com.iwyu.marking.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import com.iwyu.marking.myenum.MarkingTypeEnum;
import com.iwyu.marking.myenum.TaskTypeEnum;
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
@ApiModel(value="Task对象", description="")
public class Task implements Serializable {

    private static final long serialVersionUID=1L;

      @ApiModelProperty(value = "作业id")
      @TableId(value = "task_id", type = IdType.AUTO)
      private Integer taskId;

      @ApiModelProperty(value = "开设课程id")
      private Integer offerId;

      @ApiModelProperty(value = "标题")
      private String title;

      @ApiModelProperty(value = " 具体内容，细节")
      private String detail;

      @ApiModelProperty(value = "截至时间")
      private String deadline;

      @ApiModelProperty(value = " 作业类型，枚举")
      private TaskTypeEnum taskType;

      @ApiModelProperty(value = " 评分方式，枚举")
      private MarkingTypeEnum markingType;

      @ApiModelProperty(value = "作业附件")
      private String attachment;

      @ApiModelProperty(value = "作业目标")
      private String taskObjectives;

      private String markingAccount;

      private String markingWeight;

}
