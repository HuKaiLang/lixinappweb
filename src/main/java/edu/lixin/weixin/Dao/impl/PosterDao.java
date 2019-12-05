package edu.lixin.weixin.Dao.impl;

import edu.lixin.utils.JdbcUtils;
import edu.lixin.utils.PageBean;
import edu.lixin.weixin.Dao.IPostersDao;
import edu.lixin.weixin.model.Poster;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.util.List;

public class PosterDao implements IPostersDao {
    private QueryRunner qr = JdbcUtils.getQueryRunner();

    @Override
    public void add(Poster poster) {
        String sql = "insert into posters(poster_id,poster_title,poster_file_name) values(?,?,?);";
        try{
            qr.update(sql,poster.getPoster_id(),poster.getPoster_title(),poster.getPoster_file_name());
        }catch (Exception e){
            System.out.println("--- PosterDao Exception ---");
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(int poster_id) {
        String sql = "delete from posters where poster_id=?";
        try{
            qr.update(sql,poster_id);
        }catch (Exception e){
            System.out.println("--- PosterDao Exception ---");
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Poster poster) {
        String sql = "update posters set poster_title=?,poster_file_name=? where poster_id=?";
        try{
            qr.update(sql,poster.getPoster_title(),poster.getPoster_file_name(),poster.getPoster_id());
        }catch (Exception e){
            System.out.println("--- PosterDao Exception ---");
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Poster> query() {
        try{
            String sql = "select * from posters";
            return qr.query(sql,new BeanListHandler<Poster>(Poster.class));
        }catch(Exception e){
            System.out.println("--- PosterDao Exception ---");
            throw new RuntimeException(e);
        }
    }

    @Override
    public Poster findById(int poster_id) {
        try{
            String sql = "select * from posters where poster_id=?";
            return qr.query(sql,new BeanHandler<Poster>(Poster.class),poster_id);
        } catch(Exception e){
            System.out.println("--- PosterDao Exception ---");
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Poster> query(String keyword) {
        try{
            String sql = "select * from posters where poster_title like ?";
            return qr.query(sql,new BeanListHandler<Poster>(Poster.class),"%"+keyword+"%");
        }catch(Exception e){
            System.out.println("--- PosterDao Exception ---");
            throw new RuntimeException(e);
        }
    }

    @Override
    public int getTotalCount(PageBean<Poster> pb) {
        try{
            String sql = "select * from posters";
            Long count = qr.query(sql,new ScalarHandler<Long>());
            return count.intValue();
        }catch(Exception e){
            System.out.println("--- PosterDao Exception ---");
            throw new RuntimeException(e);
        }
    }
}
