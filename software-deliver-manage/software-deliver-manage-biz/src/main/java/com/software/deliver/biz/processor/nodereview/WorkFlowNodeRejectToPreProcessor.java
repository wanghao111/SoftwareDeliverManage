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
        //从处理进度记录中获取当前节点的上一个节点的所有处理记录
        Long currentFlowProgressId = param.getCurrentProgress().getId();
        WorkFlowProgressService workFlowProgressService = param.getWorkFlowProgressService();

        List<WorkFlowProgressRelVO> preFlowProgressRels = workFlowProgressService.batchGetFlowProgressRels(currentFlowProgressId,
                WorkFlowNodeRelTypeEnum.PRE_NODE.getType());
        List<Long> flowProgressRelIds = Optional.ofNullable(preFlowProgressRels).orElse(new ArrayList<>()).stream()
                .map(WorkFlowProgressRelVO::getWorkFlowProgressRelId).collect(Collectors.toList());
        List<WorkFlowProgress> preFlowProgresses = workFlowProgressService.batchGetFlowProgresses(flowProgressRelIds);

        //为上一个节点处理记录人创建待处理处理进度实例，相当于流程返回，实际的flowprogress是一直往下走的，不能退回去，防止成环
        Optional.ofNullable(preFlowProgresses).orElse(new ArrayList<>()).forEach(workFlowProgress -> {
            workFlowProgress.setStatus(WorkFlowProgressStatusEnum.NEED_PROCESS.getStatus());
            workFlowProgress.setCreatedAt(null);
            workFlowProgress.setUpdateAt(null);
            workFlowProgress.setId(null);
            workFlowProgress.setHandlerMsg(null);
        });
        List<WorkFlowProgress> newPreFlowProgresses = workFlowProgressService.batchCreate(preFlowProgresses);

        List<WorkFlowProgressRelVO> workFlowProgressRelVOS = new ArrayList<>();
        Optional.ofNullable(newPreFlowProgresses).orElse(new ArrayList<>()).forEach(flowProgress->{
            WorkFlowProgressRelVO nextFlowProgressRelVO = WorkFlowProgressRelVO.builder()
                    .workFlowInstanceId(param.getCurrentProgress().getWorkFlowInstanceId())
                    .workFlowProgressId(currentFlowProgressId)
                    .workFlowProgressRelId(flowProgress.getId())
                    .type(WorkFlowNodeRelTypeEnum.NEXT_NODE.getType())
                    .build();

            WorkFlowProgressRelVO preFlowProgressRelVO = WorkFlowProgressRelVO.builder()
                    .workFlowInstanceId(param.getCurrentProgress().getWorkFlowInstanceId())
                    .workFlowProgressId(flowProgress.getId())
                    .workFlowProgressRelId(currentFlowProgressId)
                    .type(WorkFlowNodeRelTypeEnum.PRE_NODE.getType())
                    .build();
            workFlowProgressRelVOS.add(nextFlowProgressRelVO);
            workFlowProgressRelVOS.add(preFlowProgressRelVO);
        });

        param.getWorkFlowProgressService().batchCreateFlowProgressRels(workFlowProgressRelVOS);
        //todo:wh更新instance状态
        return true;
    }

    @Override
    public int reviewType() {
        return WorkFlowNodeActionEnum.REJECT_TO_PRE.getType();
    }
}
