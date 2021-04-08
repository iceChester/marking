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
 * @since 2021-02-25
 */
@Data
  @EqualsAndHashCode(callSuper = false)
  @Accessors(chain = true)
@ApiModel(value="Timetable对象", description="")
public class Timetable implements Serializable {

    private static final long serialVersionUID=1L;

      @ApiModelProperty(value = "课程表id")
        @TableId(value = "timetable_id", type = IdType.AUTO)
      private Integer timetableId;

      @ApiModelProperty(value = "课程id")
      private Integer offerId;

      @ApiModelProperty(value = "学生id")
      private Integer studentId;

      private String account;

      private String studentName;


}
