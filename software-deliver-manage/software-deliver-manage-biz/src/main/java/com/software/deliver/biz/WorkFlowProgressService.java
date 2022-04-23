package com.software.deliver.biz;

import com.software.deliver.biz.model.WorkFlowProgress;
import com.software.deliver.dal.vo.WorkFlowProgressRelVO;
import com.software.deliver.dal.vo.WorkFlowProgressVO;

import java.util.List;

/**
 * @author wanghao
 * @version 1.0
 * @date 2022/4/17 20:29
 */
public interface WorkFlowProgressService {

    int create(WorkFlowProgress workFlowProgress);

    List<WorkFlowProgress> batchCreate(List<WorkFlowProgress> workFlowProgresses);

    WorkFlowProgress getByFlowProgressId(Long workFlowProgressId);

    int update(WorkFlowProgress workFlowProgress);

    int batchUpdate(List<WorkFlowProgress> workFlowProgresses);

    List<WorkFlowProgress> batchGetByNodeCode(Long workFlowId, List<String> workFlowNodeCodes);

    int batchCreateFlowProgressRels(List<WorkFlowProgressRelVO> workFlowProgressRelVOS);

    List<WorkFlowProgressRelVO> batchGetFlowProgressRels(Long workFlowProgressId, Integer type);

    List<WorkFlowProgress> batchGetFlowProgresses(List<Long> workFlowProgressIds);

}
