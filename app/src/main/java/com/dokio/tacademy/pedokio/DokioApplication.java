package com.dokio.tacademy.pedokio;

import android.app.Application;
import android.content.Context;

import com.dokio.tacademy.pedokio.net.Net;
import com.kakao.auth.ApprovalType;
import com.kakao.auth.AuthType;
import com.kakao.auth.IApplicationConfig;
import com.kakao.auth.ISessionConfig;
import com.kakao.auth.KakaoAdapter;
import com.kakao.auth.KakaoSDK;
import com.miguelbcr.ui.rx_paparazzo2.RxPaparazzo;

/**
 * kako login, 65k (Dex...), camera paparazo(open source 등),
 */

public class DokioApplication extends Application
{

    // 파파라조 라이브러리 등록

    // 동기화에 나오는 내용으로 최신 값을 항상 유지한다.
    static volatile DokioApplication self;
    @Override
    public void onCreate() {
        super.onCreate();
        self = this;
// 레트로핏 초기화
        Net.getInstance().launch(this);
        // kakao
        KakaoSDK.init(new KakaoSDKAdapter());
        RxPaparazzo.register(this);

    }

    public static DokioApplication getDokioApplicationContext()
    {
        return self;
    }
    private static class KakaoSDKAdapter extends KakaoAdapter {
        /**
         * Session Config에 대해서는 default값들이 존재한다.
         * 필요한 상황에서만 override해서 사용하면 됨.
         * @return Session의 설정값.
         */
        @Override
        public ISessionConfig getSessionConfig() {
            return new ISessionConfig() {
                @Override
                public AuthType[] getAuthTypes() {
                    return new AuthType[] {AuthType.KAKAO_LOGIN_ALL};
                }

                @Override
                public boolean isUsingWebviewTimer() {
                    return false;
                }

                @Override
                public boolean isSecureMode() {
                    return false;
                }

                @Override
                public ApprovalType getApprovalType() {
                    return ApprovalType.INDIVIDUAL;
                }

                @Override
                public boolean isSaveFormData() {
                    return true;
                }
            };
        }

        @Override
        public IApplicationConfig getApplicationConfig() {
            return new IApplicationConfig() {
                @Override
                public Context getApplicationContext() {
                    return DokioApplication.getDokioApplicationContext();
                }
            };
        }
    }

}
