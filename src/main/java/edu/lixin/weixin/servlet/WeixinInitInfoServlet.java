package edu.lixin.weixin.servlet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import edu.lixin.factory.BeanFactory;
import edu.lixin.weixin.model.Poster;
import edu.lixin.weixin.service.IPostersService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

@WebServlet(urlPatterns = "/test")
public class WeixinInitInfoServlet extends HttpServlet {
    private static IPostersService service = BeanFactory.getInstance("postersService",IPostersService.class);
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("WeixinInitServlet called");

        resp.setCharacterEncoding("utf-8");
        resp.setContentType("application/json;charset=utf-8");
        OutputStream outputStream = resp.getOutputStream();

        JSONArray jSONArray1 = new JSONArray();
        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("poster_file_name", "test1.jpg");
        jsonObject1.put("titles", "标题1");
        jSONArray1.add(jsonObject1);
        JSONObject jsonObject2 = new JSONObject();
        jsonObject2.put("poster_file_name", "test2.jpg");
        jsonObject2.put("titles", "标题2");
        jSONArray1.add(jsonObject2);
        System.out.println("jSONArray1:" + jSONArray1.toJSONString());

        List<Poster> list = service.query();

        System.out.println("json:"+JSON.toJSONString(list, SerializerFeature.WriteMapNullValue));

        outputStream.write(JSON.toJSONBytes(list, SerializerFeature.WriteMapNullValue));
        outputStream.flush();
    }
}
