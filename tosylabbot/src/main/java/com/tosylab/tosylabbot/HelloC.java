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


    @RequestMapping("/")
    public String index(HttpServletRequest req) throws Exception{
        String body = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        logger.info("get request ... " + body);
        ModelTelegramUpdate update = JSONObject.parseObject(body,ModelTelegramUpdate.class);
//        logger.info("get body ... " + JSONObject.toJSONString(update));

        StringBuilder builder = new StringBuilder(telegramUrl).append(telegramToken).append("/sendMessage?chat_id=672868707&text=hello");
//        logger.info("send url = {}",builder.toString());
        Unirest.get(builder.toString()).asJsonAsync();


        StringBuilder builderPost = new StringBuilder(telegramUrl).append(telegramToken).append("/sendMessage");
        ModelSendText sendText = new ModelSendText();
        sendText.setChat_id(update.getMessage().getChat().getId());
        sendText.setText(getDefaultText());
        logger.info("body " + JSONObject.toJSONString(sendText));
        Unirest.post(builderPost.toString()).body(JSONObject.toJSONString(sendText)).asJsonAsync();
        return "OK";
    }

    private String getDefaultText() {
        return "hello2";
//        return "[inline URL](https://www.tosylab.com)";
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
