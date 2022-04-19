package com.software.deliver.biz.model;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author wanghao
 * @version 1.0
 * @date 2022/4/17 10:54
 */
@Data
@Builder
public class WorkFlowInstance {

    private long id;

    private Long workFlowId;

    private String workFlowCode;

    private String title;

    private Long submitUserId;

//    /**
//     * 当前审批状态
//     */
//    private Integer currentReviewStatus;

    /**
     * 当前审批人id列表
     */
    private List<Long> currentReviewUserIds;

    private Timestamp createdAt;

    private Timestamp updateAt;

}
