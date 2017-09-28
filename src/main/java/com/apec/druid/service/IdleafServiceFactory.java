package com.apec.druid.service;

/**
 * @author xk
 * @createTime 2017/9/28 0028 上午 10:07
 * @description
 */
public interface IdleafServiceFactory {
    /**
     * 根据业务类型获取id
     *
     * @param bizTag
     * @return
     */
    public String getId(String bizTag);
}
