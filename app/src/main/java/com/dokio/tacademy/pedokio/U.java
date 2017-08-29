package com.dokio.tacademy.pedokio;

import android.location.Address;
import android.util.Log;

import com.squareup.otto.Bus;

/**
 * 유틸리티
 */

public class U {
    // =============================================================================================
    public static final U ourInstance = new U();
    public static U getInstance() {
        return ourInstance;
    }
    public U() {
    }

    // 로그
    final String TAG    = "T";
    boolean isTestMode  = true;
    public void log(String m)
    {
        if(isTestMode){
            Log.i(TAG, "==========");
            Log.i(TAG, "");
            Log.i(TAG, m+"");
            Log.i(TAG, "");
            Log.i(TAG, "==========");
        }
    }
    // =============================================================================================
    // 버스
    Bus gpsBus = new Bus();
    public Bus getGpsBus() {
        return gpsBus;
    }
    // =============================================================================================
    // Address => 시군구동 표시
    public String getTransferAddr(Address address)
    {
        if( address==null ) return "";
        return String.format("%s %s %s", address.getAdminArea(), address.getLocality(), address.getThoroughfare());
    }
    // =============================================================================================
    // 좌표 변환 -> KATEC -> GEO
    public GeoPoint transGeoToKatec(GeoPoint point)
    {
        return GeoTrans.convert(GeoTrans.KATEC, GeoTrans.GEO, point);
    }
    // =============================================================================================
    // String -> double
    public double getDouble(String src)
    {
        try {
            return Double.parseDouble(src);
        } catch (NumberFormatException e) {
            return 0;
        }
    }
    public String changeBrand(String brand)
    {
        if( brand.equals("스타벅스") ){
            return "starbucks";
        }
        else if( brand.equals("커피빈") ){
            return "coffeebean";
        }
        return "";
    }

}











