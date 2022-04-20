package com.software.deliver.biz.processor.nodereview;

import com.software.deliver.biz.dto.FlowNodeActionProcessParam;

public interface WorkFlowNodeActionProcessorBase {

    /**
     * 审批
     * @return
     */
    boolean doProcess(FlowNodeActionProcessParam param);


    /**
     * 处理审批动作类型
      * @return
     */
    int reviewType();

}
