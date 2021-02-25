package com.iwyu.marking.mapper;

import com.iwyu.marking.entity.Student;
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
public interface StudentMapper extends BaseMapper<Student> {

    @Select("SELECT  class_name,student_id  FROM `student` where class_name like #{name} group by class_name")
    List<Student> getClasses(String name);
}
