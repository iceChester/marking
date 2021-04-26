package com.iwyu.marking.dto;

import com.iwyu.marking.entity.StudentTask;
import lombok.Data;

import java.util.List;

/**
 * @ClassName GroupTasksDTO
 * @Description
 * @Author XiaoMao
 * @Date 2021/4/25 21:30
 * @Version 1.0
 **/
@Data
public class GroupTasksDTO {
    private List<StudentTask> studentTaskList;
    private String groupName;
}
