package edu.lixin.weixin.Dao.impl;

import edu.lixin.utils.JdbcUtils;
import edu.lixin.utils.PageBean;
import edu.lixin.weixin.Dao.IUserDao;
import edu.lixin.weixin.model.User;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

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
                "user_avatar_path," +
                "user_md5_password," +
                "user_balance) values(?,?,?,?,?,?,?,?,?,?,?,?);";
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
                    user.getUser_avatar_path(),
                    user.getUser_md5_password(),
                    user.getUser_balance()
                    );
        }catch(Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(int user_id) {
        String sql = "delete from user where user_id=?";
        try{
            qr.update(sql,user_id);
        }catch (Exception e){
            e.printStackTrace();
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
                "user_avatar_path=?," +
                "user_md5_password=?" +
                "user_balance=? where user_id=?";
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
                    user.getUser_md5_password(),
                    user.getUser_balance(),
                    user.getUser_id());
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<User> query() {
        try{
            String sql = "select * from user";
            return qr.query(sql,new BeanListHandler<User>(User.class));
        }catch(Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public User findById(int user_id) {
        String sql = "select * from user where user_id=?";
        try{
            return qr.query(sql,new BeanHandler<User>(User.class),user_id);
        }catch(Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<User> query(String keyword) {
        try{
            String sql = "select * from user where user_name like ?";
            return qr.query(sql,new BeanListHandler<User>(User.class),"%"+keyword+"%");
        }catch(Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateUserBalance(int user_id, float user_balance) {
        String sql = "update user set user_balance = ? where user_id = ?";
        try{
            qr.update(sql,user_balance,user_id);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateUserCharge(int user_id, float user_charge) {
        String sql = "update user set user_charge = ? where user_id = ?";
        try{
            qr.update(sql,user_charge,user_id);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public int getTotalCount(PageBean<User> pb) {
        try{
            String sql = "select * from user";
            Long count = qr.query(sql,new ScalarHandler<Long>());
            return count.intValue();
        }catch(Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public User userLogin(String user_name, String user_password) {
        String sql = "select * from user where user_name=? and user_md5_password=?";
        String user_md5_password = DigestUtils.md5Hex(user_password);
        try{
            return qr.query(sql,new BeanHandler<User>(User.class),user_name,user_md5_password);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public User findBySchoolId(int user_school_id) {
        String sql = "select * from user where user_school_id=?";
        try{
            return qr.query(sql,new BeanHandler<User>(User.class),user_school_id);
        }catch(Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
