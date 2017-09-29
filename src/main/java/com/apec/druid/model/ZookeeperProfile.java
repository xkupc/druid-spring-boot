package com.apec.druid.model;

import com.google.common.base.Strings;
import org.apache.curator.RetryPolicy;
import org.apache.curator.retry.RetryOneTime;
import org.apache.curator.utils.ZKPaths;
import org.springframework.stereotype.Component;

/**
 * @author xk
 * @createTime 2017/9/28 0028 下午 2:10
 * @description zookeeper的连接配置。/rooNode/environment/squenceSpace/组成一个序列号空间
 */

public class ZookeeperProfile {
    /**
     * 应用的ip
     */
    private String ipAddress;
    /**
     * 应用的端口
     */
    private String serverPort;
    /**
     * 配置的根目录
     */
    private String rootNode = "id_sequence";

    /**
     * 环境设置, 如dev, test. prod
     */
    private String environment;
    /**
     * zookeeper的连接地址; 如127.0.0.1:2181;
     * 多个时用逗号分开，如: 192.168.7.52:2181, 192.168.7.53:2181
     */
    private String connectString;

    /**
     * 序列号的空间， 一般使用当前的application名。
     * 一个空间分配的id是唯一的
     */
    private String sequenceSpaceName;

    /**
     * 重试的策略
     */
    private RetryPolicy retryPolicy;

    public ZookeeperProfile() {

    }

    public ZookeeperProfile(String rootNode, String environment, String connectString,
                            String sequenceSpaceName, RetryPolicy retryPolicy) {
        this.rootNode = rootNode;
        this.environment = environment;
        this.connectString = connectString;
        this.sequenceSpaceName = sequenceSpaceName;
        this.retryPolicy = retryPolicy;
    }

    public ZookeeperProfile(String rootNode, String connectString, String sequenceSpace, String environment) {
        //3秒重试一次
        this(rootNode, connectString, sequenceSpace, environment, new RetryOneTime(3000));
    }

    public String getBaseNode() {
        String path = "/";

        path = ZKPaths.makePath(path, rootNode);

        if (!Strings.isNullOrEmpty(sequenceSpaceName)) {
            path = ZKPaths.makePath(path, sequenceSpaceName);
        }

        if (!Strings.isNullOrEmpty(environment)) {
            path = ZKPaths.makePath(path, environment);
        }
        return path;
    }

    @Override
    public String toString() {
        return "ZookeeperProfile{" +
                "rootNode='" + rootNode + '\'' +
                ", environment='" + environment + '\'' +
                ", connectString='" + connectString + '\'' +
                ", sequenceSpaceName='" + sequenceSpaceName + '\'' +
                ", retryPolicy=" + retryPolicy +
                '}';
    }

    public String getRootNode() {
        return rootNode;
    }

    public void setRootNode(String rootNode) {
        this.rootNode = rootNode;
    }

    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    public String getConnectString() {
        return connectString;
    }

    public void setConnectString(String connectString) {
        this.connectString = connectString;
    }

    public String getSequenceSpaceName() {
        return sequenceSpaceName;
    }

    public void setSequenceSpaceName(String sequenceSpaceName) {
        this.sequenceSpaceName = sequenceSpaceName;
    }

    public RetryPolicy getRetryPolicy() {
        return retryPolicy;
    }

    public void setRetryPolicy(RetryPolicy retryPolicy) {
        this.retryPolicy = retryPolicy;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getServerPort() {
        return serverPort;
    }

    public void setServerPort(String serverPort) {
        this.serverPort = serverPort;
    }
}
