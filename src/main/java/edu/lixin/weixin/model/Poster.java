package edu.lixin.weixin.model;

import java.util.Objects;

public class Poster {
    private int poster_id;
    private String poster_title;
    private String poster_file_name;

    public int getPoster_id() {
        return poster_id;
    }

    public void setPoster_id(int poster_id) {
        this.poster_id = poster_id;
    }

    public String getPoster_title() {
        return poster_title;
    }

    public void setPoster_title(String poster_title) {
        this.poster_title = poster_title;
    }

    public String getPoster_file_name() {
        return poster_file_name;
    }

    public void setPoster_file_name(String poster_file_name) {
        this.poster_file_name = poster_file_name;
    }

    @Override
    public String toString() {
        return "Poster{" +
                "poster_id=" + poster_id +
                ", poster_title='" + poster_title + '\'' +
                ", poster_file_name='" + poster_file_name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Poster poster = (Poster) o;
        return poster_id == poster.poster_id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(poster_id);
    }
}
