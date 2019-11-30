package edu.lixin.weixin.servlet;

import com.alibaba.fastjson.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;


@WebServlet(urlPatterns = "/weixin/getapps")
public class AppsServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("AppsServlet called");

        resp.setCharacterEncoding("utf-8");
        resp.setContentType("application/json;charset=utf-8");
        OutputStream outputStream = resp.getOutputStream();

        Map<String,String> param1 = new HashMap<String,String>();
        param1.put("UserName","username");
        param1.put("PassWd","password");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("data",param1);
        System.out.println(jsonObject.toString());
        System.out.println(jsonObject.toJSONString());
        outputStream.write(jsonObject.toString().getBytes());
        outputStream.write(jsonObject.toJSONString().getBytes());
        outputStream.flush();
    }
}
