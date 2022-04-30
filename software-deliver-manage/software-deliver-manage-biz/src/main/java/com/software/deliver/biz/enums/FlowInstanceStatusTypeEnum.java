package com.software.deliver.biz.enums;

import lombok.Getter;

/**
 * @author wanghao
 * @version 1.0
 * @date 2022/4/30 23:46
 */
@Getter
public enum FlowInstanceStatusTypeEnum {

    OPEN(1, "审批中"),
    DONE(2, "已完成"),
    CLOSE(3, "已关闭"),

            ;

    private Integer code;

    private String msg;

    FlowInstanceStatusTypeEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
