package com.software.deliver.dal.mapper;

import com.software.deliver.dal.vo.WorkFlowInstanceVarVO;

/**
 * @author wanghao
 * @version 1.0
 * @date 2022/4/30 21:35
 */
public interface WorkFlowInstanceVarDAO {

    int create(WorkFlowInstanceVarVO workFlowInstanceVarVO);

    WorkFlowInstanceVarVO get(Long flowInstanceId, String nodeCode, String varName);

}
