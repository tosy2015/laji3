package com.tosylab.tosylabbot.baidu;

import com.alibaba.fastjson.JSONObject;
import com.mashape.unirest.http.Unirest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.URLEncoder;
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

    public ModelBaiduReturn getTransResult(String query, String from, String to) throws Exception {
        Map<String, String> params = buildParams(query, from, to);

        System.out.println("1 " + baiduTransUrl + "\n 2 " + JSONObject.toJSONString(params));
        StringBuilder url = new StringBuilder(baiduTransUrl).append("?");
        for (Map.Entry item  : params.entrySet()){
            url.append(item.getKey()).append("=").append(item.getValue()).append("&");

        }
        return JSONObject.parseObject(
                Unirest.get(url.toString()).asString().getBody()
                ,ModelBaiduReturn.class);
    }

    private Map<String, String> buildParams(String query, String from, String to) throws Exception {
        Map<String, String> params = new HashMap<>();
        params.put("q", URLEncoder.encode(query));
        params.put("from", from);
        params.put("to", to);
        params.put("appid", appid);
        // 随机数
        String salt = String.valueOf(System.currentTimeMillis());
        params.put("salt", salt);
        // 签名
        String src = appid + query + salt + securityKey; // 加密前的原文
        params.put("sign", MD5.md5(src));
        return params;
    }

    public static void main(String[] args) throws Exception {
        ModelBaiduReturn rt = new BaiduTransApi().getTransResult("fuck you","auto","zh");
        System.out.println("get " + JSONObject.toJSONString(rt));
    }
}
