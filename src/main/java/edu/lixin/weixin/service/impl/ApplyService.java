package edu.lixin.weixin.service.impl;

import edu.lixin.factory.BeanFactory;
import edu.lixin.utils.PageBean;
import edu.lixin.weixin.Dao.IApplyDao;
import edu.lixin.weixin.Dao.impl.ApplyDao;
import edu.lixin.weixin.model.Apply;
import edu.lixin.weixin.service.IApplyService;

import java.util.List;

public class ApplyService implements IApplyService {
    IApplyDao dao = BeanFactory.getInstance("applyDao", ApplyDao.class);
    @Override
    public void add(Apply apply) {
        dao.add(apply);
    }

    @Override
    public void update(Apply apply) {
        dao.update(apply);
    }

    @Override
    public void delete(int apply_id) {
        dao.delete(apply_id);
    }

    @Override
    public List<Apply> query() {
        return dao.query();
    }

    @Override
    public Apply findById(int apply_id) {
        return dao.findById(apply_id);
    }

    @Override
    public List<Apply> query(String keyword) {
        return dao.query(keyword);
    }

    @Override
    public int getTotalCount(PageBean<Apply> pb) {
        return dao.getTotalCount(pb);
    }
}
