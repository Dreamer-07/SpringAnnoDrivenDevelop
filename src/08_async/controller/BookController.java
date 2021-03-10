package pers.dreamer07.springAoon.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.concurrent.Callable;

/**
 * @program: springAoon
 * @description:
 * @author: EMTKnight
 * @create: 2021-03-10
 **/
@Controller
@RequestMapping("/book")
public class BookController {

    /**
     * SpringMVC 处理异步请求方式 1 - 返回 Callable 接口的实现类，泛型为返回值类型
     * 原理：
     *      1. 当处理器方法的返回值类型是 **Callable** 时，SpringMVC 会将其交给 TaskExecutor 使用一个隔离的线程执行
     *      2. DispactherServlet 和所有的 Filter 退出 web 容器的线程，但是 response 保持打开状态
     *      3. Callable 返回的结果，SpringMVC 将请求重新派发给容器，恢复之前的处理
     *      4. 根据 Callable 返回值，SpringMVC 重新进行渲染和等流程(处理请求 -> 视图渲染)
     * @return
     */
    @ResponseBody
    @GetMapping("/get")
    public Callable<String> getBook(){
        return () -> {
            Thread.sleep(3000);
            return "阿巴阿巴";
        };
    }

}
