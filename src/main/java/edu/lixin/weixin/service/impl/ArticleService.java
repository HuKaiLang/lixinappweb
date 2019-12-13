package edu.lixin.weixin.service.impl;

import edu.lixin.factory.BeanFactory;
import edu.lixin.utils.PageBean;
import edu.lixin.weixin.Dao.IArticleDao;
import edu.lixin.weixin.Dao.impl.ArticleDao;
import edu.lixin.weixin.model.Article;
import edu.lixin.weixin.service.IArticleService;

import java.util.List;

public class ArticleService implements IArticleService {
    IArticleDao dao = BeanFactory.getInstance("articleDao", ArticleDao.class);
    @Override
    public void add(Article article) {
        dao.add(article);
    }

    @Override
    public void delete(int article_id) {
        dao.delete(article_id);
    }

    @Override
    public void update(Article article) {
        dao.update(article);
    }

    @Override
    public List<Article> query() {
        return dao.query();
    }

    @Override
    public List<Article> query_active() {
        return dao.query_active();
    }

    @Override
    public Article findById(int article_id) {
        return dao.findById(article_id);
    }

    @Override
    public List<Article> query(String keyword) {
        return dao.query(keyword);
    }

    @Override
    public int getTotalCount(PageBean<Article> pb) {
        return dao.getTotalCount(pb);
    }
}
