package com.apec.druid.model;

import java.util.Date;

public class IdSegment {
    private String bizTag;

    private Long maxId;

    private Long minId;

    private Long switchPoint;

    private Long pStep;

    private Date lastUpdateTime;

    private Date currentUpdateTime;

    public String getBizTag() {
        return bizTag;
    }

    public void setBizTag(String bizTag) {
        this.bizTag = bizTag == null ? null : bizTag.trim();
    }

    public Long getMaxId() {
        return maxId;
    }

    public void setMaxId(Long maxId) {
        this.maxId = maxId;
    }

    public Long getpStep() {
        return pStep;
    }

    public void setpStep(Long pStep) {
        this.pStep = pStep;
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public Date getCurrentUpdateTime() {
        return currentUpdateTime;
    }

    public void setCurrentUpdateTime(Date currentUpdateTime) {
        this.currentUpdateTime = currentUpdateTime;
    }

    public Long getMinId() {
        if (null == minId) {
            if (null != maxId && null != pStep) {
                minId = maxId - pStep;
            }
        }
        return minId;
    }

    public void setMinId(Long minId) {
        this.minId = minId;
    }

    public Long getSwitchPoint() {
        if (null == switchPoint) {
            switchPoint = maxId - (long) Math.round(pStep / 2);
        }
        return switchPoint;
    }

    public void setSwitchPoint(Long switchPoint) {
        this.switchPoint = switchPoint;
    }
}