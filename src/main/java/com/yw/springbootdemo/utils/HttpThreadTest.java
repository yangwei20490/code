package com.yw.springbootdemo.utils;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author yangwei
 * @date 2020-05-04 13:39
 */
public class HttpThreadTest implements Runnable {

    private String url;
    private String paramStr;

    public HttpThreadTest(String url, String paramStr) {
        super();
        this.url = url;
        this.paramStr = paramStr;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getParamStr() {
        return paramStr;
    }

    public void setParamStr(String paramStr) {
        this.paramStr = paramStr;
    }

    @Override
    public void run() {
        // http请求实现方式
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost post = new HttpPost(url);
        RequestConfig config = RequestConfig.custom().setConnectionRequestTimeout(10000).setConnectTimeout(10000)
                .setSocketTimeout(10000).build();
        CloseableHttpResponse response = null;
        try {
            // 创建请求内容
            StringEntity entity = new StringEntity(paramStr, ContentType.APPLICATION_JSON);
            post.setEntity(entity);
            // 执行http请求
            response = httpClient.execute(post);
            String resultString = EntityUtils.toString(response.getEntity(), "utf-8");
            System.out.println("content:" + resultString);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private List<NameValuePair> setHttpNameValues(Map<String, Object> paramMap) {
        List<NameValuePair> params = new ArrayList();
        for (Map.Entry<String, Object> entry : paramMap.entrySet()) {
            params.add(new BasicNameValuePair(entry.getKey(), entry.getValue().toString()));
        }
        return params;
    }

    public static void main(String[] args) {
        //运用java工具类中线程池
        ExecutorService pool = Executors.newCachedThreadPool();
        for (int i = 0; i < 2; i++) { //开启俩个线程
            //target目标URL
            System.out.println("执行线程" + i);
            String url = "http://localhost:4085/task/faceidentify/callback";
            pool.execute(new HttpThreadTest(url, getHttpParamStr()));
        }
    }

    public static String getHttpParamStr() {
        JSONObject param=new JSONObject();
        param.put("personId", "58f1147c0aee4cca89337bd9b60a9b59");
        param.put("deviceKey", "B69B46A7BC78");
        param.put("type", "face_0");
        param.put("ip", "192.168.135.197");
        param.put("time", 1588152246033L);
        param.put("path", "ftp://192.168.135.197:8010/log/2020-05-04/f24229f6985d439892fcc5e2efea9114_1588571857826.jpeg");
        return param.toJSONString();
    }

}
