package com.apec.druid.util;


import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author xk
 * @createTime 2017/9/28 0028 下午 5:01
 * @description 处理生成的id长度
 */
public class IdSequenceUtils {

    private static final int ID_DEFAULT_LENGTH = 18;

    public static String dealLongId(Long idSequence){
        return dealLongId(idSequence,ID_DEFAULT_LENGTH);
    }

    private static String dealLongId(Long idSequence, int idLength) {
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyyMMdd");
        String datePrefix = simpleDateFormat.format(new Date());
        int numberLength = idLength - datePrefix.length();
        if (numberLength <= 0){
            throw new IllegalArgumentException("idLength is too small");
        }
        String seqStr = StringUtils.right(StringUtils.leftPad(String.valueOf(idSequence), numberLength, '0'), numberLength);
        return datePrefix + seqStr;
    }

}
