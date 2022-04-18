package com.software.deliver.biz.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class WorkFlowReviewDTO {

    private Long flowInstanceId;

    private String flowNodeCode;

    private Long flowProgressId;

    private Long userId;

    /**
     * 1:同意
     * 2:驳回
     * 3:加签
     * 4:转办
     */
    private Integer reviewActionType;

    /**
     * 加签指定的人
     */
    private Long addSignUserId;

    /**
     * 转办指定的人
     */
    private Long transferUserId;


}
