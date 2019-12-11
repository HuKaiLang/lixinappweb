package edu.lixin.weixin.Dao.impl;

import edu.lixin.utils.JdbcUtils;
import edu.lixin.utils.PageBean;
import edu.lixin.weixin.Dao.IApplyDao;
import edu.lixin.weixin.model.Apply;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.util.List;

public class ApplyDao implements IApplyDao {
    private QueryRunner qr = JdbcUtils.getQueryRunner();
    @Override
    public void add(Apply apply) {
        String sql = "insert into apply(" +
                "apply_type," +
                "apply_name," +
                "user_school_id," +
                "apply_reason," +
                "apply_grade," +
                "apply_department," +
                "apply_state," +
                "apply_create_date) values(?,?,?,?,?,?,?,?);";
        try{
            qr.update(sql,apply.getApply_type(),
                    apply.getApply_name(),
                    apply.getUser_school_id(),
                    apply.getApply_reason(),
                    apply.getApply_grade(),
                    apply.getApply_department(),
                    apply.getApply_state(),
                    apply.getApply_create_date()
                    );
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(int apply_id) {
        String sql = "delete from apply where apply_id="+apply_id;
        try{
            qr.update(sql);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Apply apply) {
        String sql = "update apply set apply_type=?," +
                "apply_name=?," +
                "user_school_id=?," +
                "apply_reason=?," +
                "apply_grade=?," +
                "apply_department=?" +
                "apply_state" +
                "apply_create_date where apply_id="+apply.getApply_id();
        try{
            qr.update(sql,
                    apply.getApply_type(),
                    apply.getApply_name(),
                    apply.getUser_school_id(),
                    apply.getApply_reason(),
                    apply.getApply_grade(),
                    apply.getApply_department(),
                    apply.getApply_state(),
                    apply.getApply_create_date());
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Apply> query() {
        try{
            String sql = "select * from apply order by apply_create_date desc";
            return qr.query(sql,new BeanListHandler<Apply>(Apply.class));
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public Apply findById(int apply_id) {
        try{
            String sql = "select * from apply where apply_id=?";
            return qr.query(sql,new BeanHandler<Apply>(Apply.class),apply_id);
        } catch(Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Apply> query(String keyword) {
        try{
            String sql = "select * from apply where apply_name like ? order by notice_date desc";
            return qr.query(sql,new BeanListHandler<Apply>(Apply.class),"%"+keyword+"%");
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void getAll(PageBean<Apply> pb) {

    }

    @Override
    public int getTotalCount(PageBean<Apply> pb) {
        try{
            String sql = "select * from apply";
            Long count = qr.query(sql,new ScalarHandler<Long>());
            return count.intValue();
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }
}
