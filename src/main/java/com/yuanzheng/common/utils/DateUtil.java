package com.yuanzheng.common.utils;

import java.util.Date;

public class DateUtil {


    public static int getHours(Date begin, Date end) {
        return (int) ((end.getTime() - begin.getTime()) / (1000 * 60 * 60));
    }
}
