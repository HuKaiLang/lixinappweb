package edu.lixin.weixin.servlet.Award;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import edu.lixin.factory.BeanFactory;
import edu.lixin.weixin.model.Award;
import edu.lixin.weixin.service.IAwardService;
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

@WebServlet(urlPatterns = "/weixin/awards")
public class AwardServlet extends HttpServlet {
    private static IAwardService service = BeanFactory.getInstance("awardService", IAwardService.class);

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("application/json;charset=utf-8");
        req.setCharacterEncoding("utf-8");

        OutputStream outputStream = resp.getOutputStream();

        String method;
        String award_id;
        String user_school_id;
        String award_type;
        String award_request_reason;
        String award_request_date;
        String award_grade_point;
        String award_request_deed;
        String award_request_state;

        if (req.getMethod().equals("POST")){
            InputStream is = req.getInputStream();
            String contentStr = IOUtils.toString(is, "utf-8");
            JSONObject jsonObject = JSONObject.parseObject(contentStr);
            method = jsonObject.getString("method");
            award_id = jsonObject.getString("award_id");
            user_school_id = jsonObject.getString("user_school_id");
            award_type = jsonObject.getString("award_type");
            award_request_reason = jsonObject.getString("award_request_reason");
            award_request_date = jsonObject.getString("award_request_date");
            award_grade_point = jsonObject.getString("award_grade_point");
            award_request_deed = jsonObject.getString("award_request_deed");
            award_request_state = jsonObject.getString("award_request_state");
        }else{
            method = req.getParameter("method");
            award_id = req.getParameter("award_id");
            user_school_id = req.getParameter("user_school_id");
            award_type = req.getParameter("award_type");
            award_request_reason = req.getParameter("award_request_reason");
            award_request_date = req.getParameter("award_request_date");
            award_grade_point = req.getParameter("award_grade_point");
            award_request_deed = req.getParameter("award_request_deed");
            award_request_state = req.getParameter("award_request_state");
        }

        if(award_request_date == null){
            SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date(System.currentTimeMillis());
            award_request_date = formatter.format(date);
        }
        if(award_request_state == null){
            award_request_state = "review";
        }

        Award award;

        switch (method){
            case "add":
                award = new Award();
                try{
                    BeanUtils.setProperty(award, "award_id", award_id);
                    BeanUtils.setProperty(award,"user_school_id",user_school_id);
                    BeanUtils.setProperty(award,"award_type",award_type);
                    BeanUtils.setProperty(award,"award_request_reason",award_request_reason);
                    BeanUtils.setProperty(award,"award_request_date",award_request_date);
                    BeanUtils.setProperty(award,"award_grade_point",award_grade_point);
                    BeanUtils.setProperty(award,"award_request_deed",award_request_deed);
                    BeanUtils.setProperty(award,"award_request_state",award_request_state);

                    Map map = new HashMap();
                    map.put("code",200);

                    service.add(award);
                    outputStream.write(JSON.toJSONBytes(map, SerializerFeature.WriteMapNullValue));
                    outputStream.flush();
                }catch (Exception e){
                    Map map = new HashMap();
                    map.put("code",406);
                    map.put("errorMsg",e.getMessage());

                    outputStream.write(JSON.toJSONBytes(map,SerializerFeature.WriteMapNullValue));
                    outputStream.flush();

                    e.printStackTrace();
                    throw new RuntimeException(e);
                }
                break;
            case "update":
                award = new Award();
                try{
                    BeanUtils.setProperty(award, "award_id", award_id);
                    BeanUtils.setProperty(award,"user_school_id",user_school_id);
                    BeanUtils.setProperty(award,"award_type",award_type);
                    BeanUtils.setProperty(award,"award_request_reason",award_request_reason);
                    BeanUtils.setProperty(award,"award_request_date",award_request_date);
                    BeanUtils.setProperty(award,"award_grade_point",award_grade_point);
                    BeanUtils.setProperty(award,"award_request_deed",award_request_deed);
                    BeanUtils.setProperty(award,"award_request_state",award_request_state);

                    Map map = new HashMap();
                    map.put("code",200);

                    service.update(award);
                    outputStream.write(JSON.toJSONBytes(map, SerializerFeature.WriteMapNullValue));
                    outputStream.flush();
                }catch (Exception e){
                    Map map = new HashMap();
                    map.put("code",406);
                    map.put("errorMsg",e.getMessage());

                    outputStream.write(JSON.toJSONBytes(map,SerializerFeature.WriteMapNullValue));
                    outputStream.flush();

                    e.printStackTrace();
                    throw new RuntimeException(e);
                }
                break;
            case "query":
                List<Award> list = service.query();
                outputStream.write(JSON.toJSONBytes(list, SerializerFeature.WriteMapNullValue));
                outputStream.flush();
                break;
            case "delete":
                try {
                    Map map = new HashMap();
                    map.put("code", 200);

                    service.delete(Integer.parseInt(award_id));
                    outputStream.write(JSON.toJSONBytes(map,SerializerFeature.WriteMapNullValue));
                    outputStream.flush();
                } catch (Exception e) {
                    Map map = new HashMap();
                    map.put("code",406);
                    map.put("errorMsg",e.getMessage());

                    outputStream.write(JSON.toJSONBytes(map,SerializerFeature.WriteMapNullValue));
                    outputStream.flush();

                    e.printStackTrace();
                    throw new RuntimeException(e);
                }
                break;
        }
    }
}
