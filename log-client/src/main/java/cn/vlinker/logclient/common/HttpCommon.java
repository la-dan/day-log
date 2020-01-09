package cn.vlinker.logclient.common;

import com.alibaba.fastjson.JSON;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HttpCommon {


    public static final String GetRequest(StringBuffer url, Map<String, Object> param) {
        String res = null;
        try {
            if (param != null && param.size() > 0) {
                url.append("?");
                param.forEach((k, v) -> {
                    url.append(k + "=" + v + "&");
                });
            }
            //1.打开浏览器
            CloseableHttpClient httpClient = HttpClients.createDefault();
            //2.声明get请求
            HttpGet httpGet = new HttpGet(url.toString().substring(0, url.length() - 1));
            //3.发送请求
            CloseableHttpResponse response = httpClient.execute(httpGet);
            //4.判断状态码
            if (response.getStatusLine().getStatusCode() == 200) {
                HttpEntity entity = response.getEntity();
                //使用工具类EntityUtils，从响应中取出实体表示的内容并转换成字符串
                String string = EntityUtils.toString(entity, "utf-8");
                res = string;
            }
            //5.关闭资源
            response.close();
            httpClient.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    public static final String PostRequest(String host,String path,int port, Map<String, String> param, Object o) {
        String res = null;


        // 获得Http客户端(可以理解为:你得先有一个浏览器;注意:实际上HttpClient与浏览器是不一样的)
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        // 创建Post请求
        // 参数
        URI uri = null;
        try {
            // 将参数放入键值对类NameValuePair中,再放入集合中
            List<NameValuePair> params = new ArrayList();
            if (param != null && param.size() > 0) {
                param.forEach((k, v) -> {
                    params.add(new BasicNameValuePair(k, v));
                });
            }
            // 设置uri信息,并将参数集合放入uri;
            // 注:这里也支持一个键值对一个键值对地往里面放setParameter(String key, String value)
            uri = new URIBuilder().setScheme("http").setHost(host).setPort(port)
                    .setPath(path).setParameters(params).build();
        } catch (URISyntaxException e1) {
            e1.printStackTrace();
        }

        HttpPost httpPost = new HttpPost(uri);

        // 将user对象转换为json字符串，并放入entity中
        StringEntity entity = new StringEntity(JSON.toJSONString(o), StandardCharsets.UTF_8);

        // post请求是将参数放在请求体里面传过去的;这里将entity放入post请求体中
        httpPost.setEntity(entity);

        httpPost.setHeader("Content-Type", "application/json;charset=utf8");

        // 响应模型
        CloseableHttpResponse response = null;
        try {
            // 由客户端执行(发送)Post请求
            response = httpClient.execute(httpPost);
            // 从响应模型中获取响应实体
            HttpEntity responseEntity = response.getEntity();

            if (responseEntity != null) {
                res = EntityUtils.toString(responseEntity);
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                // 释放资源
                if (httpClient != null) {
                    httpClient.close();
                }
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return res;
    }
}
