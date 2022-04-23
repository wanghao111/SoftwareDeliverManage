package com.software.deliver.biz.acquirer;

import com.software.deliver.biz.dto.FlowNodeHandlerTypeProcessParam;

import java.util.List;

public interface WorkFlowNodeHandlerUserAcquirerBase {


    /**
     * 根据节点nodetype查询节点的处理人id
     * @return
     */
    List<Long> acquire(FlowNodeHandlerTypeProcessParam param);

    /**
     * 处理nodetype类型的节点
     * @return
     */
    int nodeType();

}
