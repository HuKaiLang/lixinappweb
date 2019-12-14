package edu.lixin.servlet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import edu.lixin.factory.BeanFactory;
import edu.lixin.utils.ParameterUtils;
import edu.lixin.weixin.model.User;
import edu.lixin.weixin.service.IUserService;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

@WebServlet(urlPatterns = "/weixin/user")
public class UserServlet extends HttpServlet {
    private static IUserService service = BeanFactory.getInstance("userService", IUserService.class);

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("application/json;charset=utf-8");
        req.setCharacterEncoding("utf-8");
        ParameterUtils parameterUtils = new ParameterUtils(req);
        OutputStream outputStream = resp.getOutputStream();

        String method;
        String user_school_id;
        Map map = req.getParameterMap();


        if (req.getMethod().equals("POST")){
            method = parameterUtils.parseOriginParameter("method");
            user_school_id = parameterUtils.parseOriginParameter("user_school_id");
        }else{
            method = req.getParameter("method");
            user_school_id = req.getParameter("user_school_id");
        }

        if(method == null){
            List<User> list = service.query();

            User user = service.findBySchoolId(Integer.parseInt(user_school_id));

            outputStream.write(JSON.toJSONBytes(user, SerializerFeature.WriteMapNullValue));
            outputStream.flush();
        }else{
            String user_charge;
            String user_balance;
            int user_id;
            String user_gender;
            String user_phone_number;
            String user_email;
            String user_name;
            String user_identity;
            String user_account_state;
            String user_avatar_path;
            String user_md5_password;

            switch (method){
                case "query":
                    List<User> list = service.query();
                    outputStream.write(JSON.toJSONBytes(list, SerializerFeature.WriteMapNullValue));
                    outputStream.flush();
                    break;
                case "updateCharge":
                    user_id = Integer.parseInt(parameterUtils.parseFormParameter("user_id"));
                    user_charge = parameterUtils.parseFormParameter("user_charge");
                    service.updateUserCharge(user_id,Float.parseFloat(user_charge));
                    break;
                case "updateBalance":
                    user_id = Integer.parseInt(parameterUtils.parseFormParameter("user_id"));
                    user_balance = parameterUtils.parseFormParameter("user_balance");
                    service.updateUserBalance(user_id,Float.parseFloat(user_balance));
                    break;
                case "delete":
                    user_id = Integer.parseInt(parameterUtils.parseFormParameter("user_id"));
                    service.delete(user_id);
                    break;
                case "add":
                    User user = new User();
                    try {
                        BeanUtils.populate(user, map);
                        System.out.println(user.toString());
                        service.add(user);
                    }catch (Exception e){
                        e.printStackTrace();
                        throw new RuntimeException(e);
                    }
            }
        }
    }
}
