package com.software.deliver.dal.vo;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

/**
 * @author wanghao
 * @version 1.0
 * @date 2022/4/30 10:15
 */
@Data
@Builder
public class WorkFlowVO {

    private Long id;

    /**
     * 唯一标识
     */
    private String code;

    private String name;


    private Timestamp createdAt;

    private Timestamp updateAt;

}
