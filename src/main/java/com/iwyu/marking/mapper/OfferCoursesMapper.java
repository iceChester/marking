package com.iwyu.marking.mapper;

import com.iwyu.marking.entity.OfferCourses;
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
public interface OfferCoursesMapper extends BaseMapper<OfferCourses> {

    @Select("SELECT * FROM `offer_courses` o where  o.main_teacher_id=#{teacherId} or o.assistant_teacher_one=#{teacherId} or o.assistant_teacher_two =#{teacherId} ")
    List<OfferCourses> findMyCoures(Integer teacherId);
}
