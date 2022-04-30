package com.software.deliver.dal.vo;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

/**
 * @author wanghao
 * @version 1.0
 * @date 2022/4/30 11:35
 */
@Data
@Builder
public class FlowProcessRecordRelNextNodeVO {

    private Long id;

    private Long flowProcessRecodeId;

    private String nodeCode;

    private String nodeTitle;

    /**
     * 节点处理人
     * id1,id2,id3
     */
    private String handlerUserIds;

    private Timestamp createdAt;

    private Timestamp updateAt;


}
