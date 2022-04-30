package com.software.deliver.biz.enums;

import lombok.Getter;

/**
 * @author wanghao
 * @version 1.0
 * @date 2022/4/30 20:59
 */
@Getter
public enum NodeHandlerTypeEnum {


    APPOINTOR(1, "指定人处理"),
    FIX_PERSON(2, "固定人处理"),
    SUPERVISOR(3, "上一节点主管处理"),
    SPECIFY_ROLE(4, "指定角色处理"),
    ;

    private Integer code;

    private String msg;

    NodeHandlerTypeEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
