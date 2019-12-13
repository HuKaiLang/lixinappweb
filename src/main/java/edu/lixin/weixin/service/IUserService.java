package edu.lixin.weixin.service;

import edu.lixin.utils.PageBean;
import edu.lixin.weixin.model.User;

import java.util.List;

public interface IUserService {
    void add(User user);

    void delete(int user_id);

    List<User> query();

    User findById(int user_id);

    List<User> query(String keyword);

    int getTotalCount(PageBean<User> pb);

    User userLogin(String user_name,String user_password);

    User findBySchoolId(int user_school_id);

    void updateUserBalance(int user_id, float user_balance);

    void updateUserCharge(int user_id, float user_charge);
}
