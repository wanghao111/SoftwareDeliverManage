package com.software.deliver.biz.enums;

import lombok.Getter;

/**
 * @author wanghao
 * @version 1.0
 * @date 2022/4/24 22:43
 */
@Getter
public enum WorkFlowNodeBizTypeEnum {

    DEVELOP_OWNER_NODE(1,"开发负责人节点"),
    DEVELOP_OWNER_SUPERVISOR_NODE(2,"开发负责人主管节点"),
    TEST_OWNER_NODE(3,"测试负责人节点"),
    TEST_OWNER_SUPERVISOR_NODE(4,"测试负责人主管节点"),
    QA_OWNER_NODE(5,"qa负责人节点"),


    ;
    private Integer type;

    private String msg;

    WorkFlowNodeBizTypeEnum(Integer type, String msg) {
        this.type = type;
        this.msg = msg;
    }

}
