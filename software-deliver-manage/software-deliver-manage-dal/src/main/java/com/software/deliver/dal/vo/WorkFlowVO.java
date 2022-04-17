package com.software.deliver.dal.vo;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

/**
 * @author wanghao
 * @version 1.0
 * @date 2022/4/16 10:28
 */
@Data
@Builder
public class WorkFlowVO {

    private long id;

    private String code;

    private String title;

    /**
     * 是否启用
     * 0:无意义
     * 1:启用
     * 2:禁用
     */
    private Integer isEnable;

    private Timestamp createdAt;

    private Timestamp updateAt;

}
