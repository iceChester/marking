package com.iwyu.marking.service.impl;

import com.iwyu.marking.entity.Task;
import com.iwyu.marking.mapper.TaskMapper;
import com.iwyu.marking.service.TaskService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
