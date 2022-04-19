package com.software.deliver.biz.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class WorkFlowReviewDTO {

    private Long workFlowInstanceId;

    private String workFlowNodeCode;

    private Long workFlowProgressId;

    private Long userId;

    /**
     * 1:同意
     * 2:驳回
     * 3:加签
     * 4:转办
     */
    private Integer reviewType;

    /**
     * 加签指定的人
     */
    private Long addSignUserId;

    /**
     * 转办指定的人
     */
    private Long transferUserId;


}
