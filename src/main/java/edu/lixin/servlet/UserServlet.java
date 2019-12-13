package edu.lixin.servlet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import edu.lixin.factory.BeanFactory;
import edu.lixin.utils.ParameterUtils;
import edu.lixin.weixin.model.User;
import edu.lixin.weixin.service.IUserService;

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

@WebServlet(urlPatterns = "/weixin/user")
public class UserServlet extends HttpServlet {
    private static IUserService service = BeanFactory.getInstance("userService", IUserService.class);

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("application/json;charset=utf-8");
        ParameterUtils parameterUtils = new ParameterUtils(req);
        OutputStream outputStream = resp.getOutputStream();

        String method = parameterUtils.parseParameter("method");
        String user_school_id = parameterUtils.parseParameter("user_school_id");

        if(method == null){
            List<User> list = service.query();

            User user = service.findBySchoolId(Integer.parseInt(user_school_id));

            outputStream.write(JSON.toJSONBytes(list.get(0), SerializerFeature.WriteMapNullValue));
            outputStream.flush();
        }else{
            String user_charge = parameterUtils.parseParameter("user_charge");
            String user_balance = parameterUtils.parseParameter("user_balance");
            int user_id = Integer.parseInt(parameterUtils.parseParameter("user_id"));
            switch (method){
                case "updateCharge":
                    service.updateUserCharge(user_id,Float.parseFloat(user_charge));
                    break;
                case "updateBalance":
                    service.updateUserBalance(user_id,Float.parseFloat(user_balance));
                    break;
            }
        }
    }
}
