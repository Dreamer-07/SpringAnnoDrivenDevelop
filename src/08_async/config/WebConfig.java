package pers.dreamer07.springAoon.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Web 子容器配置类，实现 WebMvcConfigurer 接口定制 SpringMVC
 * @program: springAoon
 * @description:
 * @author: EMTKnight
 * @create: 2021-03-10
 **/
@Configuration
@EnableWebMvc // 开启 SpringMVC 高级模式
public class WebConfig implements WebMvcConfigurer  {



}
