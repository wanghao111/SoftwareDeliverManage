package com.software.deliver.biz.enums;

import lombok.Getter;

/**
 * @author wanghao
 * @version 1.0
 * @date 2022/4/17 12:58
 */
@Getter
public enum BizExceptionEnum {

    WORK_FLOW_NOT_EXIT_OR_UNABLE(10001, "work_flow_not_exist_or_unable"),
    WORK_FLOW_REVIEW_TYPE_ILLEGAL(10002, "work_flow_review_type_illegal"),


    ;
    private int errorCode;

    private String errorMsg;

    private BizExceptionEnum(int errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }
}
