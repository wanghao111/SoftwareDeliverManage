package com.software.deliver.biz.converter;

import com.software.deliver.biz.enums.WorkFlowNodeRelTypeEnum;
import com.software.deliver.biz.model.WorkFlowAction;
import com.software.deliver.biz.model.WorkFlowNode;
import com.software.deliver.dal.vo.WorkFlowNodeActionVO;
import com.software.deliver.dal.vo.WorkFlowNodeRelVO;
import com.software.deliver.dal.vo.WorkFlowNodeVO;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author wanghao
 * @version 1.0
 * @date 2022/4/16 18:08
 */
public class WorkFlowNodeConverter {

    public static void flowNodes2NodeVOs(List<WorkFlowNode> flowNodes, List<WorkFlowNodeVO> flowNodeVOS,
                                         List<WorkFlowNodeRelVO> flowNodeRelVOS, List<WorkFlowNodeActionVO> workFlowNodeActionVOS) {
        Optional.ofNullable(flowNodes).ifPresent(nodes -> nodes.forEach(workFlowNode -> {
            WorkFlowNodeVO workFlowNodeVO = flowNode2NodeVO(workFlowNode, flowNodeRelVOS, workFlowNodeActionVOS);
            flowNodeVOS.add(workFlowNodeVO);
        }));
    }

    public static WorkFlowNodeVO flowNode2NodeVO(WorkFlowNode workFlowNode, List<WorkFlowNodeRelVO> nodeRelVOS,
                                                 List<WorkFlowNodeActionVO> workFlowNodeActionVOS) {
        WorkFlowNodeVO workFlowNodeVO = WorkFlowNodeVO.builder()
                .id(workFlowNode.getId())
                .code(workFlowNode.getWorkFlowNodeCode())
                .workFlowId(workFlowNode.getWorkFlowId())
                .workFlowCode(workFlowNode.getWorkFlowCode())
                .nodeTitle(workFlowNode.getWorkFlowNodeTitle())
                .nodeType(workFlowNode.getNodeType())
                .nodeSeqType(workFlowNode.getNodeSeqType())
                .handlerType(workFlowNode.getHandlerType())
                .summaryType(workFlowNode.getSummaryType())
                .varName(workFlowNode.getVarName())
                .handlerUserId(workFlowNode.getHandlerUserId())
                .build();

        List<String> preWorkFlowNodeCodes = workFlowNode.getPreWorkFlowNodeCodes();
        List<String> nextWorkFlowNodeCodes = workFlowNode.getNextWorkFlowNodeCodes();

        Optional.ofNullable(preWorkFlowNodeCodes).ifPresent(preCodes->preCodes.forEach(preCode->{
            WorkFlowNodeRelVO workFlowNodeRelVO = WorkFlowNodeRelVO.builder()
                    .workFlowNodeId(workFlowNode.getId())
                    .workFlowId(workFlowNode.getWorkFlowId())
                    .relWorkFlowNodeCode(preCode)
                    .type(WorkFlowNodeRelTypeEnum.PRE_NODE.getType())
                    .build();
            nodeRelVOS.add(workFlowNodeRelVO);
        }));

        Optional.ofNullable(nextWorkFlowNodeCodes).ifPresent(nextCodes->nextCodes.forEach(nextCode->{
            WorkFlowNodeRelVO workFlowNodeRelVO = WorkFlowNodeRelVO.builder()
                    .workFlowNodeId(workFlowNode.getId())
                    .relWorkFlowNodeCode(nextCode)
                    .type(WorkFlowNodeRelTypeEnum.NEXT_NODE.getType())
                    .build();
            nodeRelVOS.add(workFlowNodeRelVO);
        }));

        List<WorkFlowAction> workFlowActions = workFlowNode.getWorkFlowActions();
        Optional.ofNullable(workFlowActions).ifPresent(actions->actions.forEach(action->{
            WorkFlowNodeActionVO workFlowNodeActionVO = WorkFlowNodeActionVO.builder()
                    .id(action.getId())
                    .actionType(action.getActionType())
                    .workFlowNodeCode(action.getWorkFlowNodeCode())
                    .workFlowId(action.getWorkFlowId())
                    .reviewType(action.getReviewType())
                    .build();
            workFlowNodeActionVOS.add(workFlowNodeActionVO);
        }));
        return workFlowNodeVO;
    }

    public static WorkFlowNode WorkFlowNodeVO2WorkFlowNode(WorkFlowNodeVO workFlowNodeVO) {
        WorkFlowNode workFlowNode = WorkFlowNode.builder()
                .id(workFlowNodeVO.getId())
                .workFlowNodeCode(workFlowNodeVO.getCode())
                .nodeSeqType(workFlowNodeVO.getNodeSeqType())
                .handlerType(workFlowNodeVO.getHandlerType())
                .summaryType(workFlowNodeVO.getSummaryType())
                .nodeType(workFlowNodeVO.getNodeType())
                .workFlowId(workFlowNodeVO.getWorkFlowId())
                .workFlowCode(workFlowNodeVO.getWorkFlowCode())
                .workFlowNodeTitle(workFlowNodeVO.getNodeTitle())
                .varName(workFlowNodeVO.getVarName())
                .handlerUserId(workFlowNodeVO.getHandlerUserId())
                .build();
        return workFlowNode;
    }

    public static List<WorkFlowNode> WorkFlowNodeVOs2WorkFlowNodes(List<WorkFlowNodeVO> workFlowNodeVOS) {
        return Optional.ofNullable(workFlowNodeVOS)
                .orElse(new ArrayList<>())
                .stream().map(WorkFlowNodeConverter::WorkFlowNodeVO2WorkFlowNode)
                .collect(Collectors.toList());
    }

}
