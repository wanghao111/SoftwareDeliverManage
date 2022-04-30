package com.software.deliver.dal.mapper;

import com.software.deliver.dal.vo.WorkFlowNodeVO;

import java.util.List;

/**
 * @author wanghao
 * @version 1.0
 * @date 2022/4/30 20:17
 */
public interface WorkFlowNodeDAO {

    int create(WorkFlowNodeVO workFlowNodeVO);

    List<WorkFlowNodeVO> batchGetByNodeCodes(String workFlowCode, List<String> nodeCodes);
}
