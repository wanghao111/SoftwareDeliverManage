package com.software.deliver.biz.processor.nodereview;

import com.software.deliver.biz.WorkFlowProgressService;
import com.software.deliver.biz.dto.FlowNodeActionProcessParam;
import com.software.deliver.biz.dto.FlowNodeHandlerTypeProcessParam;
import com.software.deliver.biz.enums.*;
import com.software.deliver.biz.factory.FlowHandlerTypeProcessorFactory;
import com.software.deliver.biz.model.WorkFlowProgress;
import com.software.deliver.biz.processor.nodetype.WorkFlowNodeHandlerTypeProcessorBase;
import com.software.deliver.dal.mapper.WorkFlowNodeDao;
import com.software.deliver.dal.mapper.WorkFlowNodeRelDao;
import com.software.deliver.dal.vo.WorkFlowNodeRelVO;
import com.software.deliver.dal.vo.WorkFlowNodeVO;
import com.software.deliver.dal.vo.WorkFlowProgressRelVO;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author wanghao
 * @version 1.0
 * @date 2022/4/19 22:16
 */
public class WorkFlowNodeApproveProcessor implements WorkFlowNodeActionProcessorBase{
    @Override
    public boolean doProcess(FlowNodeActionProcessParam param) {
        WorkFlowNodeRelDao workFlowNodeRelDao = param.getWorkFlowNodeRelDao();
        Long workFlowId = param.getWorkFlowId();
        String currentNodeCode = param.getWorkFlowNodeCode();
        Long currentWorkFlowInstanceId = param.getWorkFlowInstanceId();
        WorkFlowProgressService workFlowProgressService = param.getWorkFlowProgressService();

        //判断当前节点是否为最终节点
        WorkFlowNodeDao workFlowNodeDao = param.getWorkFlowNodeDao();
        WorkFlowNodeVO currentFlowNode = workFlowNodeDao.getByNodeCode(workFlowId, currentNodeCode);
        if (WorkFlowNodeSeqTypeEnum.LAST_NODE.getType().equals(currentFlowNode.getNodeSeqType())) {
            //todo:更新instance状态

            return true;
        }

        List<WorkFlowNodeRelVO> nextNodeRels = workFlowNodeRelDao.getNextNodeRels(workFlowId, currentNodeCode);
        List<String> relNodeCodes = Optional.ofNullable(nextNodeRels).orElse(new ArrayList<>()).stream().map(WorkFlowNodeRelVO::getRelWorkFlowNodeCode).collect(Collectors.toList());

        List<WorkFlowNodeVO> nextWorkFlowNodes = workFlowNodeDao.batchGetByNodeCodes(workFlowId, relNodeCodes);
        nextWorkFlowNodes.forEach(nextWorkFlowNodeVO -> {
            WorkFlowNodeHandlerTypeProcessorBase processorBase = FlowHandlerTypeProcessorFactory.build(nextWorkFlowNodeVO.getHandlerType());
            FlowNodeHandlerTypeProcessParam typeProcessParam = FlowNodeHandlerTypeProcessParam.builder()
                    .workFlowCode(param.getWorkFlowCode())
                    .workFlowId(param.getWorkFlowId())
                    .workFlowInstanceId(param.getWorkFlowInstanceId())
                    .workFlowNodeCode(nextWorkFlowNodeVO.getWorkFlowCode())
                    .reviewType(WorkFlowNodeReviewTypeEnum.APPROVE.getType())
                    .varName(nextWorkFlowNodeVO.getVarName())
                    .handlerUserId(nextWorkFlowNodeVO.getHandlerUserId())
                    .currentFlowProgressUserId(param.getCurrentFlowProgressUserId())
                    .currentFlowProgressId(param.getCurrentFlowProgressId())
                    .build();
            List<Long> userIds = processorBase.doProcess(typeProcessParam);

            List<WorkFlowProgressRelVO> workFlowProgressRelVOS = new ArrayList<>();
            if (WorkFlowNodeTypeEnum.COMMON_NODE.getType().equals(nextWorkFlowNodeVO.getNodeType()) ||
                    WorkFlowNodeTypeEnum.BRANCH_NODE.getType().equals(nextWorkFlowNodeVO.getNodeType()) ) {
                Optional.ofNullable(userIds).orElse(new ArrayList<>()).forEach(userId->{
                    WorkFlowProgress workFlowProgress = WorkFlowProgress.builder()
                            .workFlowId(param.getWorkFlowId())
                            .workFlowCode(param.getWorkFlowCode())
                            .workFlowInstanceId(param.getWorkFlowInstanceId())
                            .handlerUserId(userId)
                            .workFlowNodeCode(param.getWorkFlowNodeCode())
                            .status(WorkFlowProgressStatusEnum.NEED_PROCESS.getStatus())
                            .build();
                    workFlowProgressService.create(workFlowProgress);

                    WorkFlowProgressRelVO nextFlowProgressRelVO = WorkFlowProgressRelVO.builder()
                            .workFlowInstanceId(currentWorkFlowInstanceId)
                            .workFlowProgressId(param.getCurrentFlowProgressId())
                            .workFlowProgressRelId(workFlowProgress.getId())
                            .type(WorkFlowNodeRelTypeEnum.NEXT_NODE.getType())
                            .build();
                    WorkFlowProgressRelVO preFlowProgressRelVO = WorkFlowProgressRelVO.builder()
                            .workFlowInstanceId(currentWorkFlowInstanceId)
                            .workFlowProgressId(workFlowProgress.getId())
                            .workFlowProgressRelId(param.getCurrentFlowProgressId())
                            .type(WorkFlowNodeRelTypeEnum.PRE_NODE.getType())
                            .build();
                    workFlowProgressRelVOS.add(nextFlowProgressRelVO);
                    workFlowProgressRelVOS.add(preFlowProgressRelVO);
                });
            } else if (WorkFlowNodeTypeEnum.SUMMARY_NODE.getType().equals(nextWorkFlowNodeVO.getNodeType())) {
                List<WorkFlowNodeRelVO> preNodeRels = workFlowNodeRelDao.grePreNodeRels(workFlowId, nextWorkFlowNodeVO.getCode());
                List<String> preNodeCodes = Optional.ofNullable(preNodeRels).orElse(new ArrayList<>())
                        .stream()
                        .map(WorkFlowNodeRelVO::getRelWorkFlowNodeCode).collect(Collectors.toList());

                List<WorkFlowProgress> workFlowProgresses = workFlowProgressService.batchGetByNodeCode(workFlowId, preNodeCodes);
                List<WorkFlowProgress> aleadyAgreeProgresses = Optional.ofNullable(workFlowProgresses).orElse(new ArrayList<>()).stream()
                        .filter(workFlowProgress -> WorkFlowProgressStatusEnum.AGREE.getStatus().equals(workFlowProgress.getStatus())).collect(Collectors.toList());
                //所有父节点完成同意审批
                if (aleadyAgreeProgresses.size() == preNodeCodes.size()) {
                    Optional.ofNullable(userIds).orElse(new ArrayList<>()).forEach(userId->{
                        WorkFlowProgress workFlowProgress = WorkFlowProgress.builder()
                                .workFlowId(param.getWorkFlowId())
                                .workFlowCode(param.getWorkFlowCode())
                                .workFlowInstanceId(param.getWorkFlowInstanceId())
                                .handlerUserId(userId)
                                .workFlowNodeCode(param.getWorkFlowNodeCode())
                                .status(WorkFlowProgressStatusEnum.NEED_PROCESS.getStatus())
                                .build();
                        workFlowProgressService.create(workFlowProgress);

                        WorkFlowProgressRelVO nextFlowProgressRelVO = WorkFlowProgressRelVO.builder()
                                .workFlowInstanceId(currentWorkFlowInstanceId)
                                .workFlowProgressId(param.getCurrentFlowProgressId())
                                .workFlowProgressRelId(workFlowProgress.getId())
                                .type(WorkFlowNodeRelTypeEnum.NEXT_NODE.getType())
                                .build();
                        WorkFlowProgressRelVO preFlowProgressRelVO = WorkFlowProgressRelVO.builder()
                                .workFlowInstanceId(currentWorkFlowInstanceId)
                                .workFlowProgressId(workFlowProgress.getId())
                                .workFlowProgressRelId(param.getCurrentFlowProgressId())
                                .type(WorkFlowNodeRelTypeEnum.PRE_NODE.getType())
                                .build();
                        workFlowProgressRelVOS.add(nextFlowProgressRelVO);
                        workFlowProgressRelVOS.add(preFlowProgressRelVO);
                    });
                }
            }

            param.getWorkFlowProgressService().batchCreateFlowProgressRels(workFlowProgressRelVOS);
        });

        return true;
    }

    @Override
    public int reviewType() {
        return WorkFlowNodeActionEnum.APPROVE.getType();
    }
}
