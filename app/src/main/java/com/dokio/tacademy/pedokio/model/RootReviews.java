package com.dokio.tacademy.pedokio.model;

/**
 * Created by Tacademy on 2017-08-14.
 */

public class RootReviews
{
    // 응답에 관련된 모든 개별적인 내용을 추가
    int _id;
    String regdate;
    String content;
    int dokio_id;
    RootUserId user_id;

    public RootReviews(int _id, String regdate, String content, int dokio_id, RootUserId user_id) {
        this._id = _id;
        this.regdate = regdate;
        this.content = content;
        this.dokio_id = dokio_id;
        this.user_id = user_id;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getRegdate() {
        return regdate;
    }

    public void setRegdate(String regdate) {
        this.regdate = regdate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getDokio_id() {
        return dokio_id;
    }

    public void setDokio_id(int dokio_id) {
        this.dokio_id = dokio_id;
    }

    public RootUserId getUser_id() {
        return user_id;
    }

    public void setUser_id(RootUserId user_id) {
        this.user_id = user_id;
    }
}




