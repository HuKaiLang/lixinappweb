package edu.lixin.weixin.model;

import java.util.Objects;

public class User {
    private int user_id;
    private String user_name;
    private int user_gender; // 0表示女性，1表示男性
    private int user_phone_number;
    private String user_email;
    private String user_identity;
    private String user_school_id;
    private float user_charge;
    private String user_account_state;
    private String user_avatar_path;
    private String user_md5_password;
    private float user_balance;

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }


    public int getUser_phone_number() {
        return user_phone_number;
    }

    public void setUser_phone_number(int user_phone_number) {
        this.user_phone_number = user_phone_number;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getUser_identity() {
        return user_identity;
    }

    public void setUser_identity(String user_identity) {
        this.user_identity = user_identity;
    }

    public String getUser_school_id() {
        return user_school_id;
    }

    public void setUser_school_id(String user_school_id) {
        this.user_school_id = user_school_id;
    }

    public float getUser_charge() {
        return user_charge;
    }

    public void setUser_charge(float user_charge) {
        this.user_charge = user_charge;
    }

    public String getUser_account_state() {
        return user_account_state;
    }

    public void setUser_account_state(String user_account_state) {
        this.user_account_state = user_account_state;
    }

    public String getUser_avatar_path() {
        return user_avatar_path;
    }

    public void setUser_avatar_path(String user_avatar_path) {
        this.user_avatar_path = user_avatar_path;
    }

    public int getUser_gender() {
        return user_gender;
    }

    public void setUser_gender(int user_gender) {
        this.user_gender = user_gender;
    }

    public String getUser_md5_password() {
        return user_md5_password;
    }

    public void setUser_md5_password(String user_md5_password) {
        this.user_md5_password = user_md5_password;
    }

    @Override
    public String toString() {
        return "User{" +
                "user_id=" + user_id +
                ", user_name='" + user_name + '\'' +
                ", user_gender=" + user_gender +
                ", user_phone_number=" + user_phone_number +
                ", user_email='" + user_email + '\'' +
                ", user_identity='" + user_identity + '\'' +
                ", user_school_id='" + user_school_id + '\'' +
                ", user_charge=" + user_charge +
                ", user_account_state='" + user_account_state + '\'' +
                ", user_avatar_path='" + user_avatar_path + '\'' +
                ", user_md5_password='" + user_md5_password + '\'' +
                ", user_balance=" + user_balance +
                '}';
    }

    public float getUser_balance() {
        return user_balance;
    }

    public void setUser_balance(float user_balance) {
        this.user_balance = user_balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return user_id == user.user_id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(user_id);
    }
}
