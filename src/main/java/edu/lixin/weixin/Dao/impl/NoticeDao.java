package edu.lixin.weixin.Dao.impl;

import edu.lixin.utils.JdbcUtils;
import edu.lixin.utils.PageBean;
import edu.lixin.weixin.Dao.INoticeDao;
import edu.lixin.weixin.model.Notice;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.util.List;

public class NoticeDao implements INoticeDao {
    private QueryRunner qr = JdbcUtils.getQueryRunner();

    @Override
    public void add(Notice notice) {
        String sql = "insert into notice(" +
                "notice_title," +
                "notice_text_content," +
                "notice_img_path," +
                "notice_type," +
                "notice_date," +
                "notice_state," +
                "user_id) values(?,?,?,?,?,?,?);";
        try{
            qr.update(sql,
                    notice.getNotice_title(),
                    notice.getNotice_text_content(),
                    notice.getNotice_img_path(),
                    notice.getNotice_type(),
                    notice.getNotice_date(),
                    notice.getNotice_state(),
                    notice.getUser_id());
        }catch (Exception e){
            System.out.println("--- NoticeDao Exception ---");
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(int notice_id) {
        String sql = "delete from notice where notice_id="+notice_id;
        try{
            qr.update(sql);
        }catch (Exception e){
            System.out.println("--- NoticeDao Exception ---");
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Notice notice) {
        String sql = "update notice set notice_title=?," +
                "notice_text_content=?," +
                "notice_img_path=?," +
                "notice_type=?," +
                "notice_date=?," +
                "notice_state=?," +
                "user_id=? where notice_id="+notice.getNotice_id();
        try{
            qr.update(sql,notice.getNotice_title(),
                    notice.getNotice_text_content(),
                    notice.getNotice_img_path(),
                    notice.getNotice_type(),
                    notice.getNotice_date(),
                    notice.getNotice_state(),
                    notice.getUser_id());
        }catch(Exception e){
            System.out.println("--- NoticeDao Exception ---");
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Notice> query() {
        try{
            String sql = "select * from notice order by notice_date desc";
            return qr.query(sql,new BeanListHandler<Notice>(Notice.class));
        }catch(Exception e){
            System.out.println("--- NoticeDao Exception ---");
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Notice> query_private(int user_id) {
        try{
            String sql = "select * from notice where user_id=? order by notice_date desc";
            return qr.query(sql,new BeanListHandler<Notice>(Notice.class),user_id);
        }catch(Exception e){
            System.out.println("--- NoticeDao Exception ---");
            throw new RuntimeException(e);
        }
    }

    @Override
    public Notice findById(int notice_id) {
        try{
            String sql = "select * from posters where poster_id=?";
            return qr.query(sql,new BeanHandler<Notice>(Notice.class),notice_id);
        } catch(Exception e){
            System.out.println("--- NoticeDao Exception ---");
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Notice> query(String keyword) {
        try{
            String sql = "select * from notice where notice_title like ? order by notice_date desc";
            return qr.query(sql,new BeanListHandler<Notice>(Notice.class),"%"+keyword+"%");
        }catch(Exception e){
            System.out.println("--- NoticeDao Exception ---");
            throw new RuntimeException(e);
        }
    }

    @Override
    public int getTotalCount(PageBean<Notice> pb) {
        try{
            String sql = "select * from notice";
            Long count = qr.query(sql,new ScalarHandler<Long>());
            return count.intValue();
        }catch(Exception e){
            System.out.println("--- NoticeDao Exception ---");
            throw new RuntimeException(e);
        }
    }
}
