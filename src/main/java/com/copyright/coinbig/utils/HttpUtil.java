package com.copyright.coinbig.utils;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class HttpUtil {

    public static String getWeChatUserInfo(String url, List<NameValuePair> params) {

        String result = "";
        try {
            // POST的URL
            HttpPost httppost = new HttpPost(url);

            httppost.setHeader("user-agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/67.0.3396.99 Safari/537.36");
            // 建立HttpPost对象
            httppost.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));

            // 通过请求对象获取响应对象
            HttpResponse response = new DefaultHttpClient().execute(httppost);

            // 判断网络连接状态码是否正常(0--200都数正常)
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                result = EntityUtils.toString(response.getEntity(), "utf-8");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

}
