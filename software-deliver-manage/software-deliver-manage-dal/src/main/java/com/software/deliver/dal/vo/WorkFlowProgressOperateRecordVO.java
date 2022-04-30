package com.software.deliver.dal.vo;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

/**
 * @author wanghao
 * @version 1.0
 * @date 2022/4/30 11:31
 */
@Data
@Builder
public class WorkFlowProgressOperateRecordVO {

    private Long id;

    private String flowCode;

    private String nodeCode;

    private Long flowInstanceId;

    private Integer executeType;

    private Long processUserId;

    private String processMsg;

    private Integer isViewed;

    private Timestamp viewTime;

    /**
     * 是否处理
     */
//    private Integer isExecuted;

    private Timestamp executeTime;

//    private Long staySeconds;

    private String nextNodeCode;

    private String nextNodeProcessUserIds;

    private Timestamp createdAt;

    private Timestamp updateAt;

}
