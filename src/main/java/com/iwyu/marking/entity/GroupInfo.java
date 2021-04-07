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
 * @since 2021-04-06
 */
@Data
  @EqualsAndHashCode(callSuper = false)
  @Accessors(chain = true)
@ApiModel(value="GroupInfo对象", description="")
public class GroupInfo implements Serializable {

    private static final long serialVersionUID=1L;

      @ApiModelProperty(value = "小组信息id")
        @TableId(value = "group_info_id", type = IdType.AUTO)
      private Integer groupInfoId;

      @ApiModelProperty(value = "小组id")
      private Integer groupId;

      @ApiModelProperty(value = "开课id")
      private Integer offerId;

      @ApiModelProperty(value = " 成员id")
      private Integer memberId;

      @ApiModelProperty(value = "成员名")
      private String memberName;

      @ApiModelProperty(value = "学号")
      private String memberAccount;


}
