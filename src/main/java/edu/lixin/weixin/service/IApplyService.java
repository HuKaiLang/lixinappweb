package edu.lixin.weixin.service;

import edu.lixin.utils.PageBean;
import edu.lixin.weixin.model.Apply;

import java.util.List;

public interface IApplyService {
    void add(Apply apply);

    void update(Apply apply);

    void delete(int apply_id);

    List<Apply> query();


    Apply findById(int apply_id);

    List<Apply> query(String keyword);

    int getTotalCount(PageBean<Apply> pb);
}
