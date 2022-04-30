package com.software.deliver.biz.factory;

import com.software.deliver.dal.vo.WorkFlowProgressOperateRecordVO;

import java.sql.Timestamp;

/**
 * @author wanghao
 * @version 1.0
 * @date 2022/4/30 21:57
 */
public class WorkFlowProgressRecordFactory {


    public static WorkFlowProgressOperateRecordVO create(String workFlowCode, String nodeCode, Long instanceId,
                                                         Integer executeType, Timestamp executeTime,
                                                         Long processUserId, String processMsg,
                                                         Integer isViewed, Timestamp viewTime, String nextNodeCode, String nextNodeUserIds
                                                         ) {
        return WorkFlowProgressOperateRecordVO.builder()
                .flowCode(workFlowCode)
                .nodeCode(nodeCode)
                .flowInstanceId(instanceId)
                .executeType(executeType)
//                .isExecuted()
                .executeTime(executeTime)
                .processUserId(processUserId)
                .processMsg(processMsg)
                .isViewed(isViewed)
                .viewTime(viewTime)
                .nextNodeCode(nextNodeCode)
                .nextNodeProcessUserIds(nextNodeUserIds)
                .build();
    }

}
