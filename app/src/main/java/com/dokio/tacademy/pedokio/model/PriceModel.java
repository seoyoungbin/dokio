package com.dokio.tacademy.pedokio.model;

public class PriceModel
{
    String priceinfo1;
    String priceinfo2;

    public PriceModel() {
    }

    public PriceModel(String priceinfo1, String priceinfo2) {
        this.priceinfo1 = priceinfo1;
        this.priceinfo2 = priceinfo2;
    }

    public String getPriceinfo1() {
        return priceinfo1;
    }

    public void setPriceinfo1(String priceinfo1) {
        this.priceinfo1 = priceinfo1;
    }

    public String getPriceinfo2() {
        return priceinfo2;
    }

    public void setPriceinfo2(String priceinfo2) {
        this.priceinfo2 = priceinfo2;
    }
}
