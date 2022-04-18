package com.software.deliver.biz;

import com.software.deliver.biz.model.WorkFlow;
import com.software.deliver.biz.model.WorkFlowNode;
import com.software.deliver.dal.vo.WorkFlowNodeVO;

import java.util.List;

/**
 * @author wanghao
 * @version 1.0
 * @date 2022/4/16 17:03
 */
public interface WorkFlowService {


    int create(WorkFlow workFlow);

    int update(WorkFlow workFlow);

    int delete(Long workFlowId);

    WorkFlow getBriefByCode(String workFlowCode);

    WorkFlowNode getBriefStartNode(String workFlowCode);

    List<WorkFlowNode> getBriefNextNode(String workFlowNodeCode);
}
