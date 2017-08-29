package com.dokio.tacademy.pedokio;

import android.app.Service;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;

/**
 * GPS 디텍팅한는 코드 (최신 방법론 이전 방법)
 */
public class GpsDetectService extends Service implements LocationListener {
    public GpsDetectService() {
    }
    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // 코드는 여기서 부터 진행
        initLocation();
        return super.onStartCommand(intent, flags, startId);
    }
    LocationManager locationManager;
    final long MIN_UPDTAE_TIME      = 1000*60*1; // 1분 단위 갱신
    final float MIN_UPDATE_DISTANCE = 10.0f;     // 10m 단위 갱신
    public void initLocation()
    {
        // 1. 위치 매니저 획득
        locationManager = (LocationManager)getSystemService(LOCATION_SERVICE);
        // 2. 위치 정보를 제공하는 공급자 (3개) 가용여부 획득
        boolean isGpsOn  = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);    // 센서
        boolean isNetOn  = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);// WIFI
        boolean isPassOn = locationManager.isProviderEnabled(LocationManager.PASSIVE_PROVIDER);// 기지국
        // 3. 각 공급자 별로 처리
        if( !isGpsOn && !isNetOn && !isPassOn ){
            sendGpsBus(null);
            return;
        }

        if(isGpsOn){
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                    MIN_UPDTAE_TIME,
                    MIN_UPDATE_DISTANCE,
                    this
                    );
            Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if(location != null)
                sendGpsBus(location);
        }
        if(isNetOn){
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,
                    MIN_UPDTAE_TIME,
                    MIN_UPDATE_DISTANCE,
                    this
            );
            Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            if(location != null)
                sendGpsBus(location);
        }
        if(isPassOn){
            locationManager.requestLocationUpdates(LocationManager.PASSIVE_PROVIDER,
                    MIN_UPDTAE_TIME,
                    MIN_UPDATE_DISTANCE,
                    this
            );
            Location location = locationManager.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER);
            if(location != null)
                sendGpsBus(location);
        }
        // 4. 어느 쪽이던 데이터가 오면 BUS로 전송
    }
    public void freeLocation()
    {
        // 각종 설정된 리너너등 이벤트 해제 및 디텍팅 중단
        locationManager.removeUpdates(this);
    }
    // =============================================================================================
    @Override
    public void onLocationChanged(Location location) {
        sendGpsBus(location);
    }
    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {
    }
    @Override
    public void onProviderEnabled(String s) {
    }
    @Override
    public void onProviderDisabled(String s) {
    }
    // =============================================================================================
    public void sendGpsBus(Location location)
    {
        // GPS 값을 Bus로 전송한다.
        U.getInstance().log(location.getLatitude()+","+location.getLongitude());
        U.getInstance().getGpsBus().post(location);
    }
    @Override
    public void onDestroy() {
        freeLocation();
        super.onDestroy();
    }
}











