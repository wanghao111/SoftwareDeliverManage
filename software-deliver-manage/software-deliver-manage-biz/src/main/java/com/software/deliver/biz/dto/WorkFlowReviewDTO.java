package com.software.deliver.biz.dto;

import lombok.Data;

@Data
public class WorkFlowReviewDTO {

    private String flowInstanceCodeId;

    private String flowNodeCode;

    private Long userId;

    private Integer reviewActionType;

    


}
