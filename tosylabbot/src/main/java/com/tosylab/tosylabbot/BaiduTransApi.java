package com.tosylab.tosylabbot;

import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.apache.http.client.methods.HttpGet;
import org.springframework.beans.factory.annotation.Value;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

public class BaiduTransApi {

    @Value("${baidutrans.url}")
    private String baiduTransUrl;
    @Value("${baidutrans.appid}")
    private String appid;
    @Value("${baidutrans.key}")
    private String securityKey;

    static private BaiduTransApi ins =new BaiduTransApi();
    private BaiduTransApi(){}

    static BaiduTransApi getIns(){
        return ins;
    }

    public String getTransResult(String query, String from, String to) throws Exception {
        Map<String, String> params = buildParams(query, from, to);
        return Unirest.post(baiduTransUrl).body(params).asString().getBody();
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
        params.put("sign", MessageDigest.getInstance("MD5").digest(src.getBytes()).toString());
        return params;
    }
}
