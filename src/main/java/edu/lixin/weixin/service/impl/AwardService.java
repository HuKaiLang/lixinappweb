package edu.lixin.weixin.service.impl;

import edu.lixin.factory.BeanFactory;
import edu.lixin.utils.PageBean;
import edu.lixin.weixin.Dao.IAwardDao;
import edu.lixin.weixin.Dao.impl.AwardDao;
import edu.lixin.weixin.model.Award;
import edu.lixin.weixin.service.IAwardService;

import java.util.List;

public class AwardService implements IAwardService {
    IAwardDao dao = BeanFactory.getInstance("awardDao", AwardDao.class);
    @Override
    public void add(Award award) {
        dao.add(award);
    }

    @Override
    public void delete(int award_id) {
        dao.delete(award_id);
    }

    @Override
    public void update(Award award) {
        dao.update(award);
    }

    @Override
    public List<Award> query() {
        return dao.query();
    }

    @Override
    public Award findById(int award_id) {
        return dao.findById(award_id);
    }

    @Override
    public List<Award> query(String keyword) {
        return dao.query(keyword);
    }

    @Override
    public int getTotalCount(PageBean<Award> pb) {
        return dao.getTotalCount(pb);
    }
}
