package com.software.deliver.biz.enums;

import lombok.Getter;

@Getter
public enum WorkFlowNodeHandleTypeEnum {

    ASSIGNER(1,"指定人处理"),
    FIXED_PERSON(2, "固定人处理"),
    PRE_NODE_SUPERVISOR(3, "上一节点主管处理"),
    ASSIGN_ROLE(4,"指定角色处理")
    ;
    private Integer type;

    private String msg;

    WorkFlowNodeHandleTypeEnum(Integer type, String msg) {
        this.type = type;
        this.msg = msg;
    }
}
