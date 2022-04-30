package com.software.deliver.dal.mapper;

import com.software.deliver.dal.vo.WorkFlowInstanceVariableVO;

/**
 * @author wanghao
 * @version 1.0
 * @date 2022/4/24 23:00
 */
public interface WorkFlowInstanceVariableDao {//todo:wh待实现ddl.sql


    int create(WorkFlowInstanceVariableVO workFlowInstanceVariableVO);


    String getByVarName(Long workFlowInstanceId, String varName, Integer type);


}
