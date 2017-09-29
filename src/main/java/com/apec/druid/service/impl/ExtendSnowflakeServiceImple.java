package com.apec.druid.service.impl;

import com.apec.druid.service.IdleafService;

/**
 * @author xk
 * @createTime 2017/9/29 0029 上午 10:22
 * @description 优化雪花算法，通过zookeeper获取workid
 */
public class ExtendSnowflakeServiceImple implements IdleafService{
    @Override
    public String getId() {
        return null;
    }

    @Override
    public String getId(String name) {
        return null;
    }
}
