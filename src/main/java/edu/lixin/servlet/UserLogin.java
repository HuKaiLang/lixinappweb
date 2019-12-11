package edu.lixin.servlet;

import edu.lixin.factory.BeanFactory;
import edu.lixin.weixin.model.User;
import edu.lixin.weixin.service.IUserService;
import org.apache.commons.codec.digest.DigestUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = "/userLogin")
public class UserLogin extends HttpServlet {
    private static IUserService service = BeanFactory.getInstance("userService", IUserService.class);

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        HttpSession session = req.getSession();

        String user_name = req.getParameter("username");
        String user_password = req.getParameter("password");
        User user = service.userLogin(user_name, user_password);

        if (user != null) {
            session.setAttribute("user", user);
            req.getRequestDispatcher("admin.jsp").forward(req, resp);
        }else{
            req.setAttribute("message","账户名或密码错误");
            req.getRequestDispatcher("login.jsp").forward(req,resp);
        }
    }
}
