package com.iwyu.marking.dto;

import com.iwyu.marking.entity.GroupInfo;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName GroupInfoDTO
 * @Description
 * @Author XiaoMao
 * @Date 2021/4/7 22:53
 * @Version 1.0
 **/
@Data
public class GroupInfoDTO implements Serializable {
    private Integer groupId;
    private String leaderName;
    private String groupName;
    private List<String> memberAccount;
    private List<String> memberName;

    public GroupInfoDTO(){

    }

    public GroupInfoDTO(Integer groupId,String leaderName, String groupName, List<GroupInfo> groupInfoList) {
        List<String> account = new ArrayList<>();
        List<String> name = new ArrayList<>();
        for (GroupInfo info :groupInfoList) {
            account.add(info.getMemberAccount());
            name.add(info.getMemberName());
        }
        this.memberAccount = account;
        this.memberName = name;
        this.groupId = groupId;
        this.leaderName = leaderName;
        this.groupName = groupName;
    }
}
