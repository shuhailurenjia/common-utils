package com.zwh.common.util;

import java.math.BigDecimal;

/**
 * @author shenweijie
 * @ClassName: BigDecimalUtils
 * @Description: 加减乘除，四舍五入
 * @date 2014年8月25日 下午5:06:29
 */
public class BigDecimalUtils {

    private BigDecimalUtils() {

    }

    /**
     * 提供精确的加法运算。
     *
     * @param v1 被加数
     * @param v2 加数
     * @return 两个参数的和
     */

    public static double add(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.add(b2).doubleValue();
    }

    public static BigDecimal add(BigDecimal v1, BigDecimal v2) {
        return v1.add(v2);
    }

    /**
     * 提供精确的减法运算。
     *
     * @param v1 被减数
     * @param v2 减数
     * @return 两个参数的差
     */

    public static double sub(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.subtract(b2).doubleValue();
    }

    public static BigDecimal sub(BigDecimal v1, BigDecimal v2) {
        return v1.subtract(v2);
    }

    /**
     * 提供精确的乘法运算。
     *
     * @param v1 被乘数
     * @param v2 乘数
     * @return 两个参数的积
     */

    public static double mul(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.multiply(b2).doubleValue();
    }

    public static BigDecimal mul(BigDecimal v1, BigDecimal v2) {
        return v1.multiply(v2);
    }


    /**
     * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指
     * 定精度，以后的数字四舍五入。如果小于0，则不进行四舍五入
     *
     * @param v1    被除数
     * @param v2    除数 （v1-v2）
     * @param scale 表示表示需要精确到小数点以后几位。
     * @return 两个参数的商
     */

    public static double div(double v1, double v2, int scale) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        BigDecimal b = div(b1, b2, scale, BigDecimal.ROUND_HALF_UP);
        return b.doubleValue();
    }

    /**
     * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指
     * 定精度，以后的数字四舍五入。如果小于0，则不进行四舍五入
     *
     * @param v1    被除数
     * @param v2    除数 （v1-v2）
     * @param scale 表示表示需要精确到小数点以后几位。
     * @return 两个参数的商
     */

    public static double divRoundUp(double v1, double v2, int scale) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        BigDecimal b = div(b1, b2, scale, BigDecimal.ROUND_UP);
        return b.doubleValue();
    }

    /**
     * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指
     * 定精度，以后的数字四舍五入。如果小于0，则不进行四舍五入
     *
     * @param v1           被减数
     * @param v2           减数（v1-v2）
     * @param scale        保留小数点
     * @param roundingMode 格式化：
     *                     1、ROUND_UP = 进位处理，2.35变成2.4
     *                     2、ROUND_DOWN = 直接删除多余的小数位，如2.35会变成2.3
     *                     4、ROUND_FLOOR = 如果 BigDecimal 为正，则作ROUND_UP；如果为负，则作ROUND_DOWN。
     *                     5、ROUND_HALF_UP = 四舍五入，2.35变成2.4
     *                     6、ROUND_HALF_DOWN = 四舍五入，2.35变成2.3，如果是5则向下舍
     * @return
     */
    private static BigDecimal div(BigDecimal v1, BigDecimal v2, int scale, int roundingMode) {
        if (scale < 0) {
            return v1.divide(v2);
        }
        return v1.divide(v2, scale, roundingMode);
    }


    /**
     * 提供精确的小数位四舍五入处理。
     *
     * @param v     需要四舍五入的数字
     * @param scale 小数点后保留几位
     * @return 四舍五入后的结果
     */

    public static double round(double v, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException(
                    "The   scale   must   be   a   positive   integer   or   zero");
        }
        BigDecimal b = new BigDecimal(Double.toString(v));
        BigDecimal one = new BigDecimal("1");
        return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    public static BigDecimal round(BigDecimal v, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException(
                    "The   scale   must   be   a   positive   integer   or   zero");
        }
        BigDecimal one = new BigDecimal("1");
        return v.divide(one, scale, BigDecimal.ROUND_HALF_UP);
    }

}
