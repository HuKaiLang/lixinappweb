package edu.lixin.weixin.service;

import edu.lixin.utils.PageBean;
import edu.lixin.weixin.model.Poster;

import java.util.List;

public interface IPostersService {
    void add(Poster poster);

    void delete(int poster_id);

    List<Poster> query();

    Poster findById(int poster_id);

    List<Poster> query(String keyword);

    int getTotalCount(PageBean<Poster> pb);
}
