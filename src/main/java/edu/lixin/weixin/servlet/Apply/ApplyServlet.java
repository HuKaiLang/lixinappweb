package edu.lixin.weixin.servlet.Apply;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import edu.lixin.factory.BeanFactory;
import edu.lixin.weixin.model.Apply;
import edu.lixin.weixin.service.IApplyService;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(urlPatterns = "/weixin/applies")
public class ApplyServlet extends HttpServlet {
    private static IApplyService service = BeanFactory.getInstance("applyService", IApplyService.class);

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("application/json;charset=utf-8");
        req.setCharacterEncoding("utf-8");

        OutputStream outputStream = resp.getOutputStream();

        String method;
        String apply_id;
        String apply_type;
        String apply_name;
        String user_school_id;
        String apply_reason;
        String apply_grade;
        String apply_department;
        String apply_state;
        String apply_create_date;

        if (req.getMethod().equals("POST")) {
            InputStream is = req.getInputStream();
            String contentStr = IOUtils.toString(is, "utf-8");
            JSONObject jsonObject = JSONObject.parseObject(contentStr);
            method = jsonObject.getString("method");
            apply_id = jsonObject.getString("apply_id");
            apply_type = jsonObject.getString("apply_type");
            apply_name = jsonObject.getString("apply_name");
            user_school_id = jsonObject.getString("user_school_id");
            apply_reason = jsonObject.getString("apply_reason");
            apply_grade = jsonObject.getString("apply_grade");
            apply_department = jsonObject.getString("apply_department");
            apply_state = jsonObject.getString("apply_state");
            apply_create_date = jsonObject.getString("apply_create_date");
        } else {
            method = req.getParameter("method");
            apply_id = req.getParameter("apply_id");
            apply_type = req.getParameter("apply_type");
            apply_name = req.getParameter("apply_name");
            user_school_id = req.getParameter("user_school_id");
            apply_reason = req.getParameter("apply_reason");
            apply_grade = req.getParameter("apply_grade");
            apply_department = req.getParameter("apply_department");
            apply_state = req.getParameter("apply_state");
            apply_create_date = req.getParameter("apply_create_date");
        }

        Apply apply;

        switch (method) {
            case "query":
                List<Apply> list = service.query();
                outputStream.write(JSON.toJSONBytes(list, SerializerFeature.WriteMapNullValue));
                outputStream.flush();
                break;
            case "add":
                apply = new Apply();
                try {
                    BeanUtils.setProperty(apply, "apply_id", apply_id);
                    BeanUtils.setProperty(apply, "apply_type", apply_type);
                    BeanUtils.setProperty(apply, "apply_name", apply_name);
                    BeanUtils.setProperty(apply, "user_school_id", user_school_id);
                    BeanUtils.setProperty(apply, "apply_reason", apply_reason);
                    BeanUtils.setProperty(apply, "apply_grade", apply_grade);
                    BeanUtils.setProperty(apply, "apply_department", apply_department);
                    BeanUtils.setProperty(apply, "apply_state", apply_state);
                    BeanUtils.setProperty(apply, "apply_create_date", apply_create_date);

                    Map map = new HashMap();
                    map.put("code",200);

                    service.add(apply);
                    outputStream.write(JSON.toJSONBytes(map,SerializerFeature.WriteMapNullValue));
                    outputStream.flush();
                } catch (Exception e) {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }
                break;
            case "update":
                apply = new Apply();
                try {
                    BeanUtils.setProperty(apply, "apply_id", apply_id);
                    BeanUtils.setProperty(apply, "apply_type", apply_type);
                    BeanUtils.setProperty(apply, "apply_name", apply_name);
                    BeanUtils.setProperty(apply, "user_school_id", user_school_id);
                    BeanUtils.setProperty(apply, "apply_reason", apply_reason);
                    BeanUtils.setProperty(apply, "apply_grade", apply_grade);
                    BeanUtils.setProperty(apply, "apply_department", apply_department);
                    BeanUtils.setProperty(apply, "apply_state", apply_state);
                    BeanUtils.setProperty(apply, "apply_create_date", apply_create_date);

                    Map map = new HashMap();
                    map.put("code", 200);

                    service.update(apply);
                    outputStream.write(JSON.toJSONBytes(map,SerializerFeature.WriteMapNullValue));
                    outputStream.flush();
                } catch (Exception e) {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }
                break;
            case "delete":
                try {
                    Map map = new HashMap();
                    map.put("code", 200);

                    service.delete(Integer.parseInt(apply_id));
                    outputStream.write(JSON.toJSONBytes(map,SerializerFeature.WriteMapNullValue));
                    outputStream.flush();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                break;
        }
    }
}
