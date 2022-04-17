package com.software.deliver.biz.enums;

import lombok.Getter;

/**
 * @author wanghao
 * @version 1.0
 * @date 2022/4/16 18:26
 */
@Getter
public enum WorkFlowNodeRelTypeEnum {

    PRE_NODE(1,"前置节点"),
    NEXT_NODE(2, "后置节点"),

    ;
    private Integer type;

    private String msg;

    private WorkFlowNodeRelTypeEnum(Integer type, String msg) {
        this.type = type;
        this.msg = msg;
    }

}
