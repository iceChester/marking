package com.iwyu.marking.mapper;

import com.iwyu.marking.entity.Timetable;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Chester
 * @since 2021-02-25
 */
public interface TimetableMapper extends BaseMapper<Timetable> {

    @Select("SELECT count(*) FROM `timetable` t where t.offer_id = #{offerId}")
    Integer countMember(Integer offerId);
}
