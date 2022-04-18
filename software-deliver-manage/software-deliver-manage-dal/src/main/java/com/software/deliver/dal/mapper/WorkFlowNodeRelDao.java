package com.software.deliver.dal.mapper;

import com.software.deliver.dal.vo.WorkFlowNodeRelVO;
import com.software.deliver.dal.vo.WorkFlowNodeVO;

import java.util.List;

/**
 * @author wanghao
 * @version 1.0
 * @date 2022/4/16 16:58
 */
public interface WorkFlowNodeRelDao {

//    int create(WorkFlowNodeRelVO workFlowNodeRelVO);

    int batchInsert(List<WorkFlowNodeRelVO> workFlowNodeRelVOS);

    int deleteByWorkFlowId(Long workFlowId);

    List<WorkFlowNodeRelVO> getNextNodeRels(String workFlowNodeCode);

}
