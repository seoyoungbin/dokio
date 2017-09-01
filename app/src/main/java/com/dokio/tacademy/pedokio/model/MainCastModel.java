package com.dokio.tacademy.pedokio.model;

public class MainCastModel
{
    String adtitle;
    String adimg;

    public MainCastModel(String adtitle, String adimg) {
        this.adtitle = adtitle;
        this.adimg = adimg;
    }

    public String getAdtitle() {
        return adtitle;
    }

    public void setAdtitle(String adtitle) {
        this.adtitle = adtitle;
    }

    public String getAdimg() {
        return adimg;
    }

    public void setAdimg(String adimg) {
        this.adimg = adimg;
    }
}
