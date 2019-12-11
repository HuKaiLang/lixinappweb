package edu.lixin.servlet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import edu.lixin.factory.BeanFactory;
import edu.lixin.weixin.model.User;
import edu.lixin.weixin.service.IUserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

@WebServlet(urlPatterns = "/weixin/getUserInfo")
public class UserServlet extends HttpServlet {
    private static IUserService service = BeanFactory.getInstance("userService", IUserService.class);

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("application/json;charset=utf-8");
        String user_school_id = req.getParameter("user_school_id");
        OutputStream outputStream = resp.getOutputStream();

        List<User> list = service.query();
        System.out.println(list.get(0).toString());

        User user = service.findBySchoolId(Integer.parseInt(user_school_id));
        System.out.println(JSON.toJSONString(user));

        outputStream.write(JSON.toJSONBytes(list.get(0), SerializerFeature.WriteMapNullValue));
        outputStream.flush();
    }
}
