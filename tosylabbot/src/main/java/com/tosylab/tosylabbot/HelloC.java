package com.tosylab.tosylabbot;

import com.alibaba.fastjson.JSONObject;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
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

        //get
//        StringBuilder builder = new StringBuilder(telegramUrl).append(telegramToken).append("/sendMessage?chat_id=672868707&text=hello");
//        logger.info("send url = {}",builder.toString());
//        Unirest.get(builder.toString()).asJsonAsync();

        //post
        StringBuilder builderPost = new StringBuilder(telegramUrl).append(telegramToken).append("/sendMessage");
        ModelSendText hello = new ModelSendText();
        hello.setChat_id(update.getMessage().getChat().getId());
        hello.setText("hello " + update.getMessage().getFrom().getUsername());

        logger.info("body " + JSONObject.toJSONString(hello));
        Unirest.post(builderPost.toString())
                .header("Content-Type", "application/json")
                .body(JSONObject.toJSONString(hello)).asJsonAsync();

        ModelSendText mainpage = new ModelSendText();
        mainpage.setChat_id(update.getMessage().getChat().getId());
        mainpage.setParse_mode("markdown");
        mainpage.setText("[inline URL](https://www.tosylab.com)");

        return "OK";
    }


    public static void main(String[] args) throws UnirestException {

        StringBuilder builderPost = new StringBuilder("https://api.telegram.org/bot")
                .append("580249700:AAGSAE0bewvzOMd4vZMCy3TkjkXxPTSYxvk").append("/sendMessage");
        ModelSendText sendText = new ModelSendText();
        sendText.setChat_id(672868707);
        sendText.setText("hi");
        HttpResponse<String> rt = Unirest.post(builderPost.toString())
                .header("Content-Type", "application/json")
                .body(JSONObject.toJSONString(sendText)).asString();
        System.out.println("x" + JSONObject.toJSONString(rt));
    }
}
