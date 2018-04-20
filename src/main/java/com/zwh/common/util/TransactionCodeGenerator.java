package com.zwh.common.util;

import java.net.InetAddress;

/**
 * 生成64位订单编号
 *
 * @version: V1.0
 */
public class TransactionCodeGenerator {

    private static final int IP;
    private static final int JVM = (int) (System.currentTimeMillis() >>> 8);
    private final static String sep = "";
    private static short counter = (short) 0;

    static {
        int ipadd;
        try {
            ipadd = IptoInt(InetAddress.getLocalHost().getAddress());
        } catch (Exception e) {
            ipadd = 0;
        }
        IP = ipadd;
    }

    public TransactionCodeGenerator() {
    }

    public static int IptoInt(byte[] bytes) {
        int result = 0;
        for (int i = 0; i < 4; i++) {
            result = (result << 8) - Byte.MIN_VALUE + (int) bytes[i];
        }
        return result;
    }

    public static int getJVM() {
        return JVM;
    }

    public static short getCount() {
        synchronized (TransactionCodeGenerator.class) {
            if (counter < 0) {
                counter = 0;
            }
            return counter++;
        }
    }

    public static int getIP() {
        return IP;
    }

    public static short getHiTime() {
        return (short) (System.currentTimeMillis() >>> 32);
    }

    public static int getLoTime() {
        return (int) System.currentTimeMillis();
    }

    public static String format(int intval) {
        String formatted = Integer.toHexString(intval);
        StringBuffer buf = new StringBuffer("00000000");
        buf.replace(8 - formatted.length(), 8, formatted);
        return buf.toString();
    }

    public static String format(short shortval) {
        String formatted = Integer.toHexString(shortval);
        StringBuffer buf = new StringBuffer("0000");
        buf.replace(4 - formatted.length(), 4, formatted);
        return buf.toString();
    }

    public static String generate() {
        return String.valueOf(
                new StringBuffer(36).append(format(getIP())).append(sep).append(format(getJVM())).append(sep).append(format(getHiTime())).append(sep).append(format(getLoTime())).append(sep)
                        .append(format(getCount())).toString());
    }

//    public static void main(String args[]) {
//
//        CCC c = new CCC();
//        for (int i = 0; i < 10; i++) {
//            new Thread(c, i + "").start();
//        }
//    }

    public static class CCC implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                System.out.println(TransactionCodeGenerator.generate());
            }
        }
    }
}
