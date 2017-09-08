package com.dokio.tacademy.pedokio.model;

/**
 * Created by Tacademy on 2017-09-08.
 */

public class RootNaverLogin {
    String nickname;
    String enc_id;
    String profile_image;
    String age;
    String gender;
    String id;
    String name;
    String email;
    String birthday;

    public RootNaverLogin(String nickname, String enc_id, String profile_image, String age, String gender, String id, String name, String email, String birthday) {
        this.nickname = nickname;
        this.enc_id = enc_id;
        this.profile_image = profile_image;
        this.age = age;
        this.gender = gender;
        this.id = id;
        this.name = name;
        this.email = email;
        this.birthday = birthday;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEnc_id() {
        return enc_id;
    }

    public void setEnc_id(String enc_id) {
        this.enc_id = enc_id;
    }

    public String getProfile_image() {
        return profile_image;
    }

    public void setProfile_image(String profile_image) {
        this.profile_image = profile_image;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
}
