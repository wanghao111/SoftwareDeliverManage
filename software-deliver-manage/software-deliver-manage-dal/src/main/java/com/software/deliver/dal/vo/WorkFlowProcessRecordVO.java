package com.software.deliver.dal.vo;

import lombok.Data;

import java.sql.Timestamp;
/**
 * @author wanghao
 * @version 1.0
 * @date 2022/4/16 10:28
 */
@Data
public class WorkFlowProcessRecordVO {

    private long id;



    private Timestamp createdAt;

    private Timestamp updateAt;

}
