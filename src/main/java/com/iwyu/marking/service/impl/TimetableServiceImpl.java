package com.iwyu.marking.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.iwyu.marking.dto.TeacherCourseDTO;
import com.iwyu.marking.dto.TeacherDTO;
import com.iwyu.marking.entity.OfferCourses;
import com.iwyu.marking.entity.Student;
import com.iwyu.marking.entity.Teacher;
import com.iwyu.marking.entity.Timetable;
import com.iwyu.marking.mapper.*;
import com.iwyu.marking.service.TimetableService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.iwyu.marking.utils.Offer2CourseDTOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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

    @Resource
    private TeacherMapper teacherMapper;

    @Resource
    private OfferCoursesMapper offerCoursesMapper;
    @Resource
    private TimetableMapper timetableMapper;

    @Resource
    private StudentMapper studentMapper;

    /**
    *@Description 将课程信息封装打包
    *@Author XiaoMao
    *@Date 2021/4/5 21:32
    *@Param [offerCourses]
    *Return java.util.List<com.iwyu.marking.dto.TeacherCourseDTO>
    **/
    private  List<TeacherCourseDTO> offerCourse2DTO(List<OfferCourses> offerCourses){
        List<TeacherCourseDTO> teacherCourseDTOS = new ArrayList<>();
        for (OfferCourses courses :offerCourses) {
            TeacherCourseDTO teacherCourseDTO = new TeacherCourseDTO();
            QueryWrapper<Teacher> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("account",courses.getMainTeacher());
            teacherCourseDTO.setMainTeacherName(teacherMapper.selectOne(queryWrapper).getTeacherName());
            if(courses.getAssistantTeacherOne()!=null&&courses.getAssistantTeacherOne().length()>0){
                QueryWrapper<Teacher> queryWrapperOne = new QueryWrapper<>();
                queryWrapperOne.eq("account",courses.getAssistantTeacherOne());
                teacherCourseDTO.setAssistantOneName(teacherMapper.selectOne(queryWrapperOne).getTeacherName());
            }
            System.out.println(courses.getAssistantTeacherTwo());
            if(courses.getAssistantTeacherTwo()!=null&&courses.getAssistantTeacherTwo().length()>0){
                System.out.println(courses.getAssistantTeacherTwo());
                QueryWrapper<Teacher> queryWrapperTwo = new QueryWrapper<>();
                queryWrapperTwo.eq("account",courses.getAssistantTeacherTwo());

                teacherCourseDTO.setAssistantTwoName(teacherMapper.selectOne(queryWrapperTwo).getTeacherName());
            }
            teacherCourseDTO.setMemberCount(timetableMapper.countMember(courses.getOfferId()));
            teacherCourseDTO = Offer2CourseDTOUtil.change(courses,teacherCourseDTO);
            teacherCourseDTOS.add(teacherCourseDTO);
        }
        return teacherCourseDTOS;
    }

    @Override
    public List<TeacherCourseDTO> findMyCourse(String account) {
        List<OfferCourses> offerCourses = offerCoursesMapper.findMyCourse(account);
        return offerCourse2DTO(offerCourses);
    }

    @Override
    public List<TeacherCourseDTO> findStudentCourse(String account) {
        QueryWrapper<Timetable> timetableQueryWrapper = new QueryWrapper<>();
        timetableQueryWrapper.eq("account",account);
        List<Timetable> timetables = timetableMapper.selectList(timetableQueryWrapper);
        List<Integer> offerIds = new ArrayList<>();
        for (Timetable timetable :timetables) {
            offerIds.add(timetable.getOfferId());
        }
        List<OfferCourses> offerCourses = new ArrayList<>();
        if(offerIds.size()!= 0){
            QueryWrapper<OfferCourses> offerCoursesQueryWrapper = new QueryWrapper<>();
            offerCoursesQueryWrapper.in("offer_id",offerIds);
            offerCourses = offerCoursesMapper.selectList(offerCoursesQueryWrapper);
        }
        return offerCourse2DTO(offerCourses);
    }

    @Override
    public IPage<Student> studentInfo(Long page, Long size, Integer offerId) {
        Page<Student> studentPage = new Page<>(page,size);
        QueryWrapper<Timetable> timetableQueryWrapper = new QueryWrapper<>();
        timetableQueryWrapper.eq("offer_id",offerId);
        List<Timetable> timetableList = timetableMapper.selectList(timetableQueryWrapper);
        List<String> accountList = new ArrayList<>();
        for (Timetable timetable :timetableList) {
            accountList.add(timetable.getAccount());
        }
        QueryWrapper<Student> studentQueryWrapper = new QueryWrapper<>();
        studentQueryWrapper.in("account",accountList);
        return studentMapper.selectPage(studentPage,studentQueryWrapper);
    }

    @Override
    public List<String> getMemberAccount(Integer offerId) {
        return timetableMapper.findMember(offerId);
    }
}
