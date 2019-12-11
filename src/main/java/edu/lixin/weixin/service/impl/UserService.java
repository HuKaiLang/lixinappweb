package edu.lixin.weixin.service.impl;

import edu.lixin.factory.BeanFactory;
import edu.lixin.utils.PageBean;
import edu.lixin.weixin.Dao.IUserDao;
import edu.lixin.weixin.Dao.impl.UserDao;
import edu.lixin.weixin.model.User;
import edu.lixin.weixin.service.IUserService;

import java.util.List;

public class UserService implements IUserService {
    IUserDao dao = BeanFactory.getInstance("userDao", UserDao.class);
    @Override
    public void add(User user) {
        dao.add(user);
    }

    @Override
    public void delete(int user_id) {
        dao.delete(user_id);
    }

    @Override
    public List<User> query() {
        return dao.query();
    }

    @Override
    public User findById(int user_id) {
        return dao.findById(user_id);
    }

    @Override
    public List<User> query(String keyword) {
        return dao.query(keyword);
    }

    @Override
    public int getTotalCount(PageBean<User> pb) {
        return dao.getTotalCount(pb);
    }

    @Override
    public User userLogin(String user_name, String user_password) {
        return dao.userLogin(user_name,user_password);
    }

    @Override
    public User findBySchoolId(int user_school_id) {
        return dao.findBySchoolId(user_school_id);
    }
}
