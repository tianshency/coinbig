package com.copyright.coinbig.service;

import com.copyright.coinbig.utils.HttpUtil;
import com.copyright.coinbig.utils.SignUtil;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.logging.Logger;

@Service
public class UserService {

    private Logger logger = Logger.getLogger("UserService.class");

    public void orderFee() {
        Long date = new Date().getTime();
        Map<String, String> signParams = new HashMap<>();
        signParams.put("apikey", "19EDB6227BED371FB475680739BD45B2");
        signParams.put("time", date.toString());
        signParams.put("symbol", "btc_usdt");
        signParams.put("order_id", "123456789");
        // 请求地址：该地址为测试地址
        String url = "https://www.coinbig.com/api/publics/v1/order_fee";
        // 构建请求参数
        List<NameValuePair> params = new ArrayList<>();
        // 公钥为post请求必填参数
        params.add(new BasicNameValuePair("apikey", "19EDB6227BED371FB475680739BD45B2"));
        params.add(new BasicNameValuePair("time", date.toString()));
        params.add(new BasicNameValuePair("symbol", "btc_usdt"));
        params.add(new BasicNameValuePair("order_id", "123456789"));
        // 构建签名，请求参数和私钥
        String sign = SignUtil.buildMysign(signParams, "CBD72C9A50AB8FA1EF4E902FD113E201");

        params.add(new BasicNameValuePair("sign", sign));

        String orderFee = HttpUtil.getWeChatUserInfo(url, params);
        // 响应结果
        logger.info(orderFee);
    }

    public void userInfo() {
        Long date = new Date().getTime();
        Map<String, String> signParams = new HashMap<>();
        signParams.put("apikey", "9381986A19659AD75BBF74483A8E0CA2");
        signParams.put("time", date.toString());
        // 请求地址：获取用户资产信息
        String url = "https://www.coinbig.com/api/publics/v1/userinfo";
        // 构建请求参数
        List<NameValuePair> params = new ArrayList<>();
        // 公钥为post请求必填参数
        params.add(new BasicNameValuePair("apikey", "9381986A19659AD75BBF74483A8E0CA2"));
        params.add(new BasicNameValuePair("time", date.toString()));
        // 构建签名，请求参数和私钥
        String sign = SignUtil.buildMysign(signParams, "E81A5DE3A8BBB0A94F4E226B25CD3754");

        params.add(new BasicNameValuePair("sign", sign));

        String userInfo = HttpUtil.getWeChatUserInfo(url, params);
        // 响应结果
        logger.info(userInfo);
    }

}
