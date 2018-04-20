package com.zwh.common.util;

import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.util.EntityUtils;

import net.sf.json.JSONObject;

/**
 * <br>
 * 类 名: HttpClientManager <br>
 * 描 述: http请求工具类 <br>
 * 作 者: <br>
 * 创 建： Sep 29, 2016 <br>
 * 版 本：v1.0.0 <br>
 * <br>
 * 历 史: (版本) 作者 时间 注释
 */
public class HttpClientManager {

    /** 默认请求超时时间 */
    private static int DEFAULT_REQ_TIMEOUT = 10000;
    /** 默认连接超时时间 */
    private static int DEFAULT_CONN_TIMEOUT = 10000;
    /** 默认传输超时时间 */
    private static int DEFAULT_SOC_TIMEOUT = 10000;

    @SuppressWarnings({ "deprecation", "resource" })
	public static JSONObject doPost(String url, JSONObject json) throws Exception {
    	DefaultHttpClient client = new DefaultHttpClient();
    	client.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, DEFAULT_CONN_TIMEOUT);
    	client.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, DEFAULT_SOC_TIMEOUT);
		HttpPost post = new HttpPost(url);
		JSONObject response = null;
			StringEntity s = new StringEntity(json.toString());
			s.setContentEncoding("UTF-8");
			s.setContentType("application/json");//发送json数据需要设置contentType
			post.setEntity(s);
			HttpResponse res = client.execute(post);
			if(res.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
				//HttpEntity entity = res.getEntity();
				String result = EntityUtils.toString(res.getEntity());// 返回json格式：
				response = JSONObject.fromObject(result);
			}
		return response;
    }
    
    /**
     * 采集系统post请求    内容加密放到body
     * @param url
     * @param str
     * @return
     * @throws Exception
     */
    public static String doPost(String url, String str) throws Exception {
    	DefaultHttpClient client = new DefaultHttpClient();
    	HttpPost post = new HttpPost(url);
    	String result = null;
    	StringEntity s = new StringEntity(str);
    	s.setContentEncoding("UTF-8");
    	s.setContentType("application/json");//发送json数据需要设置contentType
    	post.setEntity(s);
    	HttpResponse res = client.execute(post);
    	if(res.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
    		//HttpEntity entity = res.getEntity();
    		result = EntityUtils.toString(res.getEntity());// 返回json格式：
    	}
    	return result;
    }

    /**
     * post请求
     * 
     * @param url
     * @param param
     * @return
     * @throws Exception
     */
    public static String getPost(String url, Map<String, String> param, int req_timeout, int conn_timeout,
            int soc_timeout) throws Exception {
        return null;
    }

    public static <T> T getPost(String url, Map<String, String> param, int req_timeout, int conn_timeout,
            int soc_timeout, Class<T> c) throws Exception {
        return null;
    }

    public static String getPost(String url, Map<String, String> param) throws Exception {
        return null;
    }

    public static <T> T getPost(String url, Map<String, String> param, Class<T> c) throws Exception {
        return null;
    }

    /**
     * <br>
     * 描 述： <br>
     * 作 者： <br>
     * 历 史: (版本) 作者 时间 注释
     */
    public static String getPostForWebService(String wsdlUrl, String reqSoapXml) throws Exception {
        return null;
    }

    private static List<NameValuePair> getParam(Map<String, String> param) {
        return null;
    }
}
