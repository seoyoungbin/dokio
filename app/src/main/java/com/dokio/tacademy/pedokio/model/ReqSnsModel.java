package com.dokio.tacademy.pedokio.model;

/**
 * Created by Tacademy on 2017-08-14.
 */

public class ReqSnsModel
{
    String username;
    String myprof_img;

    public ReqSnsModel(String username, String myprof_img) {
        this.username = username;
        this.myprof_img = myprof_img;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMyprof_img() {
        return myprof_img;
    }

    public void setMyprof_img(String myprof_img) {
        this.myprof_img = myprof_img;
    }
}
