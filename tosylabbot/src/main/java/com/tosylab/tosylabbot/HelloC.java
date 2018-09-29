package com.tosylab.tosylabbot;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.mashape.unirest.http.Unirest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class HelloC {
    private Logger logger = LoggerFactory.getLogger(HelloC.class);


    @Value("${telegram.token}")
    private String telegramToken;
    @Value("${telegram.url}")
    private String telegramUrl;


    @RequestMapping("/",method = RequestMethod.POST)
    public String index(HttpServletRequest req) throws Exception{
        String body = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        logger.info("get request ... " + body);
        StringBuilder builder = new StringBuilder(telegramUrl).append(telegramToken).append("/sendMessage?chat_id=672868707&text=hello");
        logger.info("send url = {}",builder.toString());
        Unirest.get(builder.toString()).asJsonAsync();
        return "OK";
    }

    private Map<String,String> getParameters(HttpServletRequest request) {
        Map<String, String> map = Maps.newHashMap();
        Enumeration<String> paramNames = request.getParameterNames();
        while (paramNames.hasMoreElements()) {
            String paramName = paramNames.nextElement();
            String[] paramValues = request.getParameterValues(paramName);
            if (paramValues != null && paramValues.length > 0) {
                map.put(paramName, paramValues[0]);
            }
        }
        return map;
    }
}
