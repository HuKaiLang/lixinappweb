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

        List<Poster> list = service.query();

        System.out.println("json:"+JSON.toJSONString(list, SerializerFeature.WriteMapNullValue));
        outputStream.write(JSON.toJSONBytes(list, SerializerFeature.WriteMapNullValue));
        outputStream.flush();
    }
}
