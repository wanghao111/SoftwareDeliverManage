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
    INVALID(0,"无效参数"),
    APPROVE(1,"同意批准"),
    REJECT_TO_PRE(2, "驳回至上一个节点"),
    REJECT_TO_SUBMIT(3, "驳回至提交人"),
    ADD_SIGN(4,"加签"),
    TRANSFER(5, "转办")
    ;
    private Integer type;

    private String msg;

    WorkFlowNodeActionEnum(Integer type, String msg) {
        this.type = type;
        this.msg = msg;
    }

    public static WorkFlowNodeActionEnum fromType(Integer type) {
        WorkFlowNodeActionEnum tmp = WorkFlowNodeActionEnum.INVALID;
        if (WorkFlowNodeActionEnum.APPROVE.getType().equals(type)) {
            tmp = WorkFlowNodeActionEnum.APPROVE;
        } else if (WorkFlowNodeActionEnum.REJECT_TO_PRE.getType().equals(type)) {
            tmp = WorkFlowNodeActionEnum.REJECT_TO_PRE;
        } else if (WorkFlowNodeActionEnum.REJECT_TO_SUBMIT.getType().equals(type)) {
            tmp = WorkFlowNodeActionEnum.REJECT_TO_SUBMIT;
        } else if (WorkFlowNodeActionEnum.ADD_SIGN.getType().equals(type)) {
            tmp = WorkFlowNodeActionEnum.ADD_SIGN;
        } else if (WorkFlowNodeActionEnum.TRANSFER.getType().equals(type)) {
            tmp = WorkFlowNodeActionEnum.TRANSFER;
        }

        return tmp;
    }

}
