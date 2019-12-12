package edu.lixin.weixin.Dao;

import edu.lixin.utils.PageBean;
import edu.lixin.weixin.model.Award;

import java.util.List;

public interface IAwardDao {
    void add(Award award);

    void delete(int award_id);

    void update(Award award);

    List<Award> query();

    Award findById(int award_id);

    List<Award>query(String keyword);

    int getTotalCount(PageBean<Award> pb);
}
