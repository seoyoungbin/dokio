package com.dokio.tacademy.pedokio.model;

public class NaverLoginModel
{
    int resultcode;
    String message;
    RootNaverLogin response;

    public NaverLoginModel(int resultcode, String message, RootNaverLogin response) {
        this.resultcode = resultcode;
        this.message = message;
        this.response = response;
    }

    public int getResultcode() {
        return resultcode;
    }

    public void setResultcode(int resultcode) {
        this.resultcode = resultcode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public RootNaverLogin getResponse() {
        return response;
    }

    public void setResponse(RootNaverLogin response) {
        this.response = response;
    }
}
