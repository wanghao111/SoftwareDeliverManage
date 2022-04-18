package com.software.deliver.biz;

import com.software.deliver.biz.model.WorkFlowInstance;
import com.software.deliver.dal.vo.WorkFlowVariableVO;

/**
 * @author wanghao
 * @version 1.0
 * @date 2022/4/17 10:40
 */
public interface WorkFlowInstanceService {


    int create(WorkFlowInstance workFlowInstance);

    int createWorkFlowVariable(WorkFlowVariableVO workFlowVariableVO);
}
