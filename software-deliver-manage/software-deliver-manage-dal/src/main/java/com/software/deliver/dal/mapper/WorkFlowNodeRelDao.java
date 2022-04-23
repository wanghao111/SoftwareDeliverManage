package com.software.deliver.dal.mapper;

import com.software.deliver.dal.vo.WorkFlowNodeRelVO;
import com.software.deliver.dal.vo.WorkFlowNodeVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author wanghao
 * @version 1.0
 * @date 2022/4/16 16:58
 */
public interface WorkFlowNodeRelDao {


    int batchInsert(@Param("nodeRels") List<WorkFlowNodeRelVO> workFlowNodeRelVOS);

    int deleteByWorkFlowId(Long workFlowId);

    List<WorkFlowNodeRelVO> getNextNodeRels(Long workFlowId, String workFlowNodeCode);

    List<WorkFlowNodeRelVO> grePreNodeRels(Long workFlowId, String workFlowNodeCode);

}
