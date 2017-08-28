package com.dokio.tacademy.pedokio.model;

/**
 * Created by Tacademy on 2017-08-14.
 */

public class DetailInformationResRootModel
{
    String tr;// 전문번호 (여기서는 문자열으로 표기)
    int success_code;
    String message;
    RootMessage result;

    public DetailInformationResRootModel(String tr, int success_code, String message, RootMessage result) {
        this.tr = tr;
        this.success_code = success_code;
        this.message = message;
        this.result = result;
    }

    public String getTr() {
        return tr;
    }

    public void setTr(String tr) {
        this.tr = tr;
    }

    public int getSuccess_code() {
        return success_code;
    }

    public void setSuccess_code(int success_code) {
        this.success_code = success_code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public RootMessage getResult() {
        return result;
    }

    public void setResult(RootMessage result) {
        this.result = result;
    }
}
