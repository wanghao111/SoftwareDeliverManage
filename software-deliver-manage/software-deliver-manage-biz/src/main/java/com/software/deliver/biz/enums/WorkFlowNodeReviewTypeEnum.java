package com.software.deliver.biz.enums;

import lombok.Getter;

/**
 * @author wanghao
 * @version 1.0
 * @date 2022/4/18 22:22
 */
@Getter
public enum WorkFlowNodeReviewTypeEnum {


    /**
     * 0:无意义，默认
     * 1:同意，流转至下一个节点
     * 2:驳回，返回上一节点
     * 3:加签，流转至指定人
     * 4:转办，流转至指定人
     */
    APPROVE(1,"同意批准"),
    REJECT(2, "驳回"),
    ADD_SIGN(3,"加签"),
    transfer(4, "转办")
    ;
    private Integer type;

    private String msg;

    private WorkFlowNodeReviewTypeEnum(Integer type, String msg) {
        this.type = type;
        this.msg = msg;
    }
}
