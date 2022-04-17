package com.software.deliver.biz;

import com.software.deliver.biz.model.WorkFlow;

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
}
