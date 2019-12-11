package edu.lixin.weixin.Dao;

import edu.lixin.utils.PageBean;
import edu.lixin.weixin.model.Apply;

import java.util.List;

public interface IApplyDao {
    void add(Apply apply);

    void delete(int apply_id);

    void update(Apply apply);

    List<Apply> query();

    Apply findById(int apply_id);

    List<Apply>query(String keyword);

    void getAll(PageBean<Apply> pb);

    int getTotalCount(PageBean<Apply> pb);
}
