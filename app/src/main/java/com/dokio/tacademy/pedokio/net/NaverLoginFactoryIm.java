package com.dokio.tacademy.pedokio.net;

import com.dokio.tacademy.pedokio.model.NaverLoginModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

/**
 * Created by Tacademy on 2017-09-08.
 */

public interface NaverLoginFactoryIm {

    @GET("v1/nid/me")
    Call<NaverLoginModel> getUser(@Header("Authorization") String AccessToken);
}


