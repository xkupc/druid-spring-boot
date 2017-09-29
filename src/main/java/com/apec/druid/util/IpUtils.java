package com.apec.druid.util;

import java.net.InetAddress;

/**
 * @author xk
 * @createTime 2017/9/29 0029 上午 9:51
 * @description ip处理
 */
public class IpUtils {

    public static String getLocalHostIp(){
        InetAddress address =null;
        try {
            address = InetAddress.getLocalHost();
            return address.getHostAddress();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
