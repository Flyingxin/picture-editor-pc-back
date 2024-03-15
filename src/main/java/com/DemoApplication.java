package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.autoconfigure.SpringBootApplication;
// 过滤器（Filter）是一种可以拦截请求和响应，以实现各种处理（如日志记录、压缩、加密等）的组件
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
//import org.mybatis.spring.annotation.MapperScan;

import com.jwt.JwtAuthenticationFilter;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.RequestMapping;

// 入口
@SpringBootApplication // 主程序类 ,是组合注解
@EnableScheduling // 定时任务
public class DemoApplication extends SpringBootServletInitializer{
    // 启动
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    /**
     * configure 方法：这个方法重写了 SpringBootServletInitializer 的 configure 方法，
     * 它返回一个 SpringApplicationBuilder 对象，用于构建和启动 Spring 应用。
     * */
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(DemoApplication.class);
    }
    /**
     * jwtFilter 方法：这个方法定义了一个 Bean，是一个 FilterRegistrationBean 对象，
     *
     * 设置想要的 Filter 和 URL 映射模式。就可以在 Spring Boot 应用中使用 Servlet Filter 了。
     * 这个类通常用于自定义 Filter，例如，你可以使用它来创建一个用于身份验证或日志记录的 Filter。
     *
     * Servlet Filter 是 Java Servlet 规范的一部分，
     * 是一种在请求被 servlet 或 JSP 页面处理之前或之后执行的代码。
     * Filter 可以改变请求和响应，或者根据某些条件阻止请求被进一步处理。
     * 如：
     * 图像转换：在将图像发送到用户之前，改变图像的格式。
     * 压缩：减小服务器响应的大小，以减少网络带宽的使用。
     * 身份验证和授权：阻止未经授权的请求访问受保护的资源。
     * XSRF/CSRF 防护：防止跨站请求伪造攻击。
     * */
    @Bean
    public FilterRegistrationBean jwtFilter() {
        final FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        JwtAuthenticationFilter filter = new JwtAuthenticationFilter();
        registrationBean.setFilter(filter);
        return registrationBean;
    }

}
