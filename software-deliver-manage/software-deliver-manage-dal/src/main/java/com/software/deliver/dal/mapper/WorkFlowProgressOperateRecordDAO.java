package com.software.deliver.dal.mapper;

import com.software.deliver.dal.vo.WorkFlowProgressOperateRecordVO;

/**
 * @author wanghao
 * @version 1.0
 * @date 2022/4/30 20:20
 */
public interface WorkFlowProgressOperateRecordDAO {

    int create(WorkFlowProgressOperateRecordVO workFlowProgressOperateRecordVO);

    WorkFlowProgressOperateRecordVO get(Long recordId);

    int update(WorkFlowProgressOperateRecordVO recordVO);
}
