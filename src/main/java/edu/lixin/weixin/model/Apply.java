package edu.lixin.weixin.model;

import java.util.Objects;

public class Apply {
    private int apply_id;
    private String apply_type;
    private String apply_name;
    private String user_school_id;
    private String apply_reason;
    private String apply_grade;
    private String apply_department;
    private String apply_state;
    private String apply_create_date;

    public int getApply_id() {
        return apply_id;
    }

    public void setApply_id(int apply_id) {
        this.apply_id = apply_id;
    }

    public String getApply_type() {
        return apply_type;
    }

    public void setApply_type(String apply_type) {
        this.apply_type = apply_type;
    }

    public String getApply_name() {
        return apply_name;
    }

    public void setApply_name(String apply_name) {
        this.apply_name = apply_name;
    }

    public String getUser_school_id() {
        return user_school_id;
    }

    public void setUser_school_id(String user_school_id) {
        this.user_school_id = user_school_id;
    }

    public String getApply_reason() {
        return apply_reason;
    }

    public void setApply_reason(String apply_reason) {
        this.apply_reason = apply_reason;
    }

    public String getApply_grade() {
        return apply_grade;
    }

    public void setApply_grade(String apply_grade) {
        this.apply_grade = apply_grade;
    }

    public String getApply_department() {
        return apply_department;
    }

    public void setApply_department(String apply_department) {
        this.apply_department = apply_department;
    }

    public String getApply_state() {
        return apply_state;
    }

    public void setApply_state(String apply_state) {
        this.apply_state = apply_state;
    }

    public String getApply_create_date() {
        return apply_create_date;
    }

    public void setApply_create_date(String apply_create_date) {
        this.apply_create_date = apply_create_date;
    }

    @Override
    public String toString() {
        return "Apply{" +
                "apply_id=" + apply_id +
                ", apply_type='" + apply_type + '\'' +
                ", apply_name='" + apply_name + '\'' +
                ", user_school_id='" + user_school_id + '\'' +
                ", apply_reason='" + apply_reason + '\'' +
                ", apply_grade='" + apply_grade + '\'' +
                ", apply_department='" + apply_department + '\'' +
                ", apply_state='" + apply_state + '\'' +
                ", apply_create_date='" + apply_create_date + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Apply apply = (Apply) o;
        return apply_id == apply.apply_id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(apply_id);
    }
}
