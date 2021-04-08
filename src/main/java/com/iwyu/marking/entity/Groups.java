package com.iwyu.marking.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.*;
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
 * @since 2021-04-06
 */
@Data
  @EqualsAndHashCode(callSuper = false)
  @Accessors(chain = true)
@ApiModel(value="Groups对象", description="")
public class Groups implements Serializable {

    private static final long serialVersionUID=1L;

      @ApiModelProperty(value = "小组id")
//      @TableId(value = "group_id", type = IdType.AUTO)
        private Integer groupId;

      @ApiModelProperty(value = "开课id")
      private Integer offerId;

      @ApiModelProperty(value = "组名")
      private String groupName;

//      @ApiModelProperty(value = "组长")
//      private Integer leaderId;

      @TableField(fill = FieldFill.INSERT)
      @TableLogic
      @ApiModelProperty(value = "假删除标识")
      private Integer deleted;

      @ApiModelProperty(value = "组长学号")
      private String leaderAccount;

      @ApiModelProperty(value = "组长名")
      private String leaderName;


}
