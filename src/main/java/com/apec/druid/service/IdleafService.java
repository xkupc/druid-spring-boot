package com.apec.druid.service;

/**
 * @author xk
 * @createTime 2017/9/27 0027 上午 10:44
 * @description
 */
public interface IdleafService {
    /**
     * 获取默认id
     *
     * @return
     */
    public String getId();

    /**
     * 根据id名获取id
     * @param name
     * @return
     */
    public String getId(String name);
}
