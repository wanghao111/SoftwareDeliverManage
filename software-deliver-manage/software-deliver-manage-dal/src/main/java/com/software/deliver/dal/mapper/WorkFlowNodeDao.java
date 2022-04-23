package com.software.deliver.dal.mapper;

import com.software.deliver.dal.vo.WorkFlowNodeVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author wanghao
 * @version 1.0
 * @date 2022/4/16 16:54
 */
public interface WorkFlowNodeDao {


//    int create(WorkFlowNodeVO workFlowNodeVO);

    int batchInsert(@Param("workFlowNodeVOs") List<WorkFlowNodeVO> workFlowNodeVOS);


    int deleteByWorkFlowId(Long workFlowId);


    WorkFlowNodeVO getStartNodeByFlowCode(String workFlowCode);

    List<WorkFlowNodeVO> batchGetByNodeCodes(Long workFlowId, @Param("nodeCodes") List<String> workFlowNodeCodes);

    WorkFlowNodeVO getByNodeCode(Long workFlowId, String workFlowNodeCode);


}
