package edu.lixin.weixin.Dao;

import edu.lixin.utils.PageBean;
import edu.lixin.weixin.model.User;

import java.util.List;

public interface IUserDao {
    void add(User user);

    void delete(int user_id);

    void update(User user);

    List<User> query();

    User findById(int user_id);

    User userLogin(String user_name,String user_password);

    void updateUserBalance(int user_id,float user_balance);

    void updateUserCharge(int user_id,float user_charge);

    User findBySchoolId(int user_school_id);

    List<User>query(String keyword);

    int getTotalCount(PageBean<User> pb);
}
