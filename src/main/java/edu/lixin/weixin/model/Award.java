package edu.lixin.weixin.model;

import java.util.Objects;

public class Award {
    private int award_id;
    private String user_school_id;
    private String award_type;
    private String award_request_reason;
    private String award_request_date;
    private float award_grade_point;
    private String award_request_deed;
    private String award_request_state;

    public int getAward_id() {
        return award_id;
    }

    public void setAward_id(int award_id) {
        this.award_id = award_id;
    }

    public String getUser_school_id() {
        return user_school_id;
    }

    public void setUser_school_id(String user_school_id) {
        this.user_school_id = user_school_id;
    }

    public String getAward_type() {
        return award_type;
    }

    public void setAward_type(String award_type) {
        this.award_type = award_type;
    }

    public String getAward_request_reason() {
        return award_request_reason;
    }

    public void setAward_request_reason(String award_request_reason) {
        this.award_request_reason = award_request_reason;
    }

    public String getAward_request_date() {
        return award_request_date;
    }

    public void setAward_request_date(String award_request_date) {
        this.award_request_date = award_request_date;
    }

    public float getAward_grade_point() {
        return award_grade_point;
    }

    public void setAward_grade_point(float award_grade_point) {
        this.award_grade_point = award_grade_point;
    }

    public String getAward_request_deed() {
        return award_request_deed;
    }

    public void setAward_request_deed(String award_request_deed) {
        this.award_request_deed = award_request_deed;
    }

    public String getAward_request_state() {
        return award_request_state;
    }

    public void setAward_request_state(String award_request_state) {
        this.award_request_state = award_request_state;
    }

    @Override
    public String toString() {
        return "Award{" +
                "award_id=" + award_id +
                ", user_school_id='" + user_school_id + '\'' +
                ", award_type='" + award_type + '\'' +
                ", award_request_reason='" + award_request_reason + '\'' +
                ", award_request_date='" + award_request_date + '\'' +
                ", award_grade_point=" + award_grade_point +
                ", award_request_deed='" + award_request_deed + '\'' +
                ", award_request_state='" + award_request_state + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Award award = (Award) o;
        return award_id == award.award_id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(award_id);
    }
}
