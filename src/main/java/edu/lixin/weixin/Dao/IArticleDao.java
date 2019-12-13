package edu.lixin.weixin.Dao;

import edu.lixin.utils.PageBean;
import edu.lixin.weixin.model.Article;

import java.util.List;

public interface IArticleDao {
    void add(Article article);

    void delete(int article_id);

    void update(Article article);

    List<Article> query();

    List<Article> query_active();

    Article findById(int article_id);

    List<Article>query(String keyword);

    void getAll(PageBean<Article> pb);

    int getTotalCount(PageBean<Article> pb);
}
