package edu.lixin.weixin.service.impl;

import edu.lixin.factory.BeanFactory;
import edu.lixin.utils.PageBean;
import edu.lixin.weixin.Dao.IPostersDao;
import edu.lixin.weixin.Dao.impl.PosterDao;
import edu.lixin.weixin.model.Poster;
import edu.lixin.weixin.service.IPostersService;

import java.util.List;

public class PostersService implements IPostersService {
    IPostersDao dao = BeanFactory.getInstance("postersDao", PosterDao.class);

    @Override
    public void add(Poster poster) {
        dao.add(poster);
    }

    @Override
    public void delete(int poster_id) {
        dao.delete(poster_id);
    }

    @Override
    public List<Poster> query() {
        return dao.query();
    }

    @Override
    public Poster findById(int poster_id) {
        return dao.findById(poster_id);
    }

    @Override
    public List<Poster> query(String keyword) {
        return dao.query(keyword);
    }

    @Override
    public int getTotalCount(PageBean<Poster> pb) {
        return dao.getTotalCount(pb);
    }
}
