package com.software.deliver.biz.enums;

import lombok.Getter;

/**
 * @author wanghao
 * @version 1.0
 * @date 2022/4/24 22:55
 */
@Getter
public enum WorkFlowInstanceVarTypeEnum {

    /**
     * 0:无意义，默认
     * 1:节点处理人变量
     * 2:节点流入判断变量
     * 3:节点的前置子流程处理汇总变量 key:flowNodeCode_preFlowNodeCode
     *
     */

    FLOW_NODE_PROCESS_ID_VAR(1,"节点处理人变量"),
    FLOW_NODE_ENTER_JUDGE_VAR(2, "节点流入判断变量"),
    PRE_NODE_SUMMARY_VAR(3, "节点的前置子流程处理汇总变量"),//key:flowNodeCode_preFlowNodeCode

    ;
    private Integer type;

    private String msg;

    WorkFlowInstanceVarTypeEnum(Integer type, String msg) {
        this.type = type;
        this.msg = msg;
    }


}
