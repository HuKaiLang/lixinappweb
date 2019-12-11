package edu.lixin.weixin.Dao;

import edu.lixin.utils.PageBean;
import edu.lixin.weixin.model.Notice;

import java.util.List;

public interface INoticeDao {
    void add(Notice notice);

    void delete(int notice_id);

    void update(Notice notice);

    List<Notice> query();

    List<Notice> query_private(int user_id);

    Notice findById(int notice_id);

    List<Notice>query(String keyword);

    int getTotalCount(PageBean<Notice> pb);
}
