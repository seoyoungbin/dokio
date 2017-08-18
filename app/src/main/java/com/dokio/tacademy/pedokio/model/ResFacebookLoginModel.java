package com.dokio.tacademy.pedokio.model;

/**
 * Created by Tacademy on 2017-08-14.
 */

public class ResFacebookLoginModel
{
    String tr;// 전문번호 (여기서는 문자열으로 표기)
    int successCode;
    String result;

    public ResFacebookLoginModel(String tr, int successCode, String result) {
        this.tr = tr;
        this.successCode = successCode;
        this.result = result;
    }

    public String getTr() {
        return tr;
    }

    public void setTr(String tr) {
        this.tr = tr;
    }

    public int getSuccessCode() {
        return successCode;
    }

    public void setSuccessCode(int successCode) {
        this.successCode = successCode;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
