package edu.lixin.servlet;

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

@WebServlet(urlPatterns = "/weixin/swiper/posterDownload")
public class SwiperPosterDownloads extends HttpServlet  {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Download function ----------------");

        String agent = req.getHeader("User-Agent");
        String filename = req.getParameter("filename");
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
        String aFile = getServletContext().getRealPath("files/weixin/poster/" + origin_filename);
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
