package com.software.deliver.biz.achiever;

import com.software.deliver.biz.enums.BizExceptionEnum;
import com.software.deliver.biz.enums.NodeHandlerTypeEnum;
import com.software.deliver.biz.factory.BizExceptionFactory;
import com.software.deliver.dal.mapper.WorkFlowInstanceVarDAO;
import com.software.deliver.dal.vo.WorkFlowInstanceVarVO;
import com.software.deliver.dal.vo.WorkFlowNodeVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wanghao
 * @version 1.0
 * @date 2022/4/30 20:56
 */
@Slf4j
@Component
public class NodeProcessUserAchiever {

    public static final String USER_ID_SEP = ",";
    @Autowired
    private WorkFlowInstanceVarDAO workFlowInstanceVarDAO;


    public List<Long> doAchieve(Long flowInstanceId, Long preNodeProcessUserId, WorkFlowNodeVO workFlowNodeVO) throws Exception{
        List<Long> userIds = new ArrayList<>();
        Integer handlerType = workFlowNodeVO.getHandlerType();
        String nodeCode = workFlowNodeVO.getCode();
        String handlerVarName = workFlowNodeVO.getHandlerVarName();

        if (NodeHandlerTypeEnum.APPOINTOR.getCode().equals(handlerType)) {
            WorkFlowInstanceVarVO workFlowInstanceVarVO = workFlowInstanceVarDAO.get(flowInstanceId, nodeCode, handlerVarName);

            userIds.addAll(parseUserIds(workFlowInstanceVarVO.getValue()));
        } else if (NodeHandlerTypeEnum.SUPERVISOR.getCode().equals(handlerType)) {
            //todo:wh待组织机构模块实现

            userIds.add(10000L);
        } else {
            log.error("[NodeProcessUserAchiever][doAchieve]:node handler type={} not support", handlerType);
            BizExceptionFactory.build(BizExceptionEnum.NODE_HANDLER_TYPE_NOT_SUPPORT);
        }

        return userIds;
    }

    /**
     * 解析用户id列表
     * @param param id1,id2,id3
     * @return
     */
    private List<Long> parseUserIds(String param) {
        String[] split = param.split(USER_ID_SEP);
        List<Long> userIds = new ArrayList<>();
        for (int i = 0; i < split.length; i++) {
            userIds.add(Long.valueOf(split[i]));
        }
        return userIds;
    }
}
