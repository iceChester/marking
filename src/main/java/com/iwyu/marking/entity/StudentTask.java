package com.iwyu.marking.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
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
      @Excel(name = "学号", orderNum = "0")
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
      @Excel(name = "完成时间", orderNum = "0")
      private String submitDate;

      @ApiModelProperty(value = "分数一")
      @Excel(name = "分数一", orderNum = "0")
      private Float scoreOne;

      @ApiModelProperty(value = " 分数二")
      @Excel(name = "分数二", orderNum = "0")
      private Float scoreTwo;

      @ApiModelProperty(value = " 分数三")
      @Excel(name = "分数三", orderNum = "0")
      private Float scoreThree;

      @ApiModelProperty(value = " 总分")
      @Excel(name = "总分", orderNum = "0")
      private Float scoreTotal;

      @TableField(exist = false)
      @Excel(name = "姓名", orderNum = "0")
      private String studentName;

      @TableField(exist = false)
      @Excel(name = "班级", orderNum = "0")
      private String className;

      @TableField(exist = false)
      private String score;

      @TableField(exist = false)
      private Integer groupId;

}
