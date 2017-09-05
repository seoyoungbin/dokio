package com.dokio.tacademy.pedokio.model;

import java.util.ArrayList;

/**
 * Created by Tacademy on 2017-08-14.
 */

public class RootMemoMessage
{
    String date;
    ArrayList<String> content;
    String _id;

    public RootMemoMessage(String date, ArrayList<String> content, String _id) {
        this.date = date;
        this.content = content;
        this._id = _id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public ArrayList<String> getContent() {
        return content;
    }

    public void setContent(ArrayList<String> content) {
        this.content = content;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }
}




