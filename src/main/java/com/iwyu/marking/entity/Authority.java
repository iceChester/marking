package com.iwyu.marking.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
 * @since 2021-04-03
 */
@Data
  @EqualsAndHashCode(callSuper = false)
  @Accessors(chain = true)
@ApiModel(value="Authentic对象", description="")
public class Authority implements Serializable {

    private static final long serialVersionUID=1L;
    @TableId(value = "auth_id", type = IdType.AUTO)
      private Integer authId;

    private String role;

    private String name;


}
