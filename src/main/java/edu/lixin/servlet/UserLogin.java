package edu.lixin.servlet;

import edu.lixin.factory.BeanFactory;
import edu.lixin.weixin.model.User;
import edu.lixin.weixin.service.IUserService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(urlPatterns = "/userLogin")
public class UserLogin extends HttpServlet {
    private static IUserService service = BeanFactory.getInstance("userService", IUserService.class);

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("application/json;charset=utf-8");
        req.setCharacterEncoding("utf-8");

        HttpSession session = req.getSession();

        String user_name;
        String user_password;

        Map<String, String> params = new HashMap<>();

        if (req.getMethod().equals("POST")) {
            // POST方法接收表单数据
            Map<String, String[]> parameterMap = req.getParameterMap();
            if (parameterMap != null) {
                for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
                    params.put(entry.getKey(), entry.getValue()[0]);
                }
            }
            user_name = params.get("username");
            user_password = params.get("password");


        } else {
            // GET方法接收表单数据
            user_name = req.getParameter("username");
            user_password = req.getParameter("password");
        }

        if (user_name != null && user_password != null){
            User user = service.userLogin(user_name, user_password);

            if (user != null) {
                session.setAttribute("user", user);
                req.getRequestDispatcher("admin.jsp").forward(req, resp);
            } else {
                req.setAttribute("message", "账户名或密码错误");
                req.getRequestDispatcher("login.jsp").forward(req, resp);
            }
        }else {
            req.setAttribute("message","用户已过期，请重新登录");
            req.getRequestDispatcher("login.jsp").forward(req,resp);
        }

    }
}
