package edu.lixin.weixin.Dao.impl;

import edu.lixin.utils.JdbcUtils;
import edu.lixin.utils.PageBean;
import edu.lixin.weixin.Dao.IAppsDao;
import edu.lixin.weixin.model.App;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.util.ArrayList;
import java.util.List;

public class AppsDao implements IAppsDao {
    private QueryRunner qr = JdbcUtils.getQueryRunner();

    @Override
    public void add(App app) {
        String sql = "insert into apps(app_name,app_id,app_status,app_page_uri,app_icon_uri) values(?,?,?,?,?);";
        try{
            qr.update(sql,app.getApp_name(),app.getApp_id(),app.getApp_status(),app.getApp_page_uri(),app.getApp_icon_uri());
        }catch(Exception e){
            System.out.println("-------------- AppsDao add Exceptions -------------------");
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(int app_id) {
        try{
            String sql = "delete from apps where app_id=?";
            qr.update(sql,app_id);
        }catch(Exception e){
            System.out.println("---- AppsDao delete Exceptions ----");
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(App app) {
        try{
            String sql = "update apps set app_name=?,app_status=?,app_page_uri=?,app_icon_uri=? where app_id=?";
            qr.update(sql,app.getApp_name(),app.getApp_status(),app.getApp_page_uri(),app.getApp_icon_uri(),app.getApp_id());
        }catch (Exception e){
            System.out.println("---- AppsDao update Exceptions ----");
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<App> query() {
        try{
            String sql = "select * from apps";
            return qr.query(sql,new BeanListHandler<App>(App.class));
        }catch(Exception e){
            System.out.println("---- AppsDao query Exceptions ----");
            throw new RuntimeException(e);
        }
    }

    @Override
    public App findById(int app_id) {
        try{
            String sql = "select * from apps where app_id=?";
            return qr.query(sql,new BeanHandler<App>(App.class),app_id);
        } catch(Exception e){
            System.out.println("---- AppsDao findById Exceptions ----");
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<App> query(String keyword) {
        try{
            String sql = "select * from apps where app_name like ?";
            return qr.query(sql,new BeanListHandler<App>(App.class),"%"+keyword+"%");
        }catch(Exception e){
            System.out.println("---- AppsDao query Exceptions ----");
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<App> findByType(int type) {
        return null;
    }

    @Override
    public void getAll(PageBean<App> pb) {

    }

    @Override
    public int getTotalCount(PageBean<App> pb) {
        try{
            String sql = "select * from apps";
            Long count = qr.query(sql,new ScalarHandler<Long>());
            return count.intValue();
        }catch(Exception e){
            System.out.println("---- AppsDao getTotalCount Exceptions ----");
            throw new RuntimeException(e);
        }
    }
}
