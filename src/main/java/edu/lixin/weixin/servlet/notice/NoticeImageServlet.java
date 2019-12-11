package edu.lixin.weixin.servlet.notice;

import Decoder.BASE64Encoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;

@WebServlet(urlPatterns = "/weixin/notice_img")
public class NoticeImageServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String agent = req.getHeader("User-Agent");
        String filename = req.getParameter("filename");
        String user_id = req.getParameter("user_id");
        String origin_filename = filename;

        System.out.println("filename = " + filename);


        if (agent.contains("MSIE")){
            filename = URLEncoder.encode(filename,"utf-8");
            filename = filename.replace("+"," ");
        }else  if (agent.contains("Firefox")){
            BASE64Encoder base64Encoder = new BASE64Encoder();
            filename = "=?utf-8?B?" + base64Encoder.encode(filename.getBytes("utf-8")) + "?=";
        }else {
            filename = URLEncoder.encode(filename,"utf-8");
        }
        resp.setHeader("Content-Disposition","attachment;filename="+filename);
        String aFile = getServletContext().getRealPath("upload/notice/" + user_id + "/"+ origin_filename);
        System.out.println(aFile + "requiring");
        FileInputStream fis = new FileInputStream(aFile);
        OutputStream out = resp.getOutputStream();
        int len = 0;
        byte [] bytes = new byte[1024];
        while ((len = fis.read(bytes)) != -1){
            out.write(bytes,0,len);
        }
        fis.close();
    }
}
