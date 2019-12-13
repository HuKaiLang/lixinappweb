package edu.lixin.weixin.servlet.Notices;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

@WebServlet(urlPatterns = "/weixin/uploadpic")
public class UploadPictureServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        req.setCharacterEncoding("utf-8");

        String user_id = "0";

        PrintWriter writer = resp.getWriter();

        ServletInputStream inputStream = req.getInputStream();

        DiskFileItemFactory factory = new DiskFileItemFactory();

        int sizeThreshold=1024*1024;
        factory.setSizeThreshold(sizeThreshold);

        File respository = new File(req.getSession().getServletContext().getRealPath("temp"));

        factory.setSizeThreshold(sizeThreshold);

        ServletFileUpload upload = new ServletFileUpload(factory);

        upload.setHeaderEncoding("utf-8");

        List<FileItem>parseRequest=null;
        try{
            parseRequest = upload.parseRequest(req);
        }catch (Exception e){
            e.printStackTrace();
        }

        for(FileItem fileItem:parseRequest){
            if(fileItem.isFormField()){
                System.out.println("Upload Picture Servlet获得表单数据");
                if(fileItem.getFieldName().equals("user_id")){
                    user_id = fileItem.getString();
                    System.out.println("User_id = " + user_id);
                }
            }else{
                String clientName = fileItem.getName();
                String filename = "";
                if (clientName.contains("\\")){
                    filename = clientName.substring(clientName.lastIndexOf("\\")).substring(1);
                }else{
                    filename = clientName;
                }

                String filepath = req.getSession().getServletContext().getRealPath("upload/weixin/notice");

                filepath += "/" ;
                filepath += user_id;

                File filepathfile = new File(filepath);
                if(!filepathfile.exists()){
                    filepathfile.mkdirs();
                }
                InputStream inputStream2 = fileItem.getInputStream();

                File file = new File(filepath + "/" + filename);
                BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
                byte [] buffer = new byte[10*1024];
                int len = 0;
                while ((len=inputStream2.read(buffer,0,10*1024))!=-1){
                    bos.write(buffer,0,len);
                }
                System.out.println("文件上传成功");
                bos.close();
                inputStream2.close();
            }
        }
    }
}
