package com.iwyu.marking.dto;

import com.iwyu.marking.entity.GroupInfo;
import lombok.Data;

import java.io.Serializable;
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
    private String leaderName;
    private String groupName;
    private List<String> memberAccount;
    private List<String> memberName;

    public GroupInfoDTO(){

    }

    public GroupInfoDTO(String leaderName, String groupName, List<GroupInfo> groupInfoList) {
        this.leaderName = leaderName;
        this.groupName = groupName;
        for (GroupInfo info :groupInfoList) {
            this.memberAccount.add(info.getMemberAccount());
            this.memberName.add(info.getMemberName());
        }
    }
}
