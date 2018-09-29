package com.tosylab.tosylabbot.model;

public class ModelTelegramUpdate {
    long update_id;
    ModelTelegramMessage message;

    public long getUpdate_id() {
        return update_id;
    }

    public void setUpdate_id(long update_id) {
        this.update_id = update_id;
    }

    public ModelTelegramMessage getMessage() {
        return message;
    }

    public void setMessage(ModelTelegramMessage message) {
        this.message = message;
    }
}
