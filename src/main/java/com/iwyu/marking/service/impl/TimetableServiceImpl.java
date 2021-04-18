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
            if(courses.getAssistantTeacherOne()!=null){
                QueryWrapper<Teacher> queryWrapperOne = new QueryWrapper<>();
                queryWrapperOne.eq("account",courses.getAssistantTeacherOne());
                teacherCourseDTO.setAssistantOneName(teacherMapper.selectOne(queryWrapperOne).getTeacherName());
            }
            if(courses.getAssistantTeacherTwo()!=null){
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
//需要重构
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

    @Override
    public List<String> getMemberAccount(Integer offerId) {
        return timetableMapper.findMember(offerId);
    }
}
