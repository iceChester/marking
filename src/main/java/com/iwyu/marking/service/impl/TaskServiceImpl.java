package com.iwyu.marking.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.iwyu.marking.entity.Task;
import com.iwyu.marking.mapper.TaskMapper;
import com.iwyu.marking.service.TaskService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
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
public class TaskServiceImpl extends ServiceImpl<TaskMapper, Task> implements TaskService {

    @Resource
    protected TaskMapper taskMapper;

    @Override
    public List<Task> findByOfferId(int offerId) {
        return taskMapper.findByOfferId(offerId);
    }

    @Override
    public List<Task> findCollectingTask(int offerId) {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        QueryWrapper<Task> taskQueryWrapper = new QueryWrapper<>();
        taskQueryWrapper.ge("deadline",sdf.format(date));
        taskQueryWrapper.eq("offer_id",offerId);
        return taskMapper.selectList(taskQueryWrapper);
    }

    @Override
    public List<Task> findDeadlineTask(int offerId) {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        QueryWrapper<Task> taskQueryWrapper = new QueryWrapper<>();
        taskQueryWrapper.lt("deadline",sdf.format(date));
        taskQueryWrapper.eq("offer_id",offerId);
        return taskMapper.selectList(taskQueryWrapper);
    }
}
