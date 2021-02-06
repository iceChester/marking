package com.iwyu.marking.entity;

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
@ApiModel(value="Teacher对象", description="")
public class Teacher implements Serializable {

    private static final long serialVersionUID=1L;

      @ApiModelProperty(value = "教师id")
        private String teacherId;

      @ApiModelProperty(value = "教师名")
      private String teacherName;

      @ApiModelProperty(value = "账户")
      private String account;


}
