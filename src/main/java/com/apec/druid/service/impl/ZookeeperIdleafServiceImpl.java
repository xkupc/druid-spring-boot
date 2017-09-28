package com.apec.druid.service.impl;

import com.apec.druid.exception.IdSequenceException;
import com.apec.druid.model.ZookeeperProfile;
import com.apec.druid.service.IdleafService;
import com.apec.druid.util.IdSequenceUtils;
import com.google.common.base.Strings;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.atomic.AtomicValue;
import org.apache.curator.framework.recipes.atomic.DistributedAtomicLong;
import org.apache.curator.framework.recipes.atomic.PromotedToLock;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.utils.ZKPaths;

import java.util.concurrent.TimeUnit;

/**
 * @author Administrator
 * @createTime 2017/9/28 0028 下午 4:18
 * @description
 */
public class ZookeeperIdleafServiceImpl implements IdleafService {

    private ZookeeperProfile zookeeperProfile;

    private CuratorFramework client;

    public ZookeeperIdleafServiceImpl(ZookeeperProfile zookeeperProfile) {
        this.zookeeperProfile = zookeeperProfile;
        client = CuratorFrameworkFactory.newClient(zookeeperProfile.getConnectString(), zookeeperProfile.getRetryPolicy());
        client.start();
    }

    @Override
    public String getId() {
        return null;
    }

    @Override
    public String getId(String name) {
        try {
            if (Strings.isNullOrEmpty(name)) {
                throw new IdSequenceException("id name is null");
            }
            String zookeeperIdNodePath = ZKPaths.makePath(zookeeperProfile.getBaseNode(), name);
            RetryPolicy retryPolicy = new ExponentialBackoffRetry(50, 29, 500);
            RetryPolicy lock_RetryPolicy = new ExponentialBackoffRetry(50, 10, 500);
            String lockPath = zookeeperIdNodePath + "_$lock";
            PromotedToLock.Builder builder = PromotedToLock.builder().lockPath(lockPath).timeout(100, TimeUnit.MILLISECONDS).retryPolicy(lock_RetryPolicy);
            DistributedAtomicLong dal = new DistributedAtomicLong(client, zookeeperIdNodePath, retryPolicy, builder.build());
            AtomicValue<Long> value = dal.increment();
            if (value.succeeded()) {
                return IdSequenceUtils.dealLongId(value.postValue());
            } else {
                throw new IdSequenceException("DistributedAtomicLong inclement fail.");
            }
        } catch (IdSequenceException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
