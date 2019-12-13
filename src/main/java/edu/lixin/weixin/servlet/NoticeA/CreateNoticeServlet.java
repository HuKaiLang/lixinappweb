package edu.lixin.weixin.servlet.NoticeA;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import edu.lixin.factory.BeanFactory;
import edu.lixin.weixin.model.Notice;
import edu.lixin.weixin.service.INoticeService;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(urlPatterns = "/weixin/addnotices")
public class CreateNoticeServlet extends HttpServlet {
    private static INoticeService service = BeanFactory.getInstance("noticeService", INoticeService.class);

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("application/json;charset=utf-8");
        OutputStream outputStream = resp.getOutputStream();

        String notice_title = req.getParameter("notice_title");
        String notice_text_content = req.getParameter("notice_text_content");
        String user_id = req.getParameter("user_id");

        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(System.currentTimeMillis());
        System.out.println(formatter.format(date));
        String notice_date = formatter.format(date);

        String notice_type = req.getParameter("notice_type");
        String notice_state = "active";

        Notice notice = new Notice();
        try {
            BeanUtils.setProperty(notice,"notice_id",null);
            BeanUtils.setProperty(notice, "notice_title", notice_title);
            BeanUtils.setProperty(notice,"notice_text_content",notice_text_content);
            BeanUtils.setProperty(notice,"notice_date",notice_date);
            BeanUtils.setProperty(notice,"notice_type",notice_type);
            BeanUtils.setProperty(notice,"notice_state",notice_state);
            BeanUtils.setProperty(notice,"user_id",user_id);
            System.out.println(JSON.toJSONString(notice,SerializerFeature.WriteMapNullValue));

            service.add(notice);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
