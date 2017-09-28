package com.apec.druid.service;

import com.apec.druid.mapper.IdSegmentMapper;
import com.apec.druid.service.impl.MysqlIdleafServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author xk
 * @createTime 2017/9/28 0028 上午 9:52
 * @description
 */
@Component
public class MysqlIdleafServiceFactory implements IdleafServiceFactory {

    @Autowired
    private IdSegmentMapper idSegmentMapper;
    //存储各个业务系统的id生产器
    private static ConcurrentHashMap<String, IdleafService> idleafServiceConcurrentHashMap = new ConcurrentHashMap<>();

    @Override
    public String getId(String bizTag) {
        if (null == idleafServiceConcurrentHashMap.get(bizTag)) {
            synchronized (idleafServiceConcurrentHashMap) {
                if (null == idleafServiceConcurrentHashMap.get(bizTag)) {
                    MysqlIdleafServiceImpl idleafService = new MysqlIdleafServiceImpl();
                    idleafService.setIdSegmentMapper(idSegmentMapper);
                    idleafService.init(bizTag, true);
                    idleafServiceConcurrentHashMap.putIfAbsent(bizTag, idleafService);
                }
            }
        }
        return idleafServiceConcurrentHashMap.get(bizTag).getId();
    }

}
