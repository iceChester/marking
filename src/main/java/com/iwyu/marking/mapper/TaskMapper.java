package com.iwyu.marking.mapper;

import com.iwyu.marking.entity.Task;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Chester
 * @since 2021-02-06
 */
public interface TaskMapper extends BaseMapper<Task> {
    //查找某个课程的布置的作业
    @Select("SELECT * FROM `task` t WHERE t.offer_id = #{offerId}")
    List<Task> findByOfferId(Integer offerId);

}
