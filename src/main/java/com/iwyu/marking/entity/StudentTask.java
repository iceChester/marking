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
@ApiModel(value="StudentTask对象", description="")
public class StudentTask implements Serializable {

    private static final long serialVersionUID=1L;

      @ApiModelProperty(value = "学生提交作业id")
        @TableId(value = "student_task_id", type = IdType.AUTO)
      private Integer studentTaskId;

      @ApiModelProperty(value = " 学号")
      private String account;

      @ApiModelProperty(value = " 作业号")
      private Integer taskId;

      @ApiModelProperty(value = " 作业文件名")
      private String fileName;

      @ApiModelProperty(value = " 图片作业")
      private String imgFile;

      @ApiModelProperty(value = " 视频作业")
      private String videoFile;

      @ApiModelProperty(value = " 提交日期")
      private String submitDate;

      @ApiModelProperty(value = "分数一")
      private Float scoreOne;

      @ApiModelProperty(value = " 分数二")
      private Float scoreTwo;

      @ApiModelProperty(value = " 分数三")
      private Float scoreThree;

      @ApiModelProperty(value = " 总分")
      private Float scoreTotal;


}
