package com.software.deliver.dal.mapper;

import com.software.deliver.dal.vo.WorkFlowVariableVO;

/**
 * @author wanghao
 * @version 1.0
 * @date 2022/4/17 11:55
 */
public interface WorkFlowVariableDao {

    int create(WorkFlowVariableVO workFlowVariableVO);

    //todo:wh暂时只考虑一个varname一个value，后续可以考虑一个varname可以有多个value
    WorkFlowVariableVO getByVarName(Long workFlowInstanceId, String varName);

}
