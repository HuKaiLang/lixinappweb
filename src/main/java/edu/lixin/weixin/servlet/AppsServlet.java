package edu.lixin.weixin.servlet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import edu.lixin.factory.BeanFactory;
import edu.lixin.weixin.model.App;
import edu.lixin.weixin.service.IAppsService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@WebServlet(urlPatterns = "/weixin/getapps")
public class AppsServlet extends HttpServlet {

    private static IAppsService service = BeanFactory.getInstance("appsService",IAppsService.class);


    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("AppsServlet called");

        resp.setCharacterEncoding("utf-8");
        resp.setContentType("application/json;charset=utf-8");
        OutputStream outputStream = resp.getOutputStream();

        List<App> list = service.query();
        System.out.println(list.toString());

        outputStream.write(JSON.toJSONBytes(list, SerializerFeature.WriteMapNullValue));
        outputStream.flush();
    }
}
