package edu.lixin.weixin.Dao;

import edu.lixin.utils.PageBean;
import edu.lixin.weixin.model.Poster;

import java.util.List;

public interface IPostersDao {
    void add(Poster poster);

    void delete(int poster_id);

    void update(Poster poster);

    List<Poster> query();

    Poster findById(int poster_id);

    List<Poster>query(String keyword);

    int getTotalCount(PageBean<Poster> pb);
}
