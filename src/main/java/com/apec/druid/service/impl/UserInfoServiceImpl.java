package com.apec.druid.service.impl;


import com.apec.druid.dto.UserInfoDTO;
import com.apec.druid.mapper.UserInfoMapper;
import com.apec.druid.model.UserInfo;
import com.apec.druid.service.UserInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class UserInfoServiceImpl implements UserInfoService {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserInfoMapper mapper;

    @Override
    public UserInfo getUserInfo(UserInfoDTO dto) {
        return mapper.findUserInfoById(dto);
    }
}
