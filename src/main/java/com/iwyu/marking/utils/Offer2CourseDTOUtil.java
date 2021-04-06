package com.iwyu.marking.utils;

import com.iwyu.marking.dto.TeacherCourseDTO;
import com.iwyu.marking.entity.OfferCourses;
import com.iwyu.marking.mapper.TeacherMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @ClassName Offer2CourseDTOUtil
 * @Description
 * @Author XiaoMao
 * @Date 2021/4/5 21:20
 * @Version 1.0
 **/

public class Offer2CourseDTOUtil {
    public static TeacherCourseDTO change(OfferCourses offerCourses,TeacherCourseDTO dto){
        dto.setOfferId(offerCourses.getOfferId());
        dto.setCourseId(offerCourses.getCourseId());
        dto.setCourseName(offerCourses.getCourseName());
        dto.setClasses(offerCourses.getClasses());
        return dto;
    }
}
