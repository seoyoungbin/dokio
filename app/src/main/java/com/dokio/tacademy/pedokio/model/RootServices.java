package com.dokio.tacademy.pedokio.model;

/**
 * Created by Tacademy on 2017-08-14.
 */

public class RootServices
{
    // 응답에 관련된 모든 개별적인 내용을 추가
    String service_name;

    public RootServices(String service_name) {
        this.service_name = service_name;
    }

    public String getService_name() {
        return service_name;
    }

    public void setService_name(String service_name) {
        this.service_name = service_name;
    }
}




