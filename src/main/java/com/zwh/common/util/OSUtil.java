package com.zwh.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;


/**
 * Created by Administrator on 2014/9/10.
 */
public class OSUtil {

    private static Logger logger = LoggerFactory.getLogger(OSUtil.class);
    private static List<InetAddress> list = null;


    /**
     * 判断当前操作是否Windows.
     *
     * @return true---是Windows操作系统
     */
    public static boolean isWindowsOS() {
        boolean isWindowsOS = false;
        String osName = System.getProperty("os.name");
        if (osName.toLowerCase().indexOf("windows") > -1) {
            isWindowsOS = true;
        }
        return isWindowsOS;
    }

    /**
     * 获取本机ip地址，并自动区分Windows还是linux操作系统
     *
     * @return String
     */
    public static InetAddress getLocalAddress() {
        InetAddress ip = null;
        try {
            //如果是Windows操作系统
            if (isWindowsOS()) {
                ip = InetAddress.getLocalHost();
            }
            //如果是Linux操作系统
            else {
                boolean bFindIP = false;
                Enumeration<NetworkInterface> netInterfaces = (Enumeration<NetworkInterface>) NetworkInterface.getNetworkInterfaces();
                while (netInterfaces.hasMoreElements()) {
                    if (bFindIP) {
                        break;
                    }
                    NetworkInterface ni = (NetworkInterface) netInterfaces.nextElement();
                    //----------特定情况，可以考虑用ni.getName判断
                    //遍历所有ip
                    Enumeration<InetAddress> ips = ni.getInetAddresses();
                    while (ips.hasMoreElements()) {
                        ip = (InetAddress) ips.nextElement();
                        if (ip.isSiteLocalAddress() && !ip.isLoopbackAddress()   //127.开头的都是lookback地址
                                && ip.getHostAddress().indexOf(":") == -1) {
                            bFindIP = true;
                            break;
                        }
                    }

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ip;
    }


    /**
     * 获取本机所有IP
     */
    public static List<InetAddress> getAllIPV4() {
        if (list == null) {
            list = new ArrayList<InetAddress>();
            // 根据网卡取本机配置的IP
            Enumeration<NetworkInterface> netInterfaces = null;
            try {
                netInterfaces = NetworkInterface.getNetworkInterfaces();
                while (netInterfaces.hasMoreElements()) {
                    NetworkInterface ni = netInterfaces.nextElement();
                    Enumeration<InetAddress> ips = ni.getInetAddresses();
                    while (ips.hasMoreElements()) {
                        InetAddress address = ips.nextElement();
                        if (!address.isAnyLocalAddress() && !address.isLoopbackAddress() && address.getHostAddress().indexOf("%") == -1) {
                            list.add(address);
                        }
                    }
                }
            } catch (Exception e) {
                logger.error("osutil", e);
                e.printStackTrace();
            }
        }
        return list;
    }

    /**
     * 获取本机所有IP
     */
    public static String getServerIP() {
        if (list == null) {
            getAllIPV4();
        }
        if (!list.isEmpty()) {
            boolean os = isWindowsOS();
            for (InetAddress address : list) {
                if (!os) {
                    if (!address.isSiteLocalAddress()) {
                        return address.getHostAddress();
                    }
                } else {
                    return address.getHostAddress();
                }

            }
        }
        return null;
    }

}
