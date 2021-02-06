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
@ApiModel(value="User对象", description="")
public class User implements Serializable {

    private static final long serialVersionUID=1L;

      @ApiModelProperty(value = "账号")
        private String account;

      @ApiModelProperty(value = "密码")
      private String password;

      @ApiModelProperty(value = "角色，枚举")
      private Integer role;

      @ApiModelProperty(value = "权限，枚举")
      private Integer authority;

      @ApiModelProperty(value = "假删除标识")
      private Integer deleted;


}
