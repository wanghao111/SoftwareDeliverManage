package com.software.deliver.dal.mapper;

import com.software.deliver.dal.vo.WorkFlowNodeActionVO;

import java.util.List;

/**
 * @author wanghao
 * @version 1.0
 * @date 2022/4/30 20:17
 */
public interface WorkFlowNodeActionDAO {


    int create(WorkFlowNodeActionVO workFlowNodeActionVO);

    List<WorkFlowNodeActionVO> getActionType(String flowCode, String nodeCode, Integer actionType);
}
