package com.software.deliver.biz.factory;

import com.software.deliver.dal.vo.WorkFlowProgressVO;

/**
 * @author wanghao
 * @version 1.0
 * @date 2022/4/30 21:53
 */
public class WorkFlowProgressFactory {



    public static WorkFlowProgressVO create(String flowCode, Long instanceId, String nodeCode,
                                            Long processUserId, String processMsg,Long progressOptRecordId,
                                            Integer executeType, Integer term, Integer backNo) {
        return WorkFlowProgressVO.builder()
                .flowCode(flowCode)
                .flowInstanceId(instanceId)
                .nodeCode(nodeCode)
                .processUserId(processUserId)
                .processMsg(processMsg)
                .executeType(executeType)
                .progressOptRecordId(progressOptRecordId)
                .term(term)
                .backNo(backNo)
                .build();

    }

}
