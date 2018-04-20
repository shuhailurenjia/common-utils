package com.zwh.common.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpXmlClient {
	private static Logger log = LoggerFactory.getLogger(HttpXmlClient.class);

	public static String post(String url, Map<String, String> params) {
		String body = null;
		try {
			body = HttpclientProxy.execPOSTMethodMParames(url, params);
		} catch (IOException e) {
			log.error("HttpXmlClient->post url:{},param:{},msg:{}", url, params, e.getMessage());
		}
		log.info("url:{},param:{}-> body:{}", url, params, body);
		return body;
	}

	/**
	 * 我的名片post请求用
	 * 
	 * @param url
	 * @param params
	 * @return
	 */
	public static String postForCard(String url, Map<String, String> params) {
		DefaultHttpClient httpclient = new DefaultHttpClient();
		String body = null;

		log.debug("create httppost:" + url);
		HttpPost post = postForm(url, params);

		body = invoke(httpclient, post);

		httpclient.getConnectionManager().shutdown();

		return body;
	}

	public static String get(String url) {
		DefaultHttpClient httpclient = new DefaultHttpClient();
		String body = null;

		log.debug("create httppost:" + url);
		HttpGet get = new HttpGet(url);
		body = invoke(httpclient, get);

		httpclient.getConnectionManager().shutdown();

		return body;
	}

	private static String invoke(DefaultHttpClient httpclient, HttpUriRequest httpost) {

		HttpResponse response = sendRequest(httpclient, httpost);
		String body = paseResponse(response);

		return body;
	}

	@SuppressWarnings("deprecation")
	private static String paseResponse(HttpResponse response) {
		log.debug("get response from http server..");
		HttpEntity entity = response.getEntity();

		log.debug("response status: " + response.getStatusLine());
		String charset = EntityUtils.getContentCharSet(entity);
		log.debug(charset);

		String body = null;
		try {
			body = EntityUtils.toString(entity);
			log.debug(body);
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return body;
	}

	private static HttpResponse sendRequest(DefaultHttpClient httpclient, HttpUriRequest httpost) {
		log.debug("execute post...");
		HttpResponse response = null;

		try {
			response = httpclient.execute(httpost);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return response;
	}

	@SuppressWarnings("deprecation")
	private static HttpPost postForm(String url, Map<String, String> params) {
		HttpPost httpost = new HttpPost(url);
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		Set<String> keySet = params.keySet();
		for (String key : keySet) {
			String str = StringUtils.objectToString(params.get(key));
			nvps.add(new BasicNameValuePair(key, str));
		}

		try {
			log.debug("set utf-8 form entity to httppost");
			httpost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return httpost;
	}
}
