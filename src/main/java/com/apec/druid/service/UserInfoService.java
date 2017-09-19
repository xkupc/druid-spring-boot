package com.apec.druid.service;


import com.apec.druid.dto.UserInfoDTO;
import com.apec.druid.model.UserInfo;


public interface UserInfoService {
    UserInfo getUserInfo(UserInfoDTO dto);
}
