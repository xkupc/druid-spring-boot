package com.apec.druid.controller;

import com.alibaba.fastjson.JSON;
import com.apec.druid.dto.UserInfoDTO;
import com.apec.druid.service.IdleafServiceFactory;
import com.apec.druid.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserInfoService userInfoService;
    @Autowired
    IdleafServiceFactory mysqlIdleafServiceFactory;

    @RequestMapping(value = "/getUserInfoByUserPhone", method = RequestMethod.GET)
    public String getUserInfo(@RequestParam("userPhone") String userPhone) {
        if (StringUtils.isEmpty(userPhone)) {
            return "参数异常";
        }
        UserInfoDTO dto = new UserInfoDTO();
        dto.setUserPhone(userPhone);
        dto.setStatus("Y");
        dto.setEnableFlag("Y");
        return JSON.toJSONString(userInfoService.getUserInfo(dto));
    }

    @RequestMapping(value = "/getIdByBizTag",method = RequestMethod.GET)
    public String getIdByBizTag(@RequestParam("bizTag")String bizTag){
        if (StringUtils.isEmpty(bizTag)){
            return "参数异常";
        }
        return mysqlIdleafServiceFactory.getId(bizTag);
    }
}
