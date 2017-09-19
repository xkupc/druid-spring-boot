package com.apec.druid.mapper;

import com.apec.druid.dto.UserInfoDTO;
import com.apec.druid.model.UserInfo;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface UserInfoMapper {

    UserInfo findUserInfoById(UserInfoDTO dto);
}