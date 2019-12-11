package edu.lixin.weixin.model;

import java.util.Objects;

public class Notice {
    private int notice_id;
    private String notice_title;
    private String notice_text_content;
    private String notice_img_path;
    private String notice_type;
    private String notice_date;
    private String notice_state;
    private int user_id;

    public int getNotice_id() {
        return notice_id;
    }

    public void setNotice_id(int notice_id) {
        this.notice_id = notice_id;
    }

    public String getNotice_title() {
        return notice_title;
    }

    public void setNotice_title(String notice_title) {
        this.notice_title = notice_title;
    }

    public String getNotice_text_content() {
        return notice_text_content;
    }

    public void setNotice_text_content(String notice_text_content) {
        this.notice_text_content = notice_text_content;
    }

    public String getNotice_img_path() {
        return notice_img_path;
    }

    public void setNotice_img_path(String notice_img_path) {
        this.notice_img_path = notice_img_path;
    }

    public String getNotice_type() {
        return notice_type;
    }

    public void setNotice_type(String notice_type) {
        this.notice_type = notice_type;
    }

    public String getNotice_date() {
        return notice_date;
    }

    public void setNotice_date(String notice_date) {
        this.notice_date = notice_date;
    }

    public String getNotice_state() {
        return notice_state;
    }

    public void setNotice_state(String notice_state) {
        this.notice_state = notice_state;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return "Notice{" +
                "notice_id=" + notice_id +
                ", notice_title='" + notice_title + '\'' +
                ", notice_text_content='" + notice_text_content + '\'' +
                ", notice_img_path='" + notice_img_path + '\'' +
                ", notice_type='" + notice_type + '\'' +
                ", notice_date='" + notice_date + '\'' +
                ", notice_state='" + notice_state + '\'' +
                ", user_id=" + user_id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Notice notice = (Notice) o;
        return notice_id == notice.notice_id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(notice_id);
    }
}
