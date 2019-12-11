package edu.lixin.weixin.service.impl;

import edu.lixin.factory.BeanFactory;
import edu.lixin.utils.PageBean;
import edu.lixin.weixin.Dao.INoticeDao;
import edu.lixin.weixin.Dao.impl.NoticeDao;
import edu.lixin.weixin.model.Notice;
import edu.lixin.weixin.service.INoticeService;

import java.util.List;

public class NoticeService implements INoticeService {
    INoticeDao dao = BeanFactory.getInstance("noticeDao", NoticeDao.class);
    @Override
    public void add(Notice notice) {
        dao.add(notice);
    }

    @Override
    public void delete(int notice_id) {
        dao.delete(notice_id);
    }

    @Override
    public List<Notice> query() {
        return dao.query();
    }

    @Override
    public Notice findById(int notice_id) {
        return dao.findById(notice_id);
    }

    @Override
    public List<Notice> query(String keyword) {
        return dao.query(keyword);
    }

    @Override
    public int getTotalCount(PageBean<Notice> pb) {
        return dao.getTotalCount(pb);
    }

    @Override
    public void update(Notice notice) {
        dao.update(notice);
    }

    @Override
    public List<Notice> query_private(int user_id) {
        return dao.query_private(user_id);
    }
}
