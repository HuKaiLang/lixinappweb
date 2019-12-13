package edu.lixin.weixin.Dao.impl;

import edu.lixin.utils.JdbcUtils;
import edu.lixin.utils.PageBean;
import edu.lixin.weixin.Dao.IArticleDao;
import edu.lixin.weixin.model.Article;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.util.List;

public class ArticleDao implements IArticleDao {
    private QueryRunner qr = JdbcUtils.getQueryRunner();
    @Override
    public void add(Article article){
        String sql = "insert into article(article_title," +
                "article_content," +
                "article_date," +
                "article_state," +
                "article_img_path," +
                "article_author) values(?,?,?,?,?,?)";
        try{
            qr.update(sql,article.getArticle_title(),
                    article.getArticle_content(),
                    article.getArticle_date(),
                    article.getArticle_state(),
                    article.getArticle_img_path(),
                    article.getArticle_author());
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(int article_id) {
        String sql = "delete from article where article_id="+article_id;
        try{
            qr.update(sql);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Article article) {
        String sql = "update article set article_title=?," +
                "article_content=?," +
                "article_date=?," +
                "article_state=?," +
                "article_img_path=?," +
                "article_author=? where article_id="+article.getArticle_id();
        try{
            qr.update(sql,
                    article.getArticle_title(),
                    article.getArticle_content(),
                    article.getArticle_date(),
                    article.getArticle_state(),
                    article.getArticle_img_path(),
                    article.getArticle_author()
                    );
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Article> query() {
        String sql = "select * from article order by article_date desc";
        try{
            return qr.query(sql,new BeanListHandler<Article>(Article.class));
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Article> query_active() {
        String sql = "select * from article where article_state='active' order by article_date desc";
        try{
            return qr.query(sql,new BeanListHandler<Article>(Article.class));
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public Article findById(int article_id) {
        String sql = "select * from article where article_id=?";
        try{
            return qr.query(sql,new BeanHandler<Article>(Article.class),article_id);
        } catch(Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Article> query(String keyword) {
        try{
            String sql = "select * from article where article_title like ? order by article_date desc";
            return qr.query(sql,new BeanListHandler<Article>(Article.class),"%"+keyword+"%");
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void getAll(PageBean<Article> pb) {

    }

    @Override
    public int getTotalCount(PageBean<Article> pb) {
        try{
            String sql = "select * from article";
            Long count = qr.query(sql,new ScalarHandler<Long>());
            return count.intValue();
        }catch(Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
