package com.dokio.tacademy.pedokio.model;

public class MainAdvertiseModel
{
    String advimg;
    String advtitle;
    String advaddress;

    public MainAdvertiseModel(String advimg, String advtitle, String advaddress) {
        this.advimg = advimg;
        this.advtitle = advtitle;
        this.advaddress = advaddress;
    }

    public String getAdvimg() {
        return advimg;
    }

    public void setAdvimg(String advimg) {
        this.advimg = advimg;
    }

    public String getAdvtitle() {
        return advtitle;
    }

    public void setAdvtitle(String advtitle) {
        this.advtitle = advtitle;
    }

    public String getAdvaddress() {
        return advaddress;
    }

    public void setAdvaddress(String advaddress) {
        this.advaddress = advaddress;
    }
}
