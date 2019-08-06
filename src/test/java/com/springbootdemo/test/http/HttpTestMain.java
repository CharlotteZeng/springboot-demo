package com.springbootdemo.test.http;

import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class HttpTestMain {

    public static MultiThreadedHttpConnectionManager connectionManager;
    public static int SOTIMEOUT = 3000;
    public static int CONTIMEOUT = 5000;
    static{
        connectionManager =  new   MultiThreadedHttpConnectionManager();

    }
    public HttpTestMain(){

    }
    public static void main(String[] args){
        String url = "https://%s.lianjia.com/";
        url = String.format(url, "bj");

        System.out.println(url);
        String sort = "rco21";//价格从低到高
        String sort1 = "";//综合排序
        String s = sort+"rt200600000001";
        String zufangUrl =url + "zufang/haidian/"+s+"/";
        Map<String,String> map = new HashMap<>();
        try {
            String result = execGETMethod(zufangUrl, map);
//            System.out.println(result);
            Document doc = Jsoup.parse(result);
//            Elements elementsByAttribute = doc.getElementsByAttribute("content__list--item--main");
            Elements elementsByAttribute = doc.getElementsByClass("content__list--item--main");
            System.out.println("取到多少个节点："+elementsByAttribute.size());
            for (int i=0;i<elementsByAttribute.size();i++){
                Element element = elementsByAttribute.get(i);
                Elements p = element.getElementsByTag("p");
                Element zufangTitle = p.get(0);
                Elements aElements = zufangTitle.getElementsByTag("a");
//                System.out.println(aElements);
                for (int j=0;j<aElements.size();j++){
                    Element a = aElements.get(j);
                    System.out.println(a.text());
                    String zufangInfoUrl = url + a.attr("href");
                    System.out.println(zufangInfoUrl);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
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
        if (parames.size()>0)
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
            System.out.println("request url" + url + "   Method failed: "
                    + getMethod.getStatusLine());
        }
        // 读取内容
        byte[] responseBody = getMethod.getResponseBody();
        reponseStr.append(responseBody);
        // 处理内容
        getMethod.releaseConnection();
        String rspo = new String(responseBody);
        return rspo;
    }
    private static GetMethod getMethod(String url,String param) throws IOException{
        GetMethod get = new GetMethod(url+"?"+param);
        return get;
    }
}
