package com.iwyu.marking.entity;

import com.baomidou.mybatisplus.annotation.*;

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
@ApiModel(value="TeachingMaterial对象", description="")
public class TeachingMaterial implements Serializable {

    private static final long serialVersionUID=1L;

      @ApiModelProperty(value = "资料id")
        @TableId(value = " material_id", type = IdType.AUTO)
      private Integer  materialId;

      @ApiModelProperty(value = "开课id")
      private Integer offerId;

      @ApiModelProperty(value = " 资料名")
      private String materialName;

      @ApiModelProperty(value = "上传时间")
      private String uploadDate;

      @ApiModelProperty(value = "乐观锁,自动填充，0为默认值")
      @TableField(fill = FieldFill.INSERT)
      @Version
      private Integer version;


}
