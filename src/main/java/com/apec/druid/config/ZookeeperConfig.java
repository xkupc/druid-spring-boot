package com.apec.druid.config;

import com.alibaba.fastjson.JSONObject;
import com.apec.druid.model.ZookeeperProfile;
import com.apec.druid.service.impl.ZookeeperIdleafServiceImpl;
import com.apec.druid.util.IpUtils;
import com.apec.druid.util.SnowFlakeKeyGen;
import org.apache.curator.retry.RetryNTimes;
import org.assertj.core.util.Strings;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @author xk
 * @createTime 2017/9/28 0028 下午 3:23
 * @description 初始化zookeeper配置
 */
@Configuration
public class ZookeeperConfig {
    @Value("${ejzk.json}")
    String zkJson;
    @Value("${spring.profiles.active}")
    String environment;
    @Value("${server.port}")
    String serverPort;

    @Bean
    public ZookeeperProfile createZookeeperProfile() {
        JSONObject zkJsonObject = JSONObject.parseObject(zkJson);
        String rootNode = zkJsonObject.getString("rootNode");
        String connectString = zkJsonObject.getString("serverLists");
        String sequenceSpaceName = zkJsonObject.getString("namespace");
        int maxRetries = zkJsonObject.getInteger("maxRetries");
        int baseSleepTimeMilliseconds = zkJsonObject.getInteger("baseSleepTimeMilliseconds");
        ZookeeperProfile zookeeperProfile = new ZookeeperProfile(Strings.isNullOrEmpty(rootNode) ? "id_sequence" : rootNode, environment,
                connectString, sequenceSpaceName, new RetryNTimes(maxRetries, baseSleepTimeMilliseconds));
        zookeeperProfile.setServerPort(serverPort);
        zookeeperProfile.setIpAddress(IpUtils.getLocalHostIp());
        return zookeeperProfile;
    }

    @Bean
    public ZookeeperIdleafServiceImpl createZookeeperIdleafService() {
        return new ZookeeperIdleafServiceImpl(createZookeeperProfile());
    }
}
