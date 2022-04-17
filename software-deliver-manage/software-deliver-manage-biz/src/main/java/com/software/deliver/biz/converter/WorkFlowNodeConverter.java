package com.software.deliver.biz.converter;

import com.software.deliver.biz.enums.WorkFlowNodeRelTypeEnum;
import com.software.deliver.biz.model.WorkFlowAction;
import com.software.deliver.biz.model.WorkFlowNode;
import com.software.deliver.dal.vo.WorkFlowNodeActionVO;
import com.software.deliver.dal.vo.WorkFlowNodeRelVO;
import com.software.deliver.dal.vo.WorkFlowNodeVO;
import com.software.deliver.dal.vo.WorkFlowVO;

import java.util.List;
import java.util.Optional;

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
                .workFlowNodeCode(workFlowNode.getWorkFlowNodeCode())
                .workFlowId(workFlowNode.getWorkFlowId())
                .workFlowCode(workFlowNode.getWorkFlowCode())
                .workFlowNodeTitle(workFlowNode.getWorkFlowNodeTitle())
                .nodeType(workFlowNode.getNodeType())
                .isLastNode(workFlowNode.getIsLastNode())
                .isStartNode(workFlowNode.getIsStartNode())
                .isMiddleNode(workFlowNode.getIsMiddleNode())
                .handlerType(workFlowNode.getHandlerType())
                .summaryType(workFlowNode.getSummaryType())
                .varName(workFlowNode.getVarName())
                .build();

        List<Long> preWorkFlowNodeIds = workFlowNode.getPreWorkFlowNodeIds();
        List<Long> nextWorkFlowNodeIds = workFlowNode.getNextWorkFlowNodeIds();

        Optional.ofNullable(preWorkFlowNodeIds).ifPresent(preIds->preIds.forEach(preId->{
            WorkFlowNodeRelVO workFlowNodeRelVO = WorkFlowNodeRelVO.builder()
                    .workFlowNodeId(workFlowNode.getId())
                    .workFlowId(workFlowNode.getWorkFlowId())
                    .relWorkFlowNodeId(preId)
                    .type(WorkFlowNodeRelTypeEnum.PRE_NODE.getType())
                    .build();
            nodeRelVOS.add(workFlowNodeRelVO);
        }));

        Optional.ofNullable(nextWorkFlowNodeIds).ifPresent(nextIds->nextIds.forEach(nextId->{
            WorkFlowNodeRelVO workFlowNodeRelVO = WorkFlowNodeRelVO.builder()
                    .workFlowNodeId(workFlowNode.getId())
                    .relWorkFlowNodeId(nextId)
                    .type(WorkFlowNodeRelTypeEnum.NEXT_NODE.getType())
                    .build();
            nodeRelVOS.add(workFlowNodeRelVO);
        }));

        List<WorkFlowAction> workFlowActions = workFlowNode.getWorkFlowActions();
        Optional.ofNullable(workFlowActions).ifPresent(actions->actions.forEach(action->{
            WorkFlowNodeActionVO workFlowNodeActionVO = WorkFlowNodeActionVO.builder()
                    .id(action.getId())
                    .actionType(action.getActionType())
                    .workFlowNodeId(action.getWorkFlowNodeId())
                    .workFlowId(action.getWorkFlowId())
                    .build();
            workFlowNodeActionVOS.add(workFlowNodeActionVO);
        }));
        return workFlowNodeVO;
    }

}
