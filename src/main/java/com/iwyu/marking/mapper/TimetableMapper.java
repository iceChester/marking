package com.iwyu.marking.mapper;

import com.iwyu.marking.entity.Student;
import com.iwyu.marking.entity.Timetable;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

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

    @Select("select s.student_id,s.student_name,s.class_name,s.account from student s where s.account in(select account from timetable where offer_id = #{offerId})")
    List<Student> studentInfo(Integer offerId);
    @Select("SELECT account FROM `timetable` t where t.offer_id = #{offerId}")
    List<String> findMember(Integer offerId);
}
