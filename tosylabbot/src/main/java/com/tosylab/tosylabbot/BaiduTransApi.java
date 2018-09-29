package com.tosylab.tosylabbot;

import com.alibaba.fastjson.JSONObject;
import com.mashape.unirest.http.Unirest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

@Component
public class BaiduTransApi {

    @Value("${baidutrans.url}")
    private String baiduTransUrl = "https://fanyi-api.baidu.com/api/trans/vip/translate";
    @Value("${baidutrans.appid}")
    private String appid = "20180929000214029";
    @Value("${baidutrans.key}")
    private String securityKey = "tqo39C1Q7ZRb23Wz0GU8";

    public String getTransResult(String query, String from, String to) throws Exception {
        Map<String, String> params = buildParams(query, from, to);
        System.out.println("1 " + baiduTransUrl + " 2 " + JSONObject.toJSONString(params));
        return Unirest.post(baiduTransUrl).header("Content-Type", "application/json")
                .body(JSONObject.toJSONString(params))
                .asString()
                .getBody();
    }

    private Map<String, String> buildParams(String query, String from, String to) throws NoSuchAlgorithmException {
        Map<String, String> params = new HashMap<>();
        params.put("q", query);
        params.put("from", from);
        params.put("to", to);
        params.put("appid", appid);
        // 随机数
        String salt = String.valueOf(System.currentTimeMillis());
        params.put("salt", salt);
        // 签名
        String src = appid + query + salt + securityKey; // 加密前的原文
//        params.put("sign", MD5.md5(src));
        params.put("sign", MessageDigest.getInstance("MD5").digest(src.getBytes()).toString().toLowerCase());
        return params;
    }

    public static void main(String[] args) throws Exception {
        String rt = new BaiduTransApi().getTransResult("hello","auto","zh");
        System.out.println("get " + rt);
    }
}
