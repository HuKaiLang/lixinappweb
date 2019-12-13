package edu.lixin.utils;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.IOUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class ParameterUtils {
    private HttpServletRequest req;
    private JSONObject jsonObject;
    Map<String, String> params = new HashMap<>();

    public ParameterUtils(HttpServletRequest req) throws IOException {
        this.req = req;

        if (req.getMethod().equals("POST")){
            InputStream is = req.getInputStream();
            String contentStr = IOUtils.toString(is,"utf-8");
            JSONObject jsonObject = JSONObject.parseObject(contentStr);
            this.jsonObject = jsonObject;

            Map<String, String[]> parameterMap = req.getParameterMap();
            if (parameterMap != null) {
                for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
                    this.params.put(entry.getKey(), entry.getValue()[0]);
                }
            }
        }
    }

    public String parseParameter(String key)  {
        String value;
        if (this.req.getMethod().equals("POST")){
            value = this.jsonObject.getString(key);
            return value;
        }else{
            value = this.req.getParameter(key);
            return value;
        }
    }

    public String parseFormParameter(String key){
        String value;
        if (this.req.getMethod().equals("POST")){
            value = params.get(key);
        }else{
            value = req.getParameter(key);
        }
        return value;
    }
}
