package com.zwh.common.util;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.json.JSONObject;

public class HttpclientProxy {

	private static final int ConnectionTimeOut = 5000;
	private static final int SOTimeOut = 10000;
	private static final int AP = Runtime.getRuntime().availableProcessors();
	private static final int DefaultMaxConPerHost = AP > 1 ? AP * 10 : 20;
	private static final int DefaultMaxCon = DefaultMaxConPerHost * 10;
	private static Logger log = LoggerFactory.getLogger(HttpclientProxy.class);
	public static MultiThreadedHttpConnectionManager connectionManager;
	private static String execGETMethodJSONSoTimeout;
	static {
		connectionManager = new MultiThreadedHttpConnectionManager();
		connectionManager.getParams().setConnectionTimeout(ConnectionTimeOut);
		connectionManager.getParams().setSoTimeout(SOTimeOut);
		connectionManager.getParams().setDefaultMaxConnectionsPerHost(DefaultMaxConPerHost);
		connectionManager.getParams().setMaxTotalConnections(DefaultMaxCon);
		log.info("ConnectionTimeOut:{}", ConnectionTimeOut);
		log.info("SOTimeOut:{}", SOTimeOut);
		log.info("DefaultMaxConPerHost:{}", DefaultMaxConPerHost);
		log.info("DefaultMaxCon:{}", DefaultMaxCon);
	}

	public HttpclientProxy() {

	}

	/**
	 * 发送get请求
	 * 
	 * @param url
	 *            路径
	 * @return
	 */
	public static JSONObject httpGet(String url) {
		// get请求返回结果
		JSONObject jsonResult = null;
		try {
			CloseableHttpClient client = HttpClients.createDefault();
			// DefaultHttpClient client = new DefaultHttpClient();
			// 发送get请求
			HttpGet request = new HttpGet(url);
			HttpResponse response = client.execute(request);

			/** 请求发送成功，并得到响应 **/
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				/** 读取服务器返回过来的json字符串数据 **/
				String strResult = EntityUtils.toString(response.getEntity());
				strResult = new String(strResult.getBytes("iso8859-1"), "UTF-8");
				/** 把json字符串转换成json对象 **/
				jsonResult = JSONObject.fromObject(strResult);

				url = URLDecoder.decode(url, "UTF-8");
			} else {
				log.error("get请求提交失败:" + url);
			}
		} catch (Exception e) {
			log.error("get请求提交失败:" + url, e);
		}
		return jsonResult;
	}

	/**
	 * GET多个参数
	 * 
	 * @param url
	 * @param parames
	 * @return
	 * @throws HttpException
	 * @throws IOException
	 */
	public static String execGETMethod(String url, Map<String, String> parames) throws HttpException, IOException {
		// 构造HttpClient的实例
		HttpClient httpClient = new HttpClient(connectionManager);
		GetMethod getMethod = null;
		StringBuilder paramsStr = new StringBuilder();
		// 设置参数
		if (parames != null && parames.size() > 0) {
			for (Map.Entry<String, String> entry : parames.entrySet()) {
				paramsStr.append(entry.getKey()).append("=").append(URLEncoder.encode(entry.getValue(), "UTF-8"))
						.append("&");
			}
		}
		// 创建GET方法的实例
		getMethod = getMethod(url, paramsStr.toString());
		// 使用系统提供的默认的恢复策略
		getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
		// 执行getMethod
		int statusCode = httpClient.executeMethod(getMethod);
		if (statusCode != HttpStatus.SC_OK) {
			log.info("request url" + url + "   Method failed: " + getMethod.getStatusLine());
		}
		// 读取内容
		byte[] responseBody = getMethod.getResponseBody();
		// 处理内容
		getMethod.releaseConnection();

		return new String(responseBody, "UTF-8");
	}

	/**
	 * restful url
	 * 
	 * @param url
	 * @return
	 * @throws HttpException
	 * @throws IOException
	 */
	public static String execGETResrful(String url) throws HttpException, IOException {
		// 构造HttpClient的实例
		HttpClient httpClient = new HttpClient(connectionManager);
		// 创建GET方法的实例
		GetMethod getMethod = new GetMethod(url);
		// 使用系统提供的默认的恢复策略
		getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
		// 执行getMethod
		int statusCode = httpClient.executeMethod(getMethod);
		if (statusCode != HttpStatus.SC_OK) {
			log.info("request url" + url + "   Method failed: " + getMethod.getStatusLine());
		}
		// 读取内容
		byte[] responseBody = getMethod.getResponseBody();
		// 处理内容
		getMethod.releaseConnection();

		return new String(responseBody, "UTF-8");
	}

	/**
	 * POST多个参数
	 * 
	 * @param url
	 * @param parames
	 * @return
	 * @throws IOException
	 * @throws HttpException
	 */
	public static String execPOSTMethodMParames(String url, Map<String, String> parames)
			throws HttpException, IOException {
		StringBuilder reponseStr = new StringBuilder();
		// 构造HttpClient的实例
		HttpClient httpClient = new HttpClient(connectionManager);
		PostMethod postMethod = new PostMethod(url);
		postMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
		postMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());

		// 填入各个表单域的值
		/*
		 * NameValuePair[] data = { new NameValuePair("id", "youUserName"), new
		 * NameValuePair("passwd", "yourPwd") };
		 */
		// 将表单的值放入postMethod中
		// 设置参数
		if (parames.size() > 0) {
			NameValuePair[] data = new NameValuePair[parames.size()];
			int parameLenth = 0;
			for (Map.Entry<String, String> entry : parames.entrySet()) {
				data[parameLenth] = new NameValuePair(entry.getKey(), StringUtils.objectToString(entry.getValue()));
				parameLenth += 1;
			}
			postMethod.setRequestBody(data);
		}
		// 执行postMethod
		int statusCode = httpClient.executeMethod(postMethod);
		if (statusCode != HttpStatus.SC_OK) {
			log.info("request url" + url + "   Method failed: " + postMethod.getStatusLine());
		}
		// 读取内容
		byte[] responseBody = postMethod.getResponseBody();
		reponseStr.append(new String(responseBody));
		postMethod.releaseConnection();
		// HttpClient对于要求接受后继服务的请求，象POST和PUT等不能自动处理转发
		// 301或者302
		/*
		 * if (statusCode == HttpStatus.SC_MOVED_PERMANENTLY || statusCode ==
		 * HttpStatus.SC_MOVED_TEMPORARILY) { // 从头中取出转向的地址 Header locationHeader =
		 * postMethod.getResponseHeader("location"); String location = null; if
		 * (locationHeader != null) { location = locationHeader.getValue();
		 * System.out.println("The page was redirected to:" + location); } else {
		 * System.err.println("Location field value is null."); } return; }
		 */
		return reponseStr.toString();
	}

	/**
	 * POST单个内容
	 * 
	 * @param url
	 * @param paramKey
	 *            TODO
	 * @param paramValue
	 * @return
	 * @throws HttpException
	 * @throws IOException
	 */
	@SuppressWarnings("deprecation")
	public static String execPOSTMethodParames(String url, String paramKey, String paramValue)
			throws HttpException, IOException {
		StringBuilder reponseStr = new StringBuilder();
		// 构造HttpClient的实例
		HttpClient httpClient = new HttpClient(connectionManager);
		httpClient.getParams().setParameter("http.protocol.content-charset", "UTF-8");
		httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(6000);
		httpClient.getHttpConnectionManager().getParams().setSoTimeout(10000);
		PostMethod postMethod = new PostMethod(url);
		postMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
		postMethod.setParameter(paramKey, paramValue);
		// 执行postMethod
		int statusCode = httpClient.executeMethod(postMethod);// 400
		if (statusCode != HttpStatus.SC_OK) {
			log.info("execPOSTMethodParames failed: " + postMethod.getStatusLine());
			log.info(new String(postMethod.getResponseBody()));
			return null;
		}
		log.info("http status: " + statusCode);
		// 读取内容
		byte[] responseBody = postMethod.getResponseBody();
		reponseStr.append(new String(responseBody));
		postMethod.releaseConnection();
		return reponseStr.toString();
	}

	/**
	 * POST单个内容(提现)
	 * 
	 * @param url
	 * @param strContent
	 * @return
	 * @throws HttpException
	 * @throws IOException
	 */
	@SuppressWarnings("deprecation")
	public static String execPOSTMethodParamesSoTimeout(String url, String strContent, int soTimeout, int conTimeout)
			throws HttpException, IOException {
		StringBuilder reponseStr = new StringBuilder();
		// 构造HttpClient的实例
		HttpClient httpClient = new HttpClient(connectionManager);
		httpClient.getParams().setParameter("http.protocol.content-charset", "UTF-8");
		httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(conTimeout);
		httpClient.getHttpConnectionManager().getParams().setSoTimeout(soTimeout);
		PostMethod postMethod = new PostMethod(url);
		postMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
		postMethod.setRequestBody(strContent);
		// 执行postMethod
		int statusCode = httpClient.executeMethod(postMethod);
		if (statusCode != HttpStatus.SC_OK) {
			log.info("Method failed: " + postMethod.getStatusLine());
			log.info(new String(postMethod.getResponseBody()));
			return null;
		}
		log.info("http status: " + statusCode);
		// 读取内容
		byte[] responseBody = postMethod.getResponseBody();
		reponseStr.append(new String(responseBody, "UTF-8"));
		postMethod.releaseConnection();
		return reponseStr.toString();
	}

	/**
	 * POST多个参数
	 * 
	 * @param url
	 * @param xmlString
	 * @return
	 * @throws IOException
	 * @throws HttpException
	 */
	public static String execPOSTMethodXml(String url, String xmlString) throws HttpException, IOException {
		HttpClient httpclient = new HttpClient();
		PostMethod post = new PostMethod(url);
		String info = null;
		try {
			RequestEntity entity = new StringRequestEntity(xmlString, "text/xml", "utf-8");
			post.setRequestEntity(entity);
			post.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "utf-8");
			httpclient.getHttpConnectionManager().getParams().setConnectionTimeout(6000); // 默认超时时间6秒
			httpclient.getHttpConnectionManager().getParams().setSoTimeout(6000);
			httpclient.executeMethod(post);
			int code = post.getStatusCode();
			if (code == HttpStatus.SC_OK)
				info = new String(post.getResponseBodyAsString());
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			post.releaseConnection();
		}
		return info;
	}

	private static GetMethod getMethod(String url, String param) throws IOException {
		GetMethod get = new GetMethod(url + "?" + param);
		return get;
	}

	/**
	 * @Title: execPOSTMethodJSONSoTimeout
	 * @Description: 发送json
	 * @param url
	 * @param json
	 * @return
	 * @throws IOException
	 * @throws HttpException
	 */
	public static String execPOSTMethodJSON(String url, String json) throws HttpException, IOException {
		return execPOSTMethodJSONSoTimeout(url, json, 6000, 10000, null);
	}

	/**
	 * @Title: execPOSTMethodJSONSoTimeout
	 * @Description: 发送json
	 * @param url
	 * @param json
	 * @return
	 * @throws IOException
	 * @throws HttpException
	 */
	public static String execPOSTMethodJSON(String url, String json, List<Header> headers)
			throws HttpException, IOException {
		return execPOSTMethodJSONSoTimeout(url, json, 6000, 10000, headers);
	}

	/**
	 * 
	 * @Title: execPOSTMethodJSONSoTimeout
	 * @Description: 发送json
	 * @param url
	 * @param json
	 * @param soTimeout
	 * @param conTimeout
	 * @param headers
	 *            TODO
	 * @return
	 * @throws HttpException
	 * @throws IOException
	 */
	public static String execPOSTMethodJSONSoTimeout(String url, String json, int soTimeout, int conTimeout,
			List<Header> headers) throws HttpException, IOException {
		StringBuilder reponseStr = new StringBuilder();
		// 构造HttpClient的实例
		HttpClient httpClient = new HttpClient(connectionManager);
		httpClient.getParams().setParameter("http.protocol.content-charset", "UTF-8");
		if (headers != null) {
			httpClient.getHostConfiguration().getParams().setParameter("http.default-headers", headers);
		}
		httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(conTimeout);
		httpClient.getHttpConnectionManager().getParams().setSoTimeout(soTimeout);
		PostMethod postMethod = new PostMethod(url);
		postMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());

		StringRequestEntity re = new StringRequestEntity(json, "application/json", "UTF-8");
		postMethod.setRequestEntity(re);
		// 执行postMethod
		int statusCode = httpClient.executeMethod(postMethod);
		if (statusCode != HttpStatus.SC_OK) {
			log.info("Method failed: " + postMethod.getStatusLine());
			log.info(new String(postMethod.getResponseBody()));
			return null;
		}
		log.info("http status: " + statusCode);
		// 读取内容
		byte[] responseBody = postMethod.getResponseBody();
		reponseStr.append(new String(responseBody, "UTF-8"));
		postMethod.releaseConnection();
		return reponseStr.toString();
	}

	/**
	 * 
	 * @Title: execGETMethodJSONSoTimeout
	 * @Description: 发送json
	 * @param url
	 * @param soTimeout
	 * @param conTimeout
	 * @param headers
	 *            TODO
	 * @return
	 * @throws HttpException
	 * @throws IOException
	 */
	public static String execGETMethodJSONSoTimeout(String url, int soTimeout, int conTimeout, List<Header> headers)
			throws HttpException, IOException {
		StringBuilder reponseStr = new StringBuilder();
		// 构造HttpClient的实例
		HttpClient httpClient = new HttpClient(connectionManager);
		httpClient.getParams().setParameter("http.protocol.content-charset", "UTF-8");
		if (headers != null) {
			httpClient.getHostConfiguration().getParams().setParameter("http.default-headers", headers);
		}
		httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(conTimeout);
		httpClient.getHttpConnectionManager().getParams().setSoTimeout(soTimeout);
		GetMethod getMethod = new GetMethod(url);
		getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
		// 执行postMethod
		int statusCode = httpClient.executeMethod(getMethod);
		if (statusCode != HttpStatus.SC_OK) {
			log.info("Method failed: " + getMethod.getStatusLine());
			log.info(new String(getMethod.getResponseBody()));
			return null;
		}
		log.info("http status: " + statusCode);
		// 读取内容
		byte[] responseBody = getMethod.getResponseBody();
		reponseStr.append(new String(responseBody, "UTF-8"));
		getMethod.releaseConnection();
		return reponseStr.toString();
	}

	public static void main(String[] args) {
		try {
			execGETMethodJSONSoTimeout = execGETMethodJSONSoTimeout(
					"http://10.100.19.180:18080/bdp/spider/phoneinfo?telNumber=13820656371", 100, 111, null);
			System.out.println(execGETMethodJSONSoTimeout);
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
