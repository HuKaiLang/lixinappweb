package edu.lixin.weixin.servlet.Article;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import edu.lixin.factory.BeanFactory;
import edu.lixin.utils.ParameterUtils;
import edu.lixin.weixin.model.Article;
import edu.lixin.weixin.service.IArticleService;
import org.apache.commons.beanutils.BeanUtils;

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

@WebServlet(urlPatterns = "/weixin/article")
public class ArticleServlet extends HttpServlet {
    private static IArticleService service = BeanFactory.getInstance("articleService", IArticleService.class);

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("application/json;charset=utf-8");
        req.setCharacterEncoding("utf-8");

        ParameterUtils parameterUtils = new ParameterUtils(req);
        OutputStream outputStream = resp.getOutputStream();

        String method = parameterUtils.parseParameter("method");
        String article_id = parameterUtils.parseParameter("article_id");
        String article_content = parameterUtils.parseParameter("article_content");
        String article_title = parameterUtils.parseParameter("article_title");
        String article_date = parameterUtils.parseParameter("article_date");
        String article_state = parameterUtils.parseParameter("article_state");
        String article_img_path = parameterUtils.parseParameter("article_img_path");
        String article_author = parameterUtils.parseParameter("article_author");

        Map parameterMap = req.getParameterMap();

        Article article;
        switch (method) {
            case "query":
            default:
                List<Article> list = service.query();
                outputStream.write(JSON.toJSONBytes(list, SerializerFeature.WriteMapNullValue));
                outputStream.flush();
                break;
            case "query_active":
                List<Article> list_active = service.query_active();
                outputStream.write(JSON.toJSONBytes(list_active, SerializerFeature.WriteMapNullValue));
                outputStream.flush();
                break;
            case "add":
                article = new Article();
                try {
//                    BeanUtils.setProperty(article, "article_id", article_id);
//                    BeanUtils.setProperty(article, "article_title", article_title);
//                    BeanUtils.setProperty(article, "article_content", article_content);
//                    BeanUtils.setProperty(article, "article_date", article_date);
//                    BeanUtils.setProperty(article, "article_state", article_state);
//                    BeanUtils.setProperty(article, "article_img_path", article_img_path);
//                    BeanUtils.setProperty(article, "article_author", article_author);
                    BeanUtils.populate(article,parameterMap);

                    service.add(article);
                    Map map = new HashMap();
                    map.put("code", 200);
                    outputStream.write(JSON.toJSONBytes(map, SerializerFeature.WriteMapNullValue));
                    outputStream.flush();

                } catch (Exception e) {
                    Map map = new HashMap();
                    map.put("code",406);
                    map.put("errorMsg",e.getMessage());

                    outputStream.write(JSON.toJSONBytes(map,SerializerFeature.WriteMapNullValue));
                    outputStream.flush();

                    e.printStackTrace();
                    throw new RuntimeException(e);
                }
                break;
            case "update":
                article = new Article();
                try {
//                    BeanUtils.setProperty(article, "article_id", article_id);
//                    BeanUtils.setProperty(article, "article_title", article_title);
//                    BeanUtils.setProperty(article, "article_content", article_content);
//                    BeanUtils.setProperty(article, "article_date", article_date);
//                    BeanUtils.setProperty(article, "article_state", article_state);
//                    BeanUtils.setProperty(article, "article_img_path", article_img_path);
//                    BeanUtils.setProperty(article, "article_author", article_author);

                    BeanUtils.populate(article,parameterMap);

                    service.update(article);
                    Map map = new HashMap();
                    map.put("code", 200);
                    outputStream.write(JSON.toJSONBytes(map, SerializerFeature.WriteMapNullValue));
                    outputStream.flush();

                } catch (Exception e) {
                    Map map = new HashMap();
                    map.put("code",406);
                    map.put("errorMsg",e.getMessage());

                    outputStream.write(JSON.toJSONBytes(map,SerializerFeature.WriteMapNullValue));
                    outputStream.flush();

                    e.printStackTrace();
                    throw new RuntimeException(e);
                }
                break;
            case "delete":
                try {
                    Map map = new HashMap();
                    map.put("code", 200);

                    service.delete(Integer.parseInt(article_id));
                    outputStream.write(JSON.toJSONBytes(map,SerializerFeature.WriteMapNullValue));
                    outputStream.flush();
                } catch (Exception e) {
                    Map map = new HashMap();
                    map.put("code",406);
                    map.put("errorMsg",e.getMessage());

                    outputStream.write(JSON.toJSONBytes(map,SerializerFeature.WriteMapNullValue));
                    outputStream.flush();

                    e.printStackTrace();
                    throw new RuntimeException(e);
                }
                break;
        }
    }
}
