package com.copyright.coinbig.service;

import com.copyright.coinbig.utils.SignUtil;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.logging.Logger;

@Service
public class TradeService {

    private Logger logger = Logger.getLogger("TradeService.class");

    private Map<String, String> signParams = new HashMap<String, String>();

    public TradeService() {
        signParams.put("apikey", "899F4BFD47D88EC480B37095D5728C70");
        signParams.put("symbol", "btc_usdt");
        signParams.put("type", "1");
        signParams.put("size", "20");
        logger.info("sign = [ " + SignUtil.buildMysign(signParams, "84263AB49477D07D89B79C73C9D19ED9") + "]");
    }

    /**
     * 下单测试
     */
    public void demoTrade() {
        boolean isBuy = true;
        Map<String, String> signParams = new HashMap<>();
        signParams.put("apikey", "19EDB6227BED371FB475680739BD45B2");
        signParams.put("symbol", "BOPO_USDT");
        signParams.put("type", isBuy ? "buy" : "sell");

        Random random = new Random();
        int price = random.nextInt(10);
        signParams.put("price", price + "");
        signParams.put("amount", "1");
        // 请求地址：该地址为测试地址
        String url = "http://localhost:9999/api/publics/v1/trade";
        // 构建请求参数
        List<NameValuePair> params = new ArrayList<>();
        // 公钥为post请求必填参数
        params.add(new BasicNameValuePair("apikey", "19EDB6227BED371FB475680739BD45B2"));
        params.add(new BasicNameValuePair("symbol", "BOPO_USDT"));
        params.add(new BasicNameValuePair("type", isBuy ? "buy" : "sell"));
        params.add(new BasicNameValuePair("price", price + ""));
        params.add(new BasicNameValuePair("amount", "1"));
        // 构建签名，请求参数和私钥
        String sign = SignUtil.buildMysign(signParams, "CBD72C9A50AB8FA1EF4E902FD113E201");

        params.add(new BasicNameValuePair("sign", sign));

        String userInfo = getWeChatUserInfo(url, params);
        // 响应结果
        logger.info(userInfo);

    }

    public String getWeChatUserInfo(String url, List<NameValuePair> params) {

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
        // System.out.println("响应结果：" + JSON.toJSONString(result));

        return result;
    }

}
