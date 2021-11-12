package com.example.api.dto;

import java.io.Serializable;

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

    public String getHeadTraceId() {
        return headTraceId;
    }

    public void setHeadTraceId(String headTraceId) {
        this.headTraceId = headTraceId;
    }

    public String getHeadSequenceNo() {
        return headSequenceNo;
    }

    public void setHeadSequenceNo(String headSequenceNo) {
        this.headSequenceNo = headSequenceNo;
    }

    public String getHeadClientIp() {
        return headClientIp;
    }

    public void setHeadClientIp(String headClientIp) {
        this.headClientIp = headClientIp;
    }

    public String getHeadReqServerIp() {
        return headReqServerIp;
    }

    public void setHeadReqServerIp(String headReqServerIp) {
        this.headReqServerIp = headReqServerIp;
    }

    public String getHeadEquipmentId() {
        return headEquipmentId;
    }

    public void setHeadEquipmentId(String headEquipmentId) {
        this.headEquipmentId = headEquipmentId;
    }

    public String getHeadChannelId() {
        return headChannelId;
    }

    public void setHeadChannelId(String headChannelId) {
        this.headChannelId = headChannelId;
    }

    public String getHeadUserId() {
        return headUserId;
    }

    public void setHeadUserId(String headUserId) {
        this.headUserId = headUserId;
    }

    public Integer getHeadPageNum() {
        return headPageNum;
    }

    public void setHeadPageNum(Integer headPageNum) {
        this.headPageNum = headPageNum;
    }

    public Integer getHeadPageSize() {
        return headPageSize;
    }

    public void setHeadPageSize(Integer headPageSize) {
        this.headPageSize = headPageSize;
    }

    public String getHeadReqTime() {
        return headReqTime;
    }

    public void setHeadReqTime(String headReqTime) {
        this.headReqTime = headReqTime;
    }

    @Override
    public String toString() {
        return "HeadResp{" +
                "headTraceId='" + headTraceId + '\'' +
                ", headSequenceNo='" + headSequenceNo + '\'' +
                ", headClientIp='" + headClientIp + '\'' +
                ", headReqServerIp='" + headReqServerIp + '\'' +
                ", headEquipmentId='" + headEquipmentId + '\'' +
                ", headChannelId='" + headChannelId + '\'' +
                ", headUserId='" + headUserId + '\'' +
                ", headPageNum=" + headPageNum +
                ", headPageSize=" + headPageSize +
                ", headReqTime='" + headReqTime + '\'' +
                '}';
    }
}
