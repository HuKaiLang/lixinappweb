package edu.lixin.weixin.servlet.Apply;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import edu.lixin.factory.BeanFactory;
import edu.lixin.weixin.model.Apply;
import edu.lixin.weixin.service.IApplyService;
import org.apache.commons.io.IOUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

@WebServlet(urlPatterns = "/weixin/applies")
public class ApplyServlet extends HttpServlet {
    private static IApplyService service = BeanFactory.getInstance("applyService", IApplyService.class);

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("application/json;charset=utf-8");
        req.setCharacterEncoding("utf-8");

        OutputStream outputStream = resp.getOutputStream();

        String method;

        if (req.getMethod().equals("POST") ){
            InputStream is = req.getInputStream();
            String contentStr = IOUtils.toString(is,"utf-8");
            JSONObject jsonObject = JSONObject.parseObject(contentStr);
            method = jsonObject.getString("method");
        }else{
            method = req.getParameter("method");
        }

        switch (method){
            case "query":
                List<Apply> list = service.query();
                outputStream.write(JSON.toJSONBytes(list, SerializerFeature.WriteMapNullValue));
                outputStream.flush();
                break;
            case "update":
                System.out.println("调用update");
                break;
        }
    }
}
