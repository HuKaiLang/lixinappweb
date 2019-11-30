package edu.lixin.weixin.service;

import edu.lixin.weixin.model.App;

import java.util.List;

public interface IAppsService {
    void add(App app);

    void delete(int app_id);

    List<App> query();

    App findById(int app_id);

    List<App> query(String keyword);
}
