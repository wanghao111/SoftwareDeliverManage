package com.software.deliver.biz.enums;

import lombok.Getter;

/**
 * @author wanghao
 * @version 1.0
 * @date 2022/4/16 17:55
 */
@Getter
public enum WorkFlowEnableEnum {

    ENABLE(1,"启用"),
    UNABLE(2, "禁用"),

    ;
    private Integer type;

    private String msg;

    private WorkFlowEnableEnum(Integer type, String msg) {
        this.type = type;
        this.msg = msg;
    }
}
