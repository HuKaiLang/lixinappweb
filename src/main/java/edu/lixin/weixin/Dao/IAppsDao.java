package edu.lixin.weixin.Dao;

import edu.lixin.utils.PageBean;
import edu.lixin.weixin.model.App;

import java.util.List;

public interface IAppsDao {
    void add(App app);

    void delete(int app_id);

    void update(App app);

    List<App>query();

    App findById(int app_id);

    List<App>query(String keyword);

    List<App>findByType(int type);

    void getAll(PageBean<App> pb);

    int getTotalCount(PageBean<App> pb);
}
