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

    List<User>query(String keyword);

    int getTotalCount(PageBean<User> pb);
}
