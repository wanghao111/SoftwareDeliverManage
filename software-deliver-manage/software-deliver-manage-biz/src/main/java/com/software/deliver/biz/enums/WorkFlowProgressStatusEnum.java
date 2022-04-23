package com.software.deliver.biz.enums;

import lombok.Getter;

/**
 * @author wanghao
 * @version 1.0
 * @date 2022/4/17 20:32
 */
@Getter
public enum WorkFlowProgressStatusEnum {


    SUBMIT(1,"已提交"),
    NEED_PROCESS(2, "待处理"),
    AGREE(3,"已同意"),
    REJECT(4,"已驳回"),
    ADD_SIGN(5,"已加签"),
    TRANSFER(6, "已转办"),
    RETURNED(7, "已退回"),


    ;
    private Integer status;

    private String msg;

    private WorkFlowProgressStatusEnum(Integer status, String msg) {
        this.status = status;
        this.msg = msg;
    }

}
