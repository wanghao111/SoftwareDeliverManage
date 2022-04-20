package com.software.deliver.biz.processor.nodetype;

import com.software.deliver.biz.dto.FlowNodeHandlerTypeProcessParam;

import java.util.List;

public interface WorkFlowNodeHandlerTypeProcessorBase {


    /**
     * 根据节点nodetype查询节点的处理人id
     * @return
     */
    List<Long> doProcess(FlowNodeHandlerTypeProcessParam param);

    /**
     * 处理nodetype类型的节点
     * @return
     */
    int nodeType();

}
