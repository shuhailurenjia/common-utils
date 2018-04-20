package com.zwh.common.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.util.Map;

public class HttpUtil {

    /**
     * http请求
     *
     * @param urlString       请求url
     * @param method          GET/POST
     * @param reqParams       请求参数
     * @param headParams      header setting
     * @param sendEncoding    发送字符集
     * @param receiveEncoding 接收字符集
     */
    public static String httpRequest(String urlString, String method, Map<String, String> reqParams, Map<String, String> headParams, String sendEncoding, String receiveEncoding) {
        return httpRequest(urlString, method, 5000, 5000, reqParams, headParams, sendEncoding, receiveEncoding);
    }

    /**
     * http请求
     *
     * @param urlString       请求url
     * @param method          GET/POST
     * @param connectTimeout  连接超时
     * @param readTimeout     读取超时
     * @param reqParams       请求参数
     * @param headParams      header setting
     * @param sendEncoding    发送字符集
     * @param receiveEncoding 接收字符集
     */
    public static String httpRequest(String urlString, String method, int connectTimeout, int readTimeout, Map<String, String> reqParams, Map<String, String> headParams, String sendEncoding,
                                     String receiveEncoding) {
        return httpRequest(urlString, method, connectTimeout, readTimeout, reqParams, headParams, sendEncoding, receiveEncoding, null, 0);
    }

    public static byte[] sendAndReceive(String urlString, String method, int connectTimeout, int readTimeout, Map<String, String> reqParams, Map<String, String> headParams, String sendEncoding,
                                        String receiveEncoding, byte[] body, String proxyIp, int proxyPort) {
        InputStream is = null;
        try {
            String param = getParams(reqParams);
            if (body == null) {
                if (method.equalsIgnoreCase("GET") && param.length() > 0) {
                    urlString += "?" + param;
                }
            }

//			CookieHandler.setDefault(new CookieManager(null, CookiePolicy.ACCEPT_ALL));
            URL url = new URL(urlString);
            HttpURLConnection conn = null;
            if (proxyIp != null) {
                Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyIp, proxyPort));
                conn = (HttpURLConnection) url.openConnection(proxy);
            } else {
                conn = (HttpURLConnection) url.openConnection();
            }
            conn.setRequestMethod(method);
            conn.setConnectTimeout(connectTimeout);
            conn.setReadTimeout(readTimeout);
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setUseCaches(false);
//			conn.setFollowRedirects(true);
//			conn.setInstanceFollowRedirects(true);

            setHeader(conn, headParams);
            conn.connect();
            if (method.equalsIgnoreCase("POST") && param.length() > 0) {
                conn.getOutputStream().write(param.getBytes(sendEncoding));
                conn.getOutputStream().flush();
                conn.getOutputStream().close();
            } else if (body != null) {
                conn.getOutputStream().write(body);
                conn.getOutputStream().flush();
                conn.getOutputStream().close();
            }

            is = conn.getInputStream();
            int iLen = -1;
            byte[] bytes = new byte[4096];
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            while ((iLen = is.read(bytes)) >= 0) {
                baos.write(bytes, 0, iLen);
            }
            return baos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            closeQuietly(is);
        }
        return new byte[0];
    }

    /**
     * http请求
     *
     * @param urlString       请求url
     * @param method          GET/POST
     * @param connectTimeout  连接超时
     * @param readTimeout     读取超时
     * @param reqParams       请求参数
     * @param headParams      header setting
     * @param sendEncoding    发送字符集
     * @param receiveEncoding 接收字符集
     * @param proxyIp         代理ip，不用代理时置为null
     * @param proxyPort       代理端口，不用代理时置为0
     */
    public static String httpRequest(String urlString, String method, int connectTimeout, int readTimeout, Map<String, String> reqParams, Map<String, String> headParams, String sendEncoding,
                                     String receiveEncoding, String proxyIp, int proxyPort) {
        String content = "";
        byte[] result = sendAndReceive(urlString, method, connectTimeout, readTimeout, reqParams, headParams, sendEncoding, receiveEncoding, null, proxyIp, proxyPort);
        try {
            content = new String(result, receiveEncoding);
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        return content;
    }

    public static byte[] httpRequestBytes2Bytes(String urlString, int connectTimeout, int readTimeout, Map<String, String> headParams, byte[] body, String proxyIp, int proxyPort) {
        return sendAndReceive(urlString, "POST", connectTimeout, readTimeout, null, headParams, "UTF-8", "UTF-8", body, proxyIp, proxyPort);
    }

    public static String httpRequestString2String(String urlString, int connectTimeout, int readTimeout, Map<String, String> headParams, String body, String sendEncoding, String receiveEncoding,
                                                  String proxyIp, int proxyPort) {
        String content = "";
        try {
            byte[] result = sendAndReceive(urlString, "POST", connectTimeout, readTimeout, null, headParams, "UTF-8", "UTF-8", body.getBytes(sendEncoding), proxyIp, proxyPort);
            content = new String(result, receiveEncoding);
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        return content;
    }

    private static void setHeader(HttpURLConnection conn, Map<String, String> headParams) {
        if (headParams == null) {
            return;
        }
        for (String key : headParams.keySet()) {
        	try{
            conn.addRequestProperty(key, headParams.get(key));
        	}catch(Exception e){
        		e.printStackTrace();
        	}
        }
    }

    private static String getParams(Map<String, String> reqParams) {
        StringBuffer param = new StringBuffer();
        if (reqParams != null) {
            int i = 0;
            for (String key : reqParams.keySet()) {
                if (i > 0) {
                    param.append("&");
                }
                param.append(key).append("=").append(reqParams.get(key));
                i++;
            }
        }
        return param.toString();
    }

    private static void closeQuietly(InputStream input) {
        try {
            if (input != null) {
                input.close();
            }
        } catch (IOException ioe) {
            // ignore
        }
    }
//
//    public static void main(String[] args) {
//
//    }
}
