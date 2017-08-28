package com.dokio.tacademy.pedokio.model;

/**
 * Created by Tacademy on 2017-08-14.
 */

public class RootPetcategories
{
    // 응답에 관련된 모든 개별적인 내용을 추가
    String category_name;

    public RootPetcategories(String category_name) {
        this.category_name = category_name;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }
}




