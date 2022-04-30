package com.software.deliver.biz.enums;

import lombok.Getter;

/**
 * @author wanghao
 * @version 1.0
 * @date 2022/4/30 22:12
 */
@Getter
public enum FlowProgressExecuteTypeEnum {

    SUBMIT(1, "已提交"),
    NEED_PROCESS(2, "待处理"),
    AGREE(3, "同意"),
    GO_BACK(4, "退回"),
    REJECT(5,"驳回，拒绝")

    ;

    private Integer code;

    private String msg;

    FlowProgressExecuteTypeEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
