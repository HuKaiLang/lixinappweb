package edu.lixin.weixin.model;

import java.util.Objects;

public class Article {
    private int article_id;
    private String article_title;
    private String article_content;
    private String article_date;
    private String article_state;
    private String article_img_path;
    private String article_author;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Article article = (Article) o;
        return article_id == article.article_id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(article_id);
    }

    @Override
    public String toString() {
        return "Article{" +
                "article_id=" + article_id +
                ", article_title='" + article_title + '\'' +
                ", article_content='" + article_content + '\'' +
                ", article_date='" + article_date + '\'' +
                ", article_state='" + article_state + '\'' +
                ", article_img_path='" + article_img_path + '\'' +
                ", article_author='" + article_author + '\'' +
                '}';
    }

    public int getArticle_id() {
        return article_id;
    }

    public void setArticle_id(int article_id) {
        this.article_id = article_id;
    }

    public String getArticle_title() {
        return article_title;
    }

    public void setArticle_title(String article_title) {
        this.article_title = article_title;
    }

    public String getArticle_content() {
        return article_content;
    }

    public void setArticle_content(String article_content) {
        this.article_content = article_content;
    }

    public String getArticle_date() {
        return article_date;
    }

    public void setArticle_date(String article_date) {
        this.article_date = article_date;
    }

    public String getArticle_state() {
        return article_state;
    }

    public void setArticle_state(String article_state) {
        this.article_state = article_state;
    }

    public String getArticle_img_path() {
        return article_img_path;
    }

    public void setArticle_img_path(String article_img_path) {
        this.article_img_path = article_img_path;
    }

    public String getArticle_author() {
        return article_author;
    }

    public void setArticle_author(String article_author) {
        this.article_author = article_author;
    }
}
