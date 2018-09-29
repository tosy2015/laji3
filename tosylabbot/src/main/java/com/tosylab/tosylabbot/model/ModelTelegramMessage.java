package com.tosylab.tosylabbot.model;

import java.util.List;

public class ModelTelegramMessage {
    long message_id;
    ModelFrom from;
    ModelChat chat;
    long date;
    String text;
    List<ModelEntities> entities;

    public long getMessage_id() {
        return message_id;
    }

    public void setMessage_id(long message_id) {
        this.message_id = message_id;
    }

    public ModelFrom getFrom() {
        return from;
    }

    public void setFrom(ModelFrom from) {
        this.from = from;
    }

    public ModelChat getChat() {
        return chat;
    }

    public void setChat(ModelChat chat) {
        this.chat = chat;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<ModelEntities> getEntities() {
        return entities;
    }

    public void setEntities(List<ModelEntities> entities) {
        this.entities = entities;
    }
}
