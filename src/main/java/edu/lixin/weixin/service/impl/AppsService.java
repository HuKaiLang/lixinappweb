package edu.lixin.weixin.service.impl;

import edu.lixin.factory.BeanFactory;
import edu.lixin.weixin.Dao.IAppsDao;
import edu.lixin.weixin.Dao.impl.AppsDao;
import edu.lixin.weixin.model.App;
import edu.lixin.weixin.service.IAppsService;

import java.util.List;

public class AppsService implements IAppsService {
    IAppsDao dao = BeanFactory.getInstance("appsDao", AppsDao.class);

    @Override
    public void add(App app) {
        dao.add(app);
    }

    @Override
    public void delete(int app_id) {
        dao.delete(app_id);
    }

    @Override
    public List<App> query() {
        return dao.query();
    }

    @Override
    public App findById(int app_id) {
        return dao.findById(app_id);
    }

    @Override
    public List<App> query(String keyword) {
        return dao.query(keyword);
    }
}
