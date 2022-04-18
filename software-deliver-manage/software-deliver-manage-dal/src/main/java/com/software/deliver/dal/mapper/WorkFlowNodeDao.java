package com.software.deliver.dal.mapper;

import com.software.deliver.dal.vo.WorkFlowNodeVO;
import com.software.deliver.dal.vo.WorkFlowVO;

import java.util.List;

/**
 * @author wanghao
 * @version 1.0
 * @date 2022/4/16 16:54
 */
public interface WorkFlowNodeDao {


//    int create(WorkFlowNodeVO workFlowNodeVO);

    int batchInsert(List<WorkFlowNodeVO> workFlowNodeVOS);

//    int delete(Long workFlowNodeId);

    int deleteByWorkFlowId(Long workFlowId);

//    int update(WorkFlowNodeVO workFlowNodeVO);

    WorkFlowNodeVO getStartNode(String workFlowCode);

    List<WorkFlowNodeVO> batchGetByNodeCodes(List<String> workFlowNodeCodes);


}
