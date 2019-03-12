package com.springbootdemo.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Map;

public class HttpclientProxy {


	private static Logger log = LoggerFactory.getLogger(HttpclientProxy.class);
	public static  MultiThreadedHttpConnectionManager connectionManager;
	public static int SOTIMEOUT = 3000; 
	public static int CONTIMEOUT = 5000;
	static{
		connectionManager =  new   MultiThreadedHttpConnectionManager();
				
	}
	public HttpclientProxy(){
		
	}

	
	/**
	 * GET多个参数--风险测评用
	 * @param url
	 * @param parames
	 * @return
	 * @throws HttpException
	 * @throws IOException
	 */
	public static String execGETMethodRiskEval(String url, Map<String, String> parames)
			throws HttpException, IOException {
		StringBuilder reponseStr = new StringBuilder();
		// 构造HttpClient的实例
		HttpClient httpClient = new HttpClient(connectionManager);
		GetMethod getMethod =null;
		StringBuilder paramsStr = new StringBuilder();
		// 设置参数
		if (null!=parames&&parames.size() > 0) {
			for (Map.Entry<String, String> entry : parames.entrySet()) {
				paramsStr.append(entry.getKey()).append("=")
						.append(URLEncoder.encode(entry.getValue(), "UTF-8"))
						.append("&");
			}
		}
		log.info("HttpclientProxy.execGETMethodRiskEval url= "+url+paramsStr);
		// 创建GET方法的实例
		getMethod = getMethod(url,paramsStr.toString());
		// 使用系统提供的默认的恢复策略
		getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
				new DefaultHttpMethodRetryHandler());
		// 执行getMethod
		int statusCode = httpClient.executeMethod(getMethod);
		if (statusCode != HttpStatus.SC_OK) {
			log.info("request url" + url + "   Method failed: "
					+ getMethod.getStatusLine());
		}
		// 读取内容
		byte[] responseBody = getMethod.getResponseBody();
		reponseStr.append(new String(responseBody));
		// 处理内容
		log.info(url + " response:" + new String(responseBody));
		getMethod.releaseConnection();

		return reponseStr.toString();
	}
	/**
	 * 头信息Get请求
	 * @param url
	 * @param parames
	 * @return
	 * @throws HttpException
	 * @throws IOException
	 */
	public static String execGETMethodWithHeader(String url, Map<String, String> parames,String sessionId)
			throws HttpException, IOException {
		StringBuilder reponseStr = new StringBuilder();
		// 构造HttpClient的实例
		HttpClient httpClient = new HttpClient(connectionManager);
		GetMethod getMethod =null;
		StringBuilder paramsStr = new StringBuilder();
		// 设置参数
		if (parames.size() > 0) {
			for (Map.Entry<String, String> entry : parames.entrySet()) {
				paramsStr.append(entry.getKey()).append("=")
				.append(URLEncoder.encode(entry.getValue(), "UTF-8"))
				.append("&");
			}
		}
		// 创建GET方法的实例
		getMethod = getMethod(url,paramsStr.toString());
		// 使用系统提供的默认的恢复策略
		getMethod.setRequestHeader(new Header("",sessionId));
		getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
				new DefaultHttpMethodRetryHandler());
		getMethod.addRequestHeader("Cookie", "JSESSIONID="+ sessionId);
		// 执行getMethod
		int statusCode = httpClient.executeMethod(getMethod);
		if (statusCode != HttpStatus.SC_OK) {
			log.info("request url" + url + "   Method failed: "
					+ getMethod.getStatusLine());
		}
		// 读取内容
		byte[] responseBody = getMethod.getResponseBody();
		reponseStr.append(new String(responseBody));
		// 处理内容
		log.info(url + " response:" + new String(responseBody));
		getMethod.releaseConnection();
		
		return reponseStr.toString();
	}
	
	/**
	 * GET多个参数
	 * @param url
	 * @param parames
	 * @return
	 * @throws HttpException
	 * @throws IOException
	 */
	public static String execGETMethod(String url, Map<String, String> parames)
			throws HttpException, IOException {
		StringBuilder reponseStr = new StringBuilder();
		// 构造HttpClient的实例
		HttpClient httpClient = new HttpClient(connectionManager);
		GetMethod getMethod =null;
		@SuppressWarnings("unused")
		StringBuilder paramsStr = new StringBuilder();
		// 设置参数
		String str ="";
		str = URLEncoder.encode(parames.get("originalStr"), "utf-8")+"&sign=" + parames.get("sign");
//		if (parames.size() > 0) {
//			for (Map.Entry<String, String> entry : parames.entrySet()) {
//				paramsStr.append(entry.getKey()).append("=")
//						.append(URLEncoder.encode(entry.getValue(), "UTF-8"))
//						.append("&");
//			}
//			str =	paramsStr.toString().substring(0, paramsStr.toString().length()-1);
//		}
		// 创建GET方法的实例
		System.out.println(str);
		getMethod = getMethod(url,str);
		// 使用系统提供的默认的恢复策略
		getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
				new DefaultHttpMethodRetryHandler());
		// 执行getMethod
		int statusCode = httpClient.executeMethod(getMethod);
		if (statusCode != HttpStatus.SC_OK) {
			log.info("request url" + url + "   Method failed: "
					+ getMethod.getStatusLine());
		}
		// 读取内容
		byte[] responseBody = getMethod.getResponseBody();
		reponseStr.append(responseBody);
		// 处理内容
		log.info(url + " response:" + new String(responseBody));
		getMethod.releaseConnection();
		String rspo = new String(responseBody);
		return rspo;
	}

	/**
	 * POST多个参数 
	 * @param url
	 * @param parames
	 * @return
	 * @throws IOException 
	 * @throws HttpException 
	 */
	public static String execPOSTMethodMParames(String url,
			Map<String, String> parames) throws HttpException, IOException {
		StringBuilder reponseStr = new StringBuilder();
		// 构造HttpClient的实例
		HttpClient httpClient = new HttpClient(connectionManager);
		httpClient.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
		httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(600000); 
		httpClient.getHttpConnectionManager().getParams().setSoTimeout(1000000);
		PostMethod postMethod = new PostMethod(url);
		postMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
				new DefaultHttpMethodRetryHandler());
		// 填入各个表单域的值
		/*
		 * NameValuePair[] data = { new NameValuePair("id", "youUserName"), new
		 * NameValuePair("passwd", "yourPwd") };
		 */
		// 将表单的值放入postMethod中
		// 设置参数
		if (parames != null && parames.size() > 0) {
			NameValuePair[] data = new NameValuePair[parames.size()];
			int parameLenth = 0;
			for (Map.Entry<String, String> entry : parames.entrySet()) {
				data[parameLenth] = new NameValuePair(entry.getKey(),
						entry.getValue());
				parameLenth +=1;
			}
			postMethod.setRequestBody(data);
		}
		// 执行postMethod
		int statusCode = httpClient.executeMethod(postMethod);
		if (statusCode != HttpStatus.SC_OK) {
			log.info("request url" + url + "   Method failed: "
					+ postMethod.getStatusLine());
		}
		if (statusCode == HttpStatus.SC_NO_CONTENT)
			return null;
		// 读取内容
		byte[] responseBody = postMethod.getResponseBody();
		reponseStr.append(new String(responseBody));
		postMethod.releaseConnection();
		// HttpClient对于要求接受后继服务的请求，象POST和PUT等不能自动处理转发
		// 301或者302
		/*
		 * if (statusCode == HttpStatus.SC_MOVED_PERMANENTLY || statusCode ==
		 * HttpStatus.SC_MOVED_TEMPORARILY) { // 从头中取出转向的地址 Header
		 * locationHeader = postMethod.getResponseHeader("location"); String
		 * location = null; if (locationHeader != null) { location =
		 * locationHeader.getValue();
		 * System.out.println("The page was redirected to:" + location); } else
		 * { System.err.println("Location field value is null."); } return; }
		 */
		return reponseStr.toString();
	}
    /**
     * POST多个参数 重载方法支持设置超时时间
     * @param url
     * @param parames
     * @return
     * @throws IOException
     * @throws HttpException
     */
    public static String execPOSTMethodMParames(String url,
                                                Map<String, String> parames,int connectionTimeout,int socketTimeout) throws HttpException, IOException {
        StringBuilder reponseStr = new StringBuilder();
        // 构造HttpClient的实例
        HttpClient httpClient = new HttpClient(connectionManager);
        httpClient.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
        httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(connectionTimeout);
        httpClient.getHttpConnectionManager().getParams().setSoTimeout(socketTimeout);
        PostMethod postMethod = new PostMethod(url);
        postMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
                new DefaultHttpMethodRetryHandler());
        if (parames != null && parames.size() > 0) {
            NameValuePair[] data = new NameValuePair[parames.size()];
            int parameLenth = 0;
            for (Map.Entry<String, String> entry : parames.entrySet()) {
                data[parameLenth] = new NameValuePair(entry.getKey(),
                        entry.getValue());
                parameLenth +=1;
            }
            postMethod.setRequestBody(data);
        }
        // 执行postMethod
        int statusCode = httpClient.executeMethod(postMethod);
        if (statusCode != HttpStatus.SC_OK) {
            log.info("request url" + url + "   Method failed: "
                    + postMethod.getStatusLine());
            return null;
        }
        // 读取内容
        byte[] responseBody = postMethod.getResponseBody();
        reponseStr.append(new String(responseBody));
        postMethod.releaseConnection();
        return reponseStr.toString();
    }
	/**
	 * POST多个参数 
	 * @param url
	 * @param parames
	 * @return
	 * @throws IOException 
	 * @throws HttpException 
	 */
	public static String execPOSTMethodMParamesMap(String url,
			Map<String, Object> parames) throws HttpException, IOException {
		StringBuilder reponseStr = new StringBuilder();
		// 构造HttpClient的实例
		HttpClient httpClient = new HttpClient(connectionManager);
		httpClient.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
		httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(600000); 
		httpClient.getHttpConnectionManager().getParams().setSoTimeout(1000000);
		PostMethod postMethod = new PostMethod(url);
		postMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
				new DefaultHttpMethodRetryHandler());
		// 填入各个表单域的值
		/*
		 * NameValuePair[] data = { new NameValuePair("id", "youUserName"), new
		 * NameValuePair("passwd", "yourPwd") };
		 */
		// 将表单的值放入postMethod中
		// 设置参数
		if (parames != null && parames.size() > 0) {
			NameValuePair[] data = new NameValuePair[parames.size()];
			int parameLenth = 0;
			for (Map.Entry<String, Object> entry : parames.entrySet()) {
				data[parameLenth] = new NameValuePair(entry.getKey(),
						entry.getValue().toString());
				parameLenth +=1;
			}
			postMethod.setRequestBody(data);
		}
		// 执行postMethod
		int statusCode = httpClient.executeMethod(postMethod);
		if (statusCode != HttpStatus.SC_OK) {
			log.info("request url" + url + "   Method failed: "
					+ postMethod.getStatusLine());
		}
		// 读取内容
		byte[] responseBody = postMethod.getResponseBody();
		reponseStr.append(new String(responseBody));
		postMethod.releaseConnection();
		// HttpClient对于要求接受后继服务的请求，象POST和PUT等不能自动处理转发
		// 301或者302
		/*
		 * if (statusCode == HttpStatus.SC_MOVED_PERMANENTLY || statusCode ==
		 * HttpStatus.SC_MOVED_TEMPORARILY) { // 从头中取出转向的地址 Header
		 * locationHeader = postMethod.getResponseHeader("location"); String
		 * location = null; if (locationHeader != null) { location =
		 * locationHeader.getValue();
		 * System.out.println("The page was redirected to:" + location); } else
		 * { System.err.println("Location field value is null."); } return; }
		 */
		return reponseStr.toString();
	}
	/**
	 * POST多个参数 
	 * @param url
	 * @param parames
	 * @return
	 * @throws IOException 
	 * @throws HttpException 
	 */
	public static String execPOSTMethodMParames(String url,String headerParams,
			Map<String, String> parames) throws HttpException, IOException {
		StringBuilder reponseStr = new StringBuilder();
		// 构造HttpClient的实例
		HttpClient httpClient = new HttpClient(connectionManager);
		httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(6000); 
		httpClient.getHttpConnectionManager().getParams().setSoTimeout(10000);
		PostMethod postMethod = new PostMethod(url);
		Header header = new Header("content-type",headerParams);
		postMethod.addRequestHeader(header);
		postMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
				new DefaultHttpMethodRetryHandler());
		// 填入各个表单域的值
		/*
		 * NameValuePair[] data = { new NameValuePair("id", "youUserName"), new
		 * NameValuePair("passwd", "yourPwd") };
		 */
		// 将表单的值放入postMethod中
		// 设置参数
		if (parames != null && parames.size() > 0) {
			NameValuePair[] data = new NameValuePair[parames.size()];
			int parameLenth = 0;
			for (Map.Entry<String, String> entry : parames.entrySet()) {
				data[parameLenth] = new NameValuePair(entry.getKey(),
						entry.getValue());
				parameLenth +=1;
			}
			postMethod.setRequestBody(data);
		}
		// 执行postMethod
		int statusCode = httpClient.executeMethod(postMethod);
		if (statusCode != HttpStatus.SC_OK) {
			log.info("request url" + url + "   Method failed: "
					+ postMethod.getStatusLine());
		}
		// 读取内容
		byte[] responseBody = postMethod.getResponseBody();
		reponseStr.append(new String(responseBody));
		postMethod.releaseConnection();
		// HttpClient对于要求接受后继服务的请求，象POST和PUT等不能自动处理转发
		// 301或者302
		/*
		 * if (statusCode == HttpStatus.SC_MOVED_PERMANENTLY || statusCode ==
		 * HttpStatus.SC_MOVED_TEMPORARILY) { // 从头中取出转向的地址 Header
		 * locationHeader = postMethod.getResponseHeader("location"); String
		 * location = null; if (locationHeader != null) { location =
		 * locationHeader.getValue();
		 * System.out.println("The page was redirected to:" + location); } else
		 * { System.err.println("Location field value is null."); } return; }
		 */
		return reponseStr.toString();
	}
	/**
	 * POST多个参数，带字符集参数，为空是utf-8
	 * @param url
	 * @param parames
	 * @return
	 * @throws IOException 
	 * @throws HttpException 
	 */
	public static String execPOSTMethodMParamesByCharSet(String url,
			Map<String, String> parames,String charSet) throws HttpException, IOException {
		StringBuilder reponseStr = new StringBuilder();
		// 构造HttpClient的实例
		HttpClient httpClient = new HttpClient(connectionManager);
		httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(6000); 
		httpClient.getHttpConnectionManager().getParams().setSoTimeout(10000);
		PostMethod postMethod = new PostMethod(url);
		
		/**
		 * 以下try...catch为监控日志
		 */
		/*try {
			postMethod.addRequestHeader("traceid", Span.traceid());
			postMethod.addRequestHeader("spanid", Span.spanid());
		} catch (Exception e) {
		}*/
		
		if(charSet.isEmpty())
		{
			postMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
		}
		else
		{
			postMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, charSet);
		}
			
		postMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
				new DefaultHttpMethodRetryHandler());
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
				data[parameLenth] = new NameValuePair(entry.getKey(),
						entry.getValue());
				parameLenth +=1;
			}
			postMethod.setRequestBody(data);
		}
		// 执行postMethod
		int statusCode = httpClient.executeMethod(postMethod);
		if (statusCode != HttpStatus.SC_OK) {
			log.info("request url" + url + "   Method failed: "
					+ postMethod.getStatusLine());
		}
		// 读取内容
		byte[] responseBody = postMethod.getResponseBody();
		reponseStr.append(new String(responseBody,"UTF-8"));
		postMethod.releaseConnection();
		// HttpClient对于要求接受后继服务的请求，象POST和PUT等不能自动处理转发
		// 301或者302
		/*
		 * if (statusCode == HttpStatus.SC_MOVED_PERMANENTLY || statusCode ==
		 * HttpStatus.SC_MOVED_TEMPORARILY) { // 从头中取出转向的地址 Header
		 * locationHeader = postMethod.getResponseHeader("location"); String
		 * location = null; if (locationHeader != null) { location =
		 * locationHeader.getValue();
		 * System.out.println("The page was redirected to:" + location); } else
		 * { System.err.println("Location field value is null."); } return; }
		 */
		return reponseStr.toString();
	}
	
	/**
	 * POST多个参数 
	 * @param url
	 * @param parames
	 * @return
	 * @throws IOException 
	 * @throws HttpException 
	 */
	public static String execPOSTMethodMParamesLender(String url,
			Map<String, String> parames) throws HttpException, IOException {
		StringBuilder reponseStr = new StringBuilder();
		// 构造HttpClient的实例
		HttpClient httpClient = new HttpClient();
		httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(6000); 
		httpClient.getHttpConnectionManager().getParams().setSoTimeout(10000);
		PostMethod postMethod = new PostMethod(url);
		postMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
				new DefaultHttpMethodRetryHandler());
		// 设置参数
		if (parames.size() > 0) {
			NameValuePair[] data = new NameValuePair[parames.size()];
			int parameLenth = 0;
			for (Map.Entry<String, String> entry : parames.entrySet()) {
				data[parameLenth] = new NameValuePair(entry.getKey(),
						entry.getValue());
				parameLenth +=1;
			}
			postMethod.setRequestBody(data);
		}
		// 执行postMethod
		int statusCode = httpClient.executeMethod(postMethod);
		if (statusCode != HttpStatus.SC_OK) {
			log.info("request url" + url + "   Method failed: "
					+ postMethod.getStatusLine());
		}
		// 读取内容
		byte[] responseBody = postMethod.getResponseBody();
		reponseStr.append(new String(responseBody,"GBK"));
		postMethod.releaseConnection();
		return reponseStr.toString();
	}
	
	/**
	 * POST多个参数 
	 * @param url
	 * @param parames
	 * @param charSet 空为UTF-8
	 * @return
	 * @throws IOException 
	 * @throws HttpException 
	 */
	public static String execPOSTMethodMParames(String url,
			Map<String, String> parames, String charSet) throws HttpException, IOException {
		StringBuilder reponseStr = new StringBuilder();
		// 构造HttpClient的实例
		HttpClient httpClient = new HttpClient(connectionManager);
		httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(6000); 
		httpClient.getHttpConnectionManager().getParams().setSoTimeout(10000);
		PostMethod postMethod = new PostMethod(url);
		postMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
				new DefaultHttpMethodRetryHandler());
		if(StringUtils.isBlank(charSet)){
			charSet =  "UTF-8";
		}
		postMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, charSet);
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
				data[parameLenth] = new NameValuePair(entry.getKey(),
						entry.getValue());
				parameLenth +=1;
			}
			postMethod.setRequestBody(data);
		}
		// 执行postMethod
		int statusCode = httpClient.executeMethod(postMethod);
		if (statusCode != HttpStatus.SC_OK) {
			log.info("request url" + url + "   Method failed: "
					+ postMethod.getStatusLine());
		}
		// 读取内容
		byte[] responseBody = postMethod.getResponseBody();
		reponseStr.append(new String(responseBody));
		postMethod.releaseConnection();
		// HttpClient对于要求接受后继服务的请求，象POST和PUT等不能自动处理转发
		// 301或者302
		/*
		 * if (statusCode == HttpStatus.SC_MOVED_PERMANENTLY || statusCode ==
		 * HttpStatus.SC_MOVED_TEMPORARILY) { // 从头中取出转向的地址 Header
		 * locationHeader = postMethod.getResponseHeader("location"); String
		 * location = null; if (locationHeader != null) { location =
		 * locationHeader.getValue();
		 * System.out.println("The page was redirected to:" + location); } else
		 * { System.err.println("Location field value is null."); } return; }
		 */
		return reponseStr.toString();
	}
	
	
	/**
	 * POST单个内容
	 * @param url
	 * @param strContent
	 * @return
	 * @throws HttpException
	 * @throws IOException
	 */
	@SuppressWarnings("deprecation")
	public static String execPOSTMethodParames(String url, String strContent)
			throws HttpException, IOException {
		StringBuilder reponseStr = new StringBuilder();
		// 构造HttpClient的实例
		HttpClient httpClient = new HttpClient(connectionManager);
//		httpClient.getParams().setParameter("http.protocol.content-charset", "UTF-8");
		httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(6000); 
		httpClient.getHttpConnectionManager().getParams().setSoTimeout(10000);
		PostMethod postMethod = new PostMethod(url);
		postMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
				new DefaultHttpMethodRetryHandler());
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
		reponseStr.append(new String(responseBody));
		postMethod.releaseConnection();
		return reponseStr.toString();
	}
	@SuppressWarnings("deprecation")
	public static String execPOSTMethodParamesHera(String url, String str,String contentType)
			throws HttpException, IOException {
		StringBuilder reponseStr = new StringBuilder();
		// 构造HttpClient的实例
		HttpClient httpClient = new HttpClient(connectionManager);
		httpClient.getParams().setParameter("http.protocol.content-charset", "UTF-8");
		httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(6000); 
		httpClient.getHttpConnectionManager().getParams().setSoTimeout(10000);
		PostMethod postMethod = new PostMethod(url);
		postMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
				new DefaultHttpMethodRetryHandler());
		Header header = new Header("content-type",contentType);
		postMethod.addRequestHeader(header);
		postMethod.setRequestBody(str);
		
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
		reponseStr.append(new String(responseBody));
		postMethod.releaseConnection();
		return reponseStr.toString();
	}
	public static String execPOSTMethodParamesHeraWithHeader(String url, String str,String contentType,String sessionId)
			throws HttpException, IOException {
		StringBuilder reponseStr = new StringBuilder();
		// 构造HttpClient的实例
		HttpClient httpClient = new HttpClient(connectionManager);
		httpClient.getParams().setParameter("http.protocol.content-charset", "UTF-8");
		httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(6000); 
		httpClient.getHttpConnectionManager().getParams().setSoTimeout(10000);
		PostMethod postMethod = new PostMethod(url);
		postMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
				new DefaultHttpMethodRetryHandler());
		Header header = new Header("content-type",contentType);
		postMethod.addRequestHeader(header);
		postMethod.addRequestHeader("Cookie", "JSESSIONID="+ sessionId);
		postMethod.setRequestBody(str);
		
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
		reponseStr.append(new String(responseBody));
		postMethod.releaseConnection();
		return reponseStr.toString();
	}
	
	public static String execGETMethodParamesHeraWithHeader(String url, String str,String contentType,String sessionId)
			throws HttpException, IOException {
		StringBuilder reponseStr = new StringBuilder();
		// 构造HttpClient的实例
		HttpClient httpClient = new HttpClient(connectionManager);
		httpClient.getParams().setParameter("http.protocol.content-charset", "UTF-8");
		httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(60000); 
		httpClient.getHttpConnectionManager().getParams().setSoTimeout(100000);
		GetMethod getMethod = new GetMethod(url+"?"+str);
		/*postMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
				new DefaultHttpMethodRetryHandler());*/
		Header header = new Header("content-type",contentType);
		getMethod.addRequestHeader(header);
		getMethod.addRequestHeader("Cookie", "JSESSIONID="+ sessionId);
//		postMethod.setRequestBody(str);
		
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
		reponseStr.append(new String(responseBody));
		getMethod.releaseConnection();
		return reponseStr.toString();
	}
	
	@SuppressWarnings("deprecation")
	public static String execPOSTMethodParamesXuanYuan(String url, String str)
			throws HttpException, IOException {
		StringBuilder reponseStr = new StringBuilder();
		// 构造HttpClient的实例
		HttpClient httpClient = new HttpClient(connectionManager);
		httpClient.getParams().setParameter("http.protocol.content-charset", "UTF-8");
		httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(300000); 
		httpClient.getHttpConnectionManager().getParams().setSoTimeout(300000);
		PostMethod postMethod = new PostMethod(url);
		postMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
				new DefaultHttpMethodRetryHandler());
		Header header = new Header("content-type","text/plain");
		postMethod.addRequestHeader(header);
		postMethod.setRequestBody(str);
		
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
		reponseStr.append(new String(responseBody));
		postMethod.releaseConnection();
		return reponseStr.toString();
	}
	
	
	/**
	 * POST单个内容(提现)
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
		postMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
				new DefaultHttpMethodRetryHandler());
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
		reponseStr.append(new String(responseBody,"GBK"));
		postMethod.releaseConnection();
		return reponseStr.toString();
	}
	private static GetMethod getMethod(String url,String param) throws IOException{  
        GetMethod get = new GetMethod(url+"?"+param);  
        return get;  
    } 	
	
	//推送消息部分使用。
	public static String execGETMethod(String url)
			throws HttpException, IOException {
		StringBuilder reponseStr = new StringBuilder();
		// 构造HttpClient的实例
		HttpClient httpClient = new HttpClient(connectionManager);
		GetMethod getMethod =null;
		StringBuilder paramsStr = new StringBuilder();
		// 创建GET方法的实例
		getMethod = getMethod(url,paramsStr.toString());
		// 使用系统提供的默认的恢复策略
		getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
				new DefaultHttpMethodRetryHandler());
		// 执行getMethod
		int statusCode = httpClient.executeMethod(getMethod);
		if(statusCode == HttpStatus.SC_NO_CONTENT){
			return "";
		}
		if (statusCode != HttpStatus.SC_OK) {
			log.info("request url" + url + "   Method failed: "
					+ getMethod.getStatusLine());
		}
		// 读取内容
		byte[] responseBody = getMethod.getResponseBody();
		if(responseBody!=null && responseBody.length>0) {
            reponseStr.append(new String(responseBody));
        }
		// 处理内容
		getMethod.releaseConnection();
		return reponseStr.toString();
	}
	
	/**
	 * GET多个参数--风险测评用
	 * @param url
	 * @param parames
	 * @return
	 * @throws HttpException
	 * @throws IOException
	 */
	public static String execGETMethodGetEop(String url)
			throws HttpException, IOException {
		StringBuilder reponseStr = new StringBuilder();
		// 构造HttpClient的实例
		HttpClient httpClient = new HttpClient(connectionManager);
		GetMethod getMethod =null;
		// 创建GET方法的实例
		getMethod = new GetMethod(url);
		// 使用系统提供的默认的恢复策略
		getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
				new DefaultHttpMethodRetryHandler());
		// 执行getMethod
		int statusCode = httpClient.executeMethod(getMethod);
		if (statusCode != HttpStatus.SC_OK) {
			log.info("request url" + url + "   Method failed: "
					+ getMethod.getStatusLine());
		}
		// 读取内容
		byte[] responseBody = getMethod.getResponseBody();
		reponseStr.append(new String(responseBody));
		// 处理内容
		log.info(url + " response:" + new String(responseBody));
		getMethod.releaseConnection();

		return reponseStr.toString();
	}
	@SuppressWarnings("deprecation")
	public static String getHadesContractTemplateList(String url, String strContent) throws HttpException, IOException {
		StringBuilder reponseStr = new StringBuilder();
		// 构造HttpClient的实例
		HttpClient httpClient = new HttpClient(connectionManager);
		httpClient.getParams().setParameter("http.protocol.content-charset", "UTF-8");
		httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(6000);
		httpClient.getHttpConnectionManager().getParams().setSoTimeout(10000);
		PostMethod postMethod = new PostMethod(url);
		postMethod.setRequestHeader("content-type", "application/json; charset=UTF-8");
		postMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
		postMethod.setRequestBody(strContent);
		// 执行postMethod
		int statusCode = httpClient.executeMethod(postMethod);
		if (statusCode != HttpStatus.SC_OK) {
			return null;
		}
		// 读取内容
		byte[] responseBody = postMethod.getResponseBody();
		reponseStr.append(new String(responseBody,"UTF-8"));
		postMethod.releaseConnection();
		return reponseStr.toString();
	}
	/**
	 * @Title: sendJSON
	 * @Description: 发送json
	 * @author xiaoruihu
	 * @param url
	 * @param json
	 * @return
	 * @throws IOException
	 * @throws HttpException
	 */
	public static String sendJSON(String url, String json) throws HttpException, IOException{
		return sendJSONSoTimeout(url, json, 6000, 10000);
	}

	/**
	 *
	 * @Title: sendJSONSoTimeout
	 * @Description: 发送json
	 * @author xiaoruihu
	 * @param url
	 * @param strContentObject
	 *            默认使用fastjson
	 * @param soTimeout
	 * @param conTimeout
	 * @return
	 * @throws HttpException
	 * @throws IOException
	 */
	public static String sendJSONSoTimeout(String url, String json, int soTimeout, int conTimeout)
			throws HttpException, IOException {
		StringBuilder reponseStr = new StringBuilder();
		// 构造HttpClient的实例
		HttpClient httpClient = new HttpClient(connectionManager);
		httpClient.getParams().setParameter("http.protocol.content-charset", "UTF-8");
		httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(conTimeout);
		httpClient.getHttpConnectionManager().getParams().setSoTimeout(soTimeout);
		PostMethod postMethod = new PostMethod(url);
		postMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());

		StringRequestEntity re = new StringRequestEntity(json, "application/json", "UTF-8");
		postMethod.setRequestEntity(re);
		// 执行postMethod
		int statusCode = httpClient.executeMethod(postMethod);
		if (statusCode != HttpStatus.SC_OK) {
			log.info("httpJsonMethod failed: " + postMethod.getStatusLine());
			log.info(new String(postMethod.getResponseBody()));
			return null;
		}
		log.info("httpJson status: " + statusCode);
		// 读取内容
		byte[] responseBody = postMethod.getResponseBody();
		reponseStr.append(new String(responseBody, "UTF-8"));
		postMethod.releaseConnection();
		return reponseStr.toString();
	}


	/**
	 * http通用方法
	 * @param url
	 * @return
	 * @throws HttpException
	 * @throws IOException
	 */
	public static String execGETMethodGeneral(String url)
			throws HttpException, IOException {
		StringBuilder reponseStr = new StringBuilder();
		// 构造HttpClient的实例
		HttpClient httpClient = new HttpClient(connectionManager);
		GetMethod getMethod =null;
		StringBuilder paramsStr = new StringBuilder();
		// 创建GET方法的实例
		getMethod = new GetMethod(url);
		// 使用系统提供的默认的恢复策略
		getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
				new DefaultHttpMethodRetryHandler());
		// 执行getMethod
		int statusCode = httpClient.executeMethod(getMethod);
		if(statusCode == HttpStatus.SC_NO_CONTENT){
			return "";
		}
		if (statusCode != HttpStatus.SC_OK) {
			log.info("request url" + url + "   Method failed: "
					+ getMethod.getStatusLine());
		}
		// 读取内容
		byte[] responseBody = getMethod.getResponseBody();
		if(responseBody!=null && responseBody.length>0) {
            reponseStr.append(new String(responseBody));
        }
		// 处理内容
		getMethod.releaseConnection();
		return reponseStr.toString();
	}
	
	
	//默认的超时时间
	public static String execGETMethodAutoTimeOut(String url, Map<String, String> parames)
			throws HttpException, IOException {
		return execGETMethodTimeOut(url,parames,SOTIMEOUT,CONTIMEOUT);
	}
	
	/**
	 * GET多个参数--风险测评用
	 * @param url
	 * @param parames
	 * @return
	 * @throws HttpException
	 * @throws IOException
	 */
	public static String execGETMethodTimeOut(String url, Map<String, String> parames, int soTimeout, int conTimeout)
			throws HttpException, IOException {
		StringBuilder reponseStr = new StringBuilder();
		// 构造HttpClient的实例
		HttpClient httpClient = new HttpClient(connectionManager);
		
		httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(conTimeout); 
		httpClient.getHttpConnectionManager().getParams().setSoTimeout(soTimeout);
		GetMethod getMethod =null;
		StringBuilder paramsStr = new StringBuilder();
		// 设置参数
		if (null!=parames&&parames.size() > 0) {
			for (Map.Entry<String, String> entry : parames.entrySet()) {
				paramsStr.append(entry.getKey()).append("=")
						.append(URLEncoder.encode(entry.getValue(), "UTF-8"))
						.append("&");
			}
		}
		log.info("HttpclientProxy.execGETMethodRiskEval url= "+url+paramsStr);
		// 创建GET方法的实例
		getMethod = getMethod(url,paramsStr.toString());
		// 使用系统提供的默认的恢复策略
		getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
				new DefaultHttpMethodRetryHandler());
		// 执行getMethod
		int statusCode = httpClient.executeMethod(getMethod);
		if (statusCode != HttpStatus.SC_OK) {
			log.info("request url" + url + "   Method failed: "
					+ getMethod.getStatusLine());
		}
		// 读取内容
		byte[] responseBody = getMethod.getResponseBody();
		reponseStr.append(new String(responseBody));
		// 处理内容
		log.info(url + " response:" + new String(responseBody));
		getMethod.releaseConnection();

		return reponseStr.toString();
	}
	public static void main(String[] args) throws IOException {
		String url = "http://10.143.135.133:9090/zeus-server-web/services/account/saveUicBindOffLineSynchronize";
		url = "http://10.143.135.133:8080/nuggets/services/userService/getUserById/11019710/1";
        JSONObject jo = new JSONObject();

       /* String s = sendJSON(url, jo.toJSONString());
        System.out.println(s);*/
        String s1 = execPOSTMethodMParames(url,null);
//        System.out.println("==========="+s1);
		JSONObject jsonObject = JSON.parseObject(s1);
		String userPicPath = jsonObject.getString("userPicPath");
		System.out.println(userPicPath);

		String userBindPhone = jsonObject.getString("userBindPhone");
		System.out.println(userBindPhone);
	}
	
}
