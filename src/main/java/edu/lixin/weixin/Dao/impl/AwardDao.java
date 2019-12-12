package edu.lixin.weixin.Dao.impl;

import edu.lixin.utils.JdbcUtils;
import edu.lixin.utils.PageBean;
import edu.lixin.weixin.Dao.IAwardDao;
import edu.lixin.weixin.model.Award;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.util.List;

public class AwardDao implements IAwardDao {
    private QueryRunner qr = JdbcUtils.getQueryRunner();
    @Override
    public void add(Award award) {
        String sql = "insert into award(" +
                "user_school_id," +
                "award_type," +
                "award_request_reason," +
                "award_request_date," +
                "award_grade_point," +
                "award_request_deed," +
                "award_request_state) values(?,?,?,?,?,?,?);";
        try{
            qr.update(sql,
                    award.getUser_school_id(),
                    award.getAward_type(),
                    award.getAward_request_reason(),
                    award.getAward_request_date(),
                    award.getAward_grade_point(),
                    award.getAward_request_deed(),
                    award.getAward_request_state());
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(int award_id) {
        String sql = "delete from award where award_id="+award_id;
        try{
            qr.update(sql);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Award award) {
        String sql = "update award set user_school_id=?," +
                "award_type=?," +
                "award_request_reason=?," +
                "award_request_date=?," +
                "award_grade_point=?," +
                "award_request_deed=?," +
                "award_request_state=? where award_id="+award.getAward_id();
        try{
            qr.update(sql,
                    award.getUser_school_id(),
                    award.getAward_type(),
                    award.getAward_request_reason(),
                    award.getAward_request_date(),
                    award.getAward_grade_point(),
                    award.getAward_request_deed(),
                    award.getAward_request_state());
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Award> query() {
        try{
            String sql = "select * from award order by award_request_date desc";
            return qr.query(sql,new BeanListHandler<Award>(Award.class));
        }catch(Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public Award findById(int award_id) {
        try{
            String sql = "select * from award where award_id=?";
            return qr.query(sql,new BeanHandler<Award>(Award.class),award_id);
        } catch(Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Award> query(String keyword) {
        try{
            String sql = "select * from award where award_request_reason like ? order by award_request_date desc";
            return qr.query(sql,new BeanListHandler<Award>(Award.class),"%"+keyword+"%");
        }catch(Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public int getTotalCount(PageBean<Award> pb) {
        try{
            String sql = "select * from award";
            Long count = qr.query(sql,new ScalarHandler<Long>());
            return count.intValue();
        }catch(Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
