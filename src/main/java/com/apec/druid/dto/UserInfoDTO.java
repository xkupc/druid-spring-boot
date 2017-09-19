package com.apec.druid.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class UserInfoDTO extends UserDTO implements Serializable {
    private static final long serialVersionUID = -4868885937662449321L;

    private String userId;

    private String userName;

    private String userPasswd;

    private String userPhone;

    private String userTitle;

    private String customerId;

    private String erpNo;

    private String erpName;

    private String erpPasswd;

    private String openId;

    private String wechatNo;

    private String groupId;

    private String groupName;

    private String idcardNo;

    private String userAddress;

    private String companyName;

    private String bankAccount;

    private String provinceId;

    private String provinceName;

    private String cityId;

    private String cityName;

    private String businessAreaId;

    private String businessAreaName;

    private String marketerId;

    private String marketerName;

    private Integer userType;

    private String businessTypeId;

    private String businessTypeName;

    private String groupFlag;

    private String gender;

    private String userNickName;

    private String userHeadImageUrl;

    private String userRank;

    private String userRole;

    private Integer authType;

    private Integer authStatus;

    private Integer signAccountStatus;

    private String signUserId;

    private String sourceId;

    private String deviceId;

    private String status;

    private String remark;

    //创建时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createDate;

    private String enableFlag;

    //创建人
    private String createBy;

    //更新人
    private String lastUpdateBy;

    //更新时间
    @LastModifiedDate
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date lastUpdateDate;
    // 批量userId查询
    private List<String> userIdList;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = StringUtils.isEmpty(userId) ? null : (StringUtils.isEmpty(userId.trim()) ? null : userId.trim());
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = StringUtils.isEmpty(userName) ? null : (StringUtils.isEmpty(userName.trim()) ? null : userName.trim());
    }

    public String getUserPasswd() {
        return userPasswd;
    }

    public void setUserPasswd(String userPasswd) {
        this.userPasswd = StringUtils.isEmpty(userPasswd) ? null : (StringUtils.isEmpty(userPasswd.trim()) ? null : userPasswd.trim());
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = StringUtils.isEmpty(userPhone) ? null : (StringUtils.isEmpty(userPhone.trim()) ? null : userPhone.trim());
    }

    public String getUserTitle() {
        return userTitle;
    }

    public void setUserTitle(String userTitle) {
        this.userTitle = StringUtils.isEmpty(userTitle) ? null : (StringUtils.isEmpty(userTitle.trim()) ? null : userTitle.trim());
    }

    public String getErpNo() {
        return erpNo;
    }

    public void setErpNo(String erpNo) {
        this.erpNo = StringUtils.isEmpty(erpNo) ? null : (StringUtils.isEmpty(erpNo.trim()) ? null : erpNo.trim());
    }

    public String getErpName() {
        return erpName;
    }

    public void setErpName(String erpName) {
        this.erpName = StringUtils.isEmpty(erpName) ? null : (StringUtils.isEmpty(erpName.trim()) ? null : erpName.trim());
    }

    public String getErpPasswd() {
        return erpPasswd;
    }

    public void setErpPasswd(String erpPasswd) {
        this.erpPasswd = StringUtils.isEmpty(erpPasswd) ? null : (StringUtils.isEmpty(erpPasswd.trim()) ? null : erpPasswd.trim());
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = StringUtils.isEmpty(openId) ? null : (StringUtils.isEmpty(openId.trim()) ? null : openId.trim());
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = StringUtils.isEmpty(groupId) ? null : (StringUtils.isEmpty(groupId.trim()) ? null : groupId.trim());
    }

    public String getIdcardNo() {
        return idcardNo;
    }

    public void setIdcardNo(String idcardNo) {
        this.idcardNo = StringUtils.isEmpty(idcardNo) ? null : (StringUtils.isEmpty(idcardNo.trim()) ? null : idcardNo.trim());
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = StringUtils.isEmpty(userAddress) ? null : (StringUtils.isEmpty(userAddress.trim()) ? null : userAddress.trim());
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = StringUtils.isEmpty(bankAccount) ? null : (StringUtils.isEmpty(bankAccount.trim()) ? null : bankAccount.trim());
    }

    public String getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(String provinceId) {
        this.provinceId = StringUtils.isEmpty(provinceId) ? null : (StringUtils.isEmpty(provinceId.trim()) ? null : provinceId.trim());
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = StringUtils.isEmpty(provinceName) ? null : (StringUtils.isEmpty(provinceName.trim()) ? null : provinceName.trim());
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = StringUtils.isEmpty(cityId) ? null : (StringUtils.isEmpty(cityId.trim()) ? null : cityId.trim());
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = StringUtils.isEmpty(cityName) ? null : (StringUtils.isEmpty(cityName.trim()) ? null : cityName.trim());
    }

    public String getBusinessAreaId() {
        return businessAreaId;
    }

    public void setBusinessAreaId(String businessAreaId) {
        this.businessAreaId = StringUtils.isEmpty(businessAreaId) ? null : (StringUtils.isEmpty(businessAreaId.trim()) ? null : businessAreaId.trim());
    }

    public String getBusinessAreaName() {
        return businessAreaName;
    }

    public void setBusinessAreaName(String businessAreaName) {
        this.businessAreaName = StringUtils.isEmpty(businessAreaName) ? null : (StringUtils.isEmpty(businessAreaName.trim()) ? null : businessAreaName.trim());
    }

    public String getMarketerId() {
        return marketerId;
    }

    public void setMarketerId(String marketerId) {
        this.marketerId = StringUtils.isEmpty(marketerId) ? null : (StringUtils.isEmpty(marketerId.trim()) ? null : marketerId.trim());
    }

    public String getMarketerName() {
        return marketerName;
    }

    public void setMarketerName(String marketerName) {
        this.marketerName = StringUtils.isEmpty(marketerName) ? null : (StringUtils.isEmpty(marketerName.trim()) ? null : marketerName.trim());
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = validate(companyName);
    }

    public String getBusinessTypeId() {
        return businessTypeId;
    }

    public void setBusinessTypeId(String businessTypeId) {
        this.businessTypeId = validate(businessTypeId);
    }

    public String getBusinessTypeName() {
        return businessTypeName;
    }

    public void setBusinessTypeName(String businessTypeName) {
        this.businessTypeName = validate(businessTypeName);
    }

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = validate(sourceId);
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = validate(signUserId);
    }

    public String getGroupFlag() {
        return groupFlag;
    }

    public void setGroupFlag(String groupFlag) {
        this.groupFlag = StringUtils.isEmpty(groupFlag) ? null : (StringUtils.isEmpty(groupFlag.trim()) ? null : groupFlag.trim());
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = StringUtils.isEmpty(gender) ? null : (StringUtils.isEmpty(gender.trim()) ? null : gender.trim());
    }

    public Integer getAuthType() {
        return authType;
    }

    public void setAuthType(Integer authType) {
        this.authType = authType;
    }

    public Integer getAuthStatus() {
        return authStatus;
    }

    public void setAuthStatus(Integer authStatus) {
        this.authStatus = authStatus;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = StringUtils.isEmpty(groupName) ? null : (StringUtils.isEmpty(groupName.trim()) ? null : groupName.trim());
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getEnableFlag() {
        return enableFlag;
    }

    public void setEnableFlag(String enableFlag) {
        this.enableFlag = enableFlag;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getLastUpdateBy() {
        return lastUpdateBy;
    }

    public void setLastUpdateBy(String lastUpdateBy) {
        this.lastUpdateBy = lastUpdateBy;
    }

    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public List<String> getUserIdList() {
        return userIdList;
    }

    public void setUserIdList(List<String> userIdList) {
        this.userIdList = userIdList;
    }

    public Integer getSignAccountStatus() {
        return signAccountStatus;
    }

    public void setSignAccountStatus(Integer signAccountStatus) {
        this.signAccountStatus = signAccountStatus;
    }

    public String getSignUserId() {
        return signUserId;
    }

    public void setSignUserId(String signUserId) {
        this.signUserId = validate(signUserId);
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = validate(customerId);
    }

    public String getUserNickName() {
        return userNickName;
    }

    public void setUserNickName(String userNickName) {
        this.userNickName = validate(userNickName);
    }

    public String getUserHeadImageUrl() {
        return userHeadImageUrl;
    }

    public void setUserHeadImageUrl(String userHeadImageUrl) {
        this.userHeadImageUrl = userHeadImageUrl;
    }

    public String getUserRank() {
        return userRank;
    }

    public void setUserRank(String userRank) {
        this.userRank = validate(userRank);
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }
}
