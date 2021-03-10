package pers.dreamer07.springAoon.servlet;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet 3.0 处理异步请求
 * @program: springAoon
 * @description:
 * @author: EMTKnight
 * @create: 2021-03-10
 **/
// value 指定映射的地址; asyncSupported 是否支持异步，默认为 false
@WebServlet(value = "/book", asyncSupported = true)
public class BookServlet extends HttpServlet {

    /**
     * Servlet 处理异步请求
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. 开启异步模式
        AsyncContext asyncContext = req.startAsync();
        // 2. 开启处理异步请求
        asyncContext.start(() -> {
            try {
                // 3. 开始业务逻辑
                Thread.sleep(3000);
                // 4. 告诉异步容器，逻辑逻辑即将处理完成
                asyncContext.complete();
                // 5. 响应
                ServletResponse response = asyncContext.getResponse();
                response.getWriter().write("阿巴巴");
            } catch (InterruptedException | IOException e) {
                e.printStackTrace();
            }
        });
    }
}
