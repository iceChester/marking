package com.iwyu.marking.entity;

import java.io.Serializable;
import java.util.List;

import com.baomidou.mybatisplus.annotation.*;
import com.iwyu.marking.myenum.RoleEnum;
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
@ApiModel(value="User对象", description="")
public class User implements Serializable {

    private static final long serialVersionUID=1L;

  @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
      @ApiModelProperty(value = "账号")
        private String account;

      @ApiModelProperty(value = "密码")
      private String password;

      @ApiModelProperty(value = "角色")
      private String role;

      @ApiModelProperty(value = "权限")
      private String authority;

}
