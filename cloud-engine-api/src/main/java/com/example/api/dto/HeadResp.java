package com.example.api.dto;

import java.io.Serializable;

public abstract class HeadResp implements Serializable {

    private static final long serialVersionUID = -2651434529662567337L;

    /**
     * 请求链路号
     */
    private String headTraceId;

    /**
     * 请求流水号
     */
    private String headSequenceNo;

    /**
     * 分页总数
     */
    private Integer headTotalNum;

    /**
     *  请求响应码
     */
    private String headRespCode;

    /**
     * 请求响应提示
     */
    private String headRespMessage;

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

    public Integer getHeadTotalNum() {
        return headTotalNum;
    }

    public void setHeadTotalNum(Integer headTotalNum) {
        this.headTotalNum = headTotalNum;
    }

    public String getHeadRespCode() {
        return headRespCode;
    }

    public void setHeadRespCode(String headRespCode) {
        this.headRespCode = headRespCode;
    }

    public String getHeadRespMessage() {
        return headRespMessage;
    }

    public void setHeadRespMessage(String headRespMessage) {
        this.headRespMessage = headRespMessage;
    }

    @Override
    public String toString() {
        return "HeadResp{" +
                "headTraceId='" + headTraceId + '\'' +
                ", headSequenceNo='" + headSequenceNo + '\'' +
                ", headTotalNum=" + headTotalNum +
                ", headRespCode='" + headRespCode + '\'' +
                ", headRespMessage='" + headRespMessage + '\'' +
                '}';
    }
}
