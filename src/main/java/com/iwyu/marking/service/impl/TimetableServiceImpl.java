package com.iwyu.marking.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.iwyu.marking.dto.TeacherCourseDTO;
import com.iwyu.marking.dto.TeacherDTO;
import com.iwyu.marking.entity.OfferCourses;
import com.iwyu.marking.entity.Student;
import com.iwyu.marking.entity.Timetable;
import com.iwyu.marking.mapper.OfferCoursesMapper;
import com.iwyu.marking.mapper.TeacherMapper;
import com.iwyu.marking.mapper.TimetableMapper;
import com.iwyu.marking.service.TimetableService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
 * @since 2021-02-25
 */
@Service
public class TimetableServiceImpl extends ServiceImpl<TimetableMapper, Timetable> implements TimetableService {

    @Autowired
    private TeacherMapper teacherMapper;
    @Autowired
    private OfferCoursesMapper offerCoursesMapper;
    @Autowired
    private TimetableMapper timetableMapper;

    @Override
    public List<TeacherCourseDTO> findMyCoures(Integer teacherId) {
        List<OfferCourses> offerCourses = offerCoursesMapper.findMyCoures(teacherId);
        List<TeacherCourseDTO> teacherCourseDTOS = new ArrayList<>();
        for (OfferCourses courses :offerCourses) {
            TeacherCourseDTO teacherCourseDTO = new TeacherCourseDTO();
            teacherCourseDTO.setMainTeacherName(teacherMapper.selectById(courses.getMainTeacherId()).getTeacherName());
            if(courses.getAssistantTeacherOne()!=null){
                teacherCourseDTO.setAssistantOneName(teacherMapper.selectById(courses.getAssistantTeacherOne()).getTeacherName());
            }
            if(courses.getAssistantTeacherTwo()!=null){
                teacherCourseDTO.setAssistantTwoName(teacherMapper.selectById(courses.getAssistantTeacherTwo()).getTeacherName());
            }
            teacherCourseDTO.setOfferId(courses.getOfferId());
            teacherCourseDTO.setCourseId(courses.getCourseId());
            teacherCourseDTO.setCourseName(courses.getCourseName());
            teacherCourseDTO.setClasses(courses.getClasses());
            teacherCourseDTO.setMemberCount(timetableMapper.countMember(courses.getOfferId()));
            teacherCourseDTOS.add(teacherCourseDTO);
        }
        return teacherCourseDTOS;
    }

    @Override
    public IPage<Student> studentInfo(Long page, Long size, Integer offerId) {
        Page<Student> studentPage = new Page<>(page,size);
        List<Student> students = timetableMapper.studentInfo(offerId);
        int count = students.size();
        List<Student> pageList = new ArrayList<>();
//计算当前页第一条数据的下标
        int current = page.intValue();
        int currId = current>1 ? (int) ((current - 1) * size) :0;
        for (int i=0; i<size && i<count - currId;i++){
            pageList.add(students.get(currId+i));
        }
        studentPage.setSize(size);
        studentPage.setCurrent(current);
        studentPage.setTotal(count);
//计算分页总页数
        studentPage.setPages(count %10 == 0 ? count/10 :count/10+1);
        studentPage.setRecords(pageList);
        return studentPage;
    }
}
