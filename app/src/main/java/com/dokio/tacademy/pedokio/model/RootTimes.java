package com.dokio.tacademy.pedokio.model;

/**
 * Created by Tacademy on 2017-08-14.
 */

public class RootTimes
{
    // 응답에 관련된 모든 개별적인 내용을 추가
    String weekday;
    String weekend;
    String ectinfo;

    public RootTimes(String weekday, String weekend, String ectinfo) {
        this.weekday = weekday;
        this.weekend = weekend;
        this.ectinfo = ectinfo;
    }

    public String getWeekday() {
        return weekday;
    }

    public void setWeekday(String weekday) {
        this.weekday = weekday;
    }

    public String getWeekend() {
        return weekend;
    }

    public void setWeekend(String weekend) {
        this.weekend = weekend;
    }

    public String getEctinfo() {
        return ectinfo;
    }

    public void setEctinfo(String ectinfo) {
        this.ectinfo = ectinfo;
    }
}




