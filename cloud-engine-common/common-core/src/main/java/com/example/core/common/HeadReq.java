package com.example.core.common;

import lombok.Data;

import java.io.Serializable;

@Data
public abstract class HeadReq implements Serializable {

    private static final long serialVersionUID = -2539579101528422094L;

    /**
     * 请求链路号
     */
    private String headTraceId;

    /**
     * 请求流水号
     */
    private String headSequenceNo;

    /**
     * 客户端ip
     */
    private String headClientIp;

    /**
     * 服务端ip
     */
    private String headReqServerIp;

    /**
     * 设备号
     */
    private String headEquipmentId;

    /**
     * 渠道号
     */
    private String headChannelId;

    /**
     * 用户id
     */
    private String headUserId;

    /**
     * 分页请求页数
     */
    private Integer headPageNum;

    /**
     * 分页请求记录条数
     */
    private Integer headPageSize;

    /**
     * 当前发送时间
     */
    private String headReqTime;

}
