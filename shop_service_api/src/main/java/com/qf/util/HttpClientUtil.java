package com.qf.util;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * HttpClient的工具方法
 * @Author pdn
 * @Time 2018/11/20 22:03
 * @Version 1.0
 */
public class HttpClientUtil {

    public static String sendJson(String url,String json) throws IOException {
        //构建httpClient对象
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        //构建post请求
        HttpPost post = new HttpPost(url);
        post.addHeader("Content-Type","application/json");
        //设置请求体的格式
        StringEntity entity = new StringEntity(json, "utf-8");
        //设置请求体的内容
        post.setEntity(entity);
        //发送请求
        CloseableHttpResponse response = httpClient.execute(post);
        //获得响应题中的内容
        HttpEntity entity1 = response.getEntity();
        //解析相引题的内容
        String result = EntityUtils.toString(entity1);
        //关闭httpClient
        httpClient.close();
        return  result;
    }


//    public static void sendJson() throws IOException {
//        //构建httpClient对象
//        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
//        //构建一个get请求
//        HttpGet httpGet = new HttpGet("http://www.baidu.com");
//        //发送请求
//        CloseableHttpResponse response = httpClient.execute(httpGet);
//        //获得响应题
//        HttpEntity entity = response.getEntity();
//        //从响应题中解析响应的结果
//        String result = EntityUtils.toString(entity);
//        System.out.println("响应的结果："+result);
//    }

}
