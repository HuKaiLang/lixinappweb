package edu.lixin.weixin.Dao.impl;

import edu.lixin.utils.JdbcUtils;
import edu.lixin.utils.PageBean;
import edu.lixin.weixin.Dao.IUserDao;
import edu.lixin.weixin.model.User;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.util.List;

public class UserDao implements IUserDao {
    private QueryRunner qr = JdbcUtils.getQueryRunner();
    @Override
    public void add(User user) {
        String sql = "insert into user(user_id," +
                "user_name," +
                "user_gender," +
                "user_phone_number," +
                "user_email," +
                "user_identity," +
                "user_school_id," +
                "user_charge," +
                "user_account_state," +
                "user_avatar_path) values(?,?,?,?,?,?,?,?,?,?);";
        try{
            qr.update(sql,
                    user.getUser_id(),
                    user.getUser_name(),
                    user.getUser_gender(),
                    user.getUser_phone_number(),
                    user.getUser_email(),
                    user.getUser_identity(),
                    user.getUser_school_id(),
                    user.getUser_charge(),
                    user.getUser_account_state(),
                    user.getUser_avatar_path()
                    );
        }catch(Exception e){
            System.out.println("--- UserDao Exception ---");
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(int user_id) {
        String sql = "delete from user where user_id=?";
        try{
            qr.update(sql,user_id);
        }catch (Exception e){
            System.out.println("--- UserDao Exception ---");
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(User user) {
        String sql = "update user set " +
                "user_name=?," +
                "user_gender=?," +
                "user_phone_number=?," +
                "user_email=?," +
                "user_identity=?," +
                "user_school_id=?," +
                "user_charge=?," +
                "user_account_state=?," +
                "user_avatar_path=? where user_id=?";
        try{
            qr.update(sql,user.getUser_name(),
                    user.getUser_gender(),
                    user.getUser_phone_number(),
                    user.getUser_email(),
                    user.getUser_identity(),
                    user.getUser_school_id(),
                    user.getUser_charge(),
                    user.getUser_account_state(),
                    user.getUser_avatar_path(),
                    user.getUser_id());
        }catch (Exception e){
            System.out.println("--- UserDao Exception ---");
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<User> query() {
        try{
            String sql = "select * from user";
            return qr.query(sql,new BeanListHandler<User>(User.class));
        }catch(Exception e){
            System.out.println("--- UserDao Exceptions ---");
            throw new RuntimeException(e);
        }
    }

    @Override
    public User findById(int user_id) {
        return null;
    }

    @Override
    public List<User> query(String keyword) {
        return null;
    }

    @Override
    public int getTotalCount(PageBean<User> pb) {
        return 0;
    }
}
