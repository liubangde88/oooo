package com.yuanzheng.common.utils;

import java.math.BigDecimal;

public class DoubleUtil {


    public static Double formatDouble(Double x) {
        BigDecimal bg = new BigDecimal(x);
        Double y = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        return y;
    }

    public static Long getRandomCode() {
        return (long) ((Math.random() * 9 + 1) * 100000);
    }
}
