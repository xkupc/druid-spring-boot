package com.apec.druid.elasticjob.proxy;

import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperConfiguration;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;

/**
 * @author xk
 * @createTime 2017/9/29 0029 下午 3:23
 * @description elastic-job zookeeper 注册代理类
 */
public class EJZkRegistryCenterProxy extends ZookeeperRegistryCenter {
    public EJZkRegistryCenterProxy(ZookeeperConfiguration zkConfig) {
        super(zkConfig);
        super.init();
    }
}
