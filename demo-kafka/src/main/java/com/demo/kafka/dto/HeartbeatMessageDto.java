package com.demo.kafka.dto;

/**
 * 心跳消息模型
 * @author Liam(003046)
 * @date 2019/8/19 下午5:50
 */
public class HeartbeatMessageDto {

    private static final long serialVersionUID = -9093480576059962211L;
    private String edcode;
    /**柜机版本号*/
    private Integer appVersionCode;
    private long heartTime;
    private String appTime;

    /**
     * 柜机心跳报文序号.
     */
    private Long heartNum;

    /**
     * 当前接收心跳包次数.
     */
    private int heartCount;

    public String getEdcode() {
        return edcode;
    }

    public void setEdcode(String edcode) {
        this.edcode = edcode;
    }

    public Integer getAppVersionCode() {
        return appVersionCode;
    }

    public void setAppVersionCode(Integer appVersionCode) {
        this.appVersionCode = appVersionCode;
    }

    public long getHeartTime() {
        return heartTime;
    }

    public void setHeartTime(long heartTime) {
        this.heartTime = heartTime;
    }

    public String getAppTime() {
        return appTime;
    }

    public void setAppTime(String appTime) {
        this.appTime = appTime;
    }

    public Long getHeartNum() {
        return heartNum;
    }

    public void setHeartNum(Long heartNum) {
        this.heartNum = heartNum;
    }

    public int getHeartCount() {
        return heartCount;
    }

    public void setHeartCount(int heartCount) {
        this.heartCount = heartCount;
    }
}
