package edu.lixin.weixin.service;

import edu.lixin.utils.PageBean;
import edu.lixin.weixin.model.Article;

import java.util.List;

public interface IArticleService {
    void add(Article article);

    void delete(int article_id);

    void update(Article article);

    List<Article> query();

    List<Article> query_active();

    Article findById(int article_id);

    List<Article> query(String keyword);

    int getTotalCount(PageBean<Article> pb);
}
