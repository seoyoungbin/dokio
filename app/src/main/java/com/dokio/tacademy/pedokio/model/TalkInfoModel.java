package com.dokio.tacademy.pedokio.model;

public class TalkInfoModel
{
    String user_id;
    String content;
    String date;

    public TalkInfoModel(String user_id, String content, String date) {
        this.user_id = user_id;
        this.content = content;
        this.date = date;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
