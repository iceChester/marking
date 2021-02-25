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
@ApiModel(value="GroupInfo对象", description="")
public class GroupInfo implements Serializable {

    private static final long serialVersionUID=1L;

      @ApiModelProperty(value = "小组id")
        @TableId(value = "group_id", type = IdType.AUTO)
      private Integer groupId;

      @ApiModelProperty(value = "开课id")
      private Integer offerId;

      @ApiModelProperty(value = "小组名")
      private String groupName;

      @ApiModelProperty(value = "组长")
      private Integer leaderId;

      @ApiModelProperty(value = " 成员一")
      private Integer memberOne;

      @ApiModelProperty(value = " 成员二")
      private Integer memberTwo;

      @ApiModelProperty(value = "成员三")
      private Integer memberThree;

      @ApiModelProperty(value = " 成员四")
      private Integer memberFour;

      @ApiModelProperty(value = " 成员五")
      private Integer memberFive;


}
