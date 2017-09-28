package com.apec.druid.exception;

/**
 * @author xk
 * @createTime 2017/9/28 0028 下午 4:13
 * @description 定义生成id异常，明确异常原因
 */
public class IdSequenceException extends RuntimeException {

    public IdSequenceException(String message) {
        super(message);
    }

    public IdSequenceException(String message, Throwable e) {
        super(message, e);
    }
}
