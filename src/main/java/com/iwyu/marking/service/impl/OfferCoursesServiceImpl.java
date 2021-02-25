package com.iwyu.marking.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.iwyu.marking.dto.OfferCourseDTO;
import com.iwyu.marking.entity.OfferCourses;
import com.iwyu.marking.entity.Student;
import com.iwyu.marking.entity.Timetable;
import com.iwyu.marking.mapper.CourseMapper;
import com.iwyu.marking.mapper.OfferCoursesMapper;
import com.iwyu.marking.mapper.StudentMapper;
import com.iwyu.marking.mapper.TimetableMapper;
import com.iwyu.marking.service.OfferCoursesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.iwyu.marking.service.TimetableService;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Chester
 * @since 2021-02-06
 */
@Service
public class OfferCoursesServiceImpl extends ServiceImpl<OfferCoursesMapper, OfferCourses> implements OfferCoursesService {

    @Autowired
    private OfferCoursesMapper offerMapper;

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private TimetableService timetableService;

    //后期把事务加上,保存开课信息和添加学生要同步
    @Override
    public boolean saveOffer(OfferCourseDTO dto) {
        OfferCourses offerCourses = OfferCourseDTO.saveChange(dto);
        offerMapper.insert(offerCourses);
        Integer offerId = offerCourses.getOfferId();
        List<Timetable> timetables = new ArrayList<>();
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("class_name",dto.getClasses());
        List<Student> students =  studentMapper.selectList(queryWrapper);
        for (Student student :students) {
            Timetable timetable = new Timetable();
            timetable.setStudentId(student.getStudentId());
            timetable.setOfferId(offerId);
            timetables.add(timetable);
        }
        if(timetableService.saveBatch(timetables)){
            return true;
        }
        return false;
    }
}
