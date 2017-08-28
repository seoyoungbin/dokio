package com.dokio.tacademy.pedokio.model;

/**
 * Created by Tacademy on 2017-08-14.
 */

public class RootPrice
{
    // 응답에 관련된 모든 개별적인 내용을 추가
    int weight;
    int price;

    public RootPrice(int weight, int price) {
        this.weight = weight;
        this.price = price;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}




