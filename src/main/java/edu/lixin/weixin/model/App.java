package edu.lixin.weixin.model;

public class App {
    private String app_name;
    private int app_id;
    private String app_status;
    private String app_page_uri;
    private String app_icon_uri;

    public String getApp_icon_uri() {
        return app_icon_uri;
    }

    public void setApp_icon_uri(String app_icon_uri) {
        this.app_icon_uri = app_icon_uri;
    }

    public String getApp_name() {
        return app_name;
    }

    public void setApp_name(String app_name) {
        this.app_name = app_name;
    }

    public int getApp_id() {
        return app_id;
    }

    public void setApp_id(int app_id) {
        this.app_id = app_id;
    }

    public String getApp_status() {
        return app_status;
    }

    public void setApp_status(String app_status) {
        this.app_status = app_status;
    }

    public String getApp_page_uri() {
        return app_page_uri;
    }

    public void setApp_page_uri(String app_page_uri) {
        this.app_page_uri = app_page_uri;
    }

    @Override
    public String toString() {
        return "App{" +
                "app_name='" + app_name + '\'' +
                ", app_id=" + app_id +
                ", app_status='" + app_status + '\'' +
                ", app_page_uri='" + app_page_uri + '\'' +
                ", app_icon_uri='" + app_icon_uri + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        App a = (App)obj;
        return a.getApp_id() == this.app_id;
    }
}
