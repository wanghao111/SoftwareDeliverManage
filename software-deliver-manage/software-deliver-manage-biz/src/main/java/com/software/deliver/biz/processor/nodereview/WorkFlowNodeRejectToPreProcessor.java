package com.software.deliver.biz.processor.nodereview;

import com.software.deliver.biz.WorkFlowProgressService;
import com.software.deliver.biz.enums.WorkFlowNodeActionEnum;
import com.software.deliver.biz.dto.FlowNodeActionProcessParam;
import com.software.deliver.biz.enums.WorkFlowNodeRelTypeEnum;
import com.software.deliver.biz.enums.WorkFlowProgressStatusEnum;
import com.software.deliver.biz.model.WorkFlowProgress;
import com.software.deliver.dal.vo.WorkFlowProgressRelVO;
import com.software.deliver.dal.vo.WorkFlowProgressVO;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author wanghao
 * @version 1.0
 * @date 2022/4/19 22:18
 */
public class WorkFlowNodeRejectToPreProcessor implements WorkFlowNodeActionProcessorBase{
    @Override
    public boolean doProcess(FlowNodeActionProcessParam param) {
        //todo:wh待实现
        Long currentFlowProgressId = param.getCurrentFlowProgressId();
        WorkFlowProgressService workFlowProgressService = param.getWorkFlowProgressService();

        List<WorkFlowProgressRelVO> preFlowProgressRels = workFlowProgressService.batchGetFlowProgressRels(currentFlowProgressId,
                WorkFlowNodeRelTypeEnum.PRE_NODE.getType());

        List<Long> flowProgressRelIds = Optional.ofNullable(preFlowProgressRels).orElse(new ArrayList<>()).stream()
                .map(WorkFlowProgressRelVO::getWorkFlowProgressRelId).collect(Collectors.toList());
        List<WorkFlowProgress> workFlowProgresses = workFlowProgressService.batchGetFlowProgresses(flowProgressRelIds);
        List<WorkFlowProgress> needUpdateFlowProgresses = Optional.ofNullable(workFlowProgresses).orElse(new ArrayList<>())
                .stream()
                .peek(workFlowProgressVO -> workFlowProgressVO.setStatus(WorkFlowProgressStatusEnum.NEED_PROCESS.getStatus()))
                .collect(Collectors.toList());
        //todo:wh batchCreate progress record
        workFlowProgressService.batchUpdate(needUpdateFlowProgresses);
        return true;
    }

    @Override
    public int reviewType() {
        return WorkFlowNodeActionEnum.REJECT_TO_PRE.getType();
    }
}
