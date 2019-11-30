package edu.lixin.servlet;

import com.alibaba.fastjson.JSON;
import edu.lixin.model.testUser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;


@WebServlet(urlPatterns = "/webapp")
public class TestServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("TestServlet called");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("application/json;charset=utf-8");
        OutputStream out = resp.getOutputStream();

        testUser user = new testUser("张三","123","example@example.com");
        String testData = JSON.toJSONString(user);
        System.out.println(testData);
        out.write(testData.getBytes());
        out.flush();
    }
}
