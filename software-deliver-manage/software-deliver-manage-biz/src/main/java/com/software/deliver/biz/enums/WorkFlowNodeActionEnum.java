package com.software.deliver.biz.enums;

import lombok.Getter;

@Getter
public enum WorkFlowNodeActionEnum {
    /**
     * 0:无意义，默认
     * 1:同意，流转至下一个节点
     * 2:驳回，返回上一节点
     * 3:驳回，返回提交人
     * 4:加签，流转至指定人
     * 5:转办，流转至指定人
     */
    APPROVE(1,"同意批准"),
    REJECT_TO_PRE(2, "驳回至上一个节点"),
    REJECT_TO_SUBMIT(3, "驳回至提交人"),
    ADD_SIGN(4,"加签"),
    transfer(5, "转办")
    ;
    private Integer type;

    private String msg;

    private WorkFlowNodeActionEnum(Integer type, String msg) {
        this.type = type;
        this.msg = msg;
    }

}
