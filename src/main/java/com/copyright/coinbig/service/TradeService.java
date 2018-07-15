package com.copyright.coinbig.service;

import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.logging.Logger;

@Service
public class TradeService {

    private Logger logger = Logger.getLogger("TradeService.class");

    private static final char HEX_DIGITS[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D',
            'E', 'F'};

    private Map<String, String> signParams = new HashMap<String, String>();

    public TradeService() {
        signParams.put("apikey", "899F4BFD47D88EC480B37095D5728C70");
        signParams.put("symbol", "btc_usdt");
        signParams.put("type", "1");
        signParams.put("size", "20");
        logger.info("sign = [ " + buildMysign(signParams, "84263AB49477D07D89B79C73C9D19ED9") + "]");
    }

    // 构造签名
    public String buildMysign(Map<String, String> params, String secret) {
        // 2.将待签名字符串要求按照参数名进行排序(首先比较所有参数名的第一个字母，按abcd顺序排列，若遇到相同首字母，则看第二个字母，以此类推)
        String result = createLinkString(params);

        // 将排序后的结果链接&secret_key=secretKey
        StringBuffer stringBuffer = new StringBuffer(result).append("&secret_key=").append(secret);

        // 利用32位MD5算法，对最终待签名字符串进行签名运算，从而得到签名结果字符串（MD5计算结果中字母全部大写）
        String sign = getMD5String(stringBuffer.toString());
        return sign;
    }

    /**
     * 生成32位大写MD5值
     */
    public String getMD5String(String str) {
        try {
            if (str == null || str.trim().length() == 0) {
                return "";
            }
            byte[] bytes = str.getBytes();
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(bytes);
            bytes = messageDigest.digest();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(HEX_DIGITS[(bytes[i] & 0xf0) >> 4] + "" + HEX_DIGITS[bytes[i] & 0xf]);
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 把数组所有元素排序，并按照“参数=参数值”的模式用“&”字符拼接成字符串
     *
     * @param params 需要排序并参与字符拼接的参数组
     * @return 拼接后字符串
     */
    public String createLinkString(Map<String, String> params) {
        List<String> keys = new ArrayList<String>(params.keySet());
        Collections.sort(keys);
        String prestr = "";
        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            String value = params.get(key);
            // 拼接时，不包括最后一个&字符
            if (i == keys.size() - 1) {
                prestr = prestr + key + "=" + value;
            } else {
                prestr = prestr + key + "=" + value + "&";
            }
        }
        return prestr;
    }
}
