package com.software.deliver.biz;

import com.software.deliver.biz.model.WorkFlowAprovalParam;
import com.software.deliver.dal.vo.WorkFlowProgressVO;

import java.util.List;

/**
 * @author wanghao
 * @version 1.0
 * @date 2022/4/30 20:24
 */
public interface WorkFlowService {

//    boolean createInstance(WorkFlowInstanceVO workFlowInstanceVO);

    boolean agree(WorkFlowAprovalParam workFlowAprovalParam);

    boolean goBack(WorkFlowAprovalParam workFlowAprovalParam);

    WorkFlowProgressVO getDetail(Long progressId);

    List<WorkFlowProgressVO> page();

}
