package com.apec.druid.dto;


import org.springframework.util.StringUtils;

/**
 * 用户中心基础接口
 * Created by xk on 2017/6/30 0030.
 */
public class UserDTO extends BaseDTO {
    /**
     * 渠道号
     */
    private String channelId;

    /**
     * 平台号
     */
    private String plantformId;

    /**
     * 设备类型
     */
    private String deviceType;

    /**
     * 设备机型
     */
    private String deviceName;
    /**
     * 设备号
     */
    private String deviceNo;

    /**
     * 请求ip
     */
    private String ipAddress;
    /**
     * 应用版本
     */
    private String deviceAppVersion;
    /**
     * 设备操作系统版本
     */
    private String deviceSystemVersion;
    /**
     * 组织编码
     */
    private String oecdNo;

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = StringUtils.isEmpty(channelId) ? null : (StringUtils.isEmpty(channelId.trim()) ? null : channelId.trim());
    }

    public String getPlantformId() {
        return plantformId;
    }

    public void setPlantformId(String plantformId) {
        this.plantformId = StringUtils.isEmpty(plantformId) ? null : (StringUtils.isEmpty(plantformId.trim()) ? null : plantformId.trim());
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = StringUtils.isEmpty(deviceType) ? null : (StringUtils.isEmpty(deviceType.trim()) ? null : deviceType.trim());
        ;
    }

    public String getDeviceNo() {
        return deviceNo;
    }

    public void setDeviceNo(String deviceNo) {
        this.deviceNo = StringUtils.isEmpty(deviceNo) ? null : (StringUtils.isEmpty(deviceNo.trim()) ? null : deviceNo.trim());
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = StringUtils.isEmpty(ipAddress) ? null : (StringUtils.isEmpty(ipAddress.trim()) ? null : ipAddress.trim());
    }

    public String validate(String string) {
        return StringUtils.isEmpty(string) ? null : (StringUtils.isEmpty(string.trim()) ? null : string.trim());
    }

    public String getDeviceAppVersion() {
        return deviceAppVersion;
    }

    public void setDeviceAppVersion(String deviceAppVersion) {
        this.deviceAppVersion = validate(deviceAppVersion);
    }

    public String getDeviceSystemVersion() {
        return deviceSystemVersion;
    }

    public void setDeviceSystemVersion(String deviceSystemVersion) {
        this.deviceSystemVersion = validate(deviceSystemVersion);
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getOecdNo() {
        return oecdNo;
    }

    public void setOecdNo(String oecdNo) {
        this.oecdNo = oecdNo;
    }
}
