package edu.lixin.weixin.servlet.Notice;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import edu.lixin.factory.BeanFactory;
import edu.lixin.weixin.model.Notice;
import edu.lixin.weixin.service.INoticeService;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.io.IOUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(urlPatterns = "/weixin/notices")
public class NoticeServlet extends HttpServlet {
    private static INoticeService service = BeanFactory.getInstance("noticeService", INoticeService.class);

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("application/json;charset=utf-8");
        req.setCharacterEncoding("utf-8");
        OutputStream outputStream = resp.getOutputStream();

        String notice_id;
        String method;
        String notice_title;
        String notice_text_content;
        String notice_img_path;
        String user_id;
        String notice_date;
        String notice_type;
        String notice_state;



        if(req.getMethod().equals("POST") ){
            System.out.println("POST 初始化");
            InputStream is = req.getInputStream();
            String contentStr = IOUtils.toString(is,"utf-8");
            JSONObject jsonObject = JSONObject.parseObject(contentStr);
            method = jsonObject.getString("method");
            notice_id = jsonObject.getString("notice_id");
            notice_title = jsonObject.getString("notice_title");
            notice_text_content = jsonObject.getString("notice_text_content");
            notice_img_path = jsonObject.getString("notice_img_path");
            user_id = jsonObject.getString("user_id");
            notice_type = jsonObject.getString("notice_type");
            notice_state = jsonObject.getString("notice_state");
            notice_date = jsonObject.getString("notice_date");
            if (notice_date==null){
                SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");
                Date date = new Date(System.currentTimeMillis());
                System.out.println(formatter.format(date));
                notice_date = formatter.format(date);
            }

        }else{
            method = req.getParameter("method");
            notice_id = req.getParameter("notice_id");
            notice_title = req.getParameter("notice_title");
            notice_text_content = req.getParameter("notice_text_content");
            notice_img_path = req.getParameter("notice_img_path");
            if(notice_img_path != null && !notice_img_path.equals("undefined")) {
                notice_img_path = notice_img_path.substring(11, notice_img_path.length());
            }
            user_id = req.getParameter("user_id");
            notice_type = req.getParameter("notice_type");
            notice_state = req.getParameter("notice_state");
            notice_date = req.getParameter("notice_date");
            if (notice_date==null){
                SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");
                Date date = new Date(System.currentTimeMillis());
                notice_date = formatter.format(date);
            }
        }

        Notice notice;

        switch (method){
            case "query":
                List<Notice> list = service.query();
                outputStream.write(JSON.toJSONBytes(list, SerializerFeature.WriteMapNullValue));
                outputStream.flush();
                break;
            case "update":
                notice = new Notice();
                try {
                    BeanUtils.setProperty(notice,"notice_id",notice_id);
                    BeanUtils.setProperty(notice, "notice_title", notice_title);
                    BeanUtils.setProperty(notice,"notice_text_content",notice_text_content);
                    BeanUtils.setProperty(notice,"notice_date",notice_date);
                    BeanUtils.setProperty(notice,"notice_type",notice_type);
                    BeanUtils.setProperty(notice,"notice_state",notice_state);
                    BeanUtils.setProperty(notice,"notice_img_path",notice_img_path);
                    BeanUtils.setProperty(notice,"user_id",user_id);

                    Map map = new HashMap();
                    map.put("code",200);

                    outputStream.flush();
                    service.update(notice);
                }catch (Exception e){
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }
                break;
            case "delete":
                try {
                    service.delete(Integer.parseInt(notice_id));
                }catch (Exception e){
                    throw new RuntimeException(e);
                }
                break;
            case "add":
                notice = new Notice();
                try {
                    BeanUtils.setProperty(notice,"notice_id",null);
                    BeanUtils.setProperty(notice, "notice_title", notice_title);
                    BeanUtils.setProperty(notice,"notice_text_content",notice_text_content);
                    BeanUtils.setProperty(notice,"notice_date",notice_date);
                    BeanUtils.setProperty(notice,"notice_type",notice_type);
                    BeanUtils.setProperty(notice,"notice_state",notice_state);
                    BeanUtils.setProperty(notice,"user_id",user_id);

                    service.add(notice);
                }catch (Exception e){
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }
                break;
        }


    }

}
