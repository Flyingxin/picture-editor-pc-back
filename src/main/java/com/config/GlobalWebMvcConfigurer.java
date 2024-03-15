package com.config;

import com.common.GlobalData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.filter.CorsFilter;

// 用于配置跨域资源共享（CORS）。
@Configuration
public class GlobalWebMvcConfigurer implements WebMvcConfigurer {
    @Override // 不配置，新的资源是不会被解析的，只能重启才能被解析
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String absoultePath = System.getProperty("user.dir") + GlobalData.RESOUCRCES_PATH;
        // 融合图片配置
        registry.addResourceHandler("/diffusionPicture/**")
                .addResourceLocations("file:" + absoultePath + GlobalData.DIFFUSION_PICTURE_DIRECTORY)
                .setCachePeriod(0);
        // 编辑图片配置
        registry.addResourceHandler("/editorPicture/**")
                .addResourceLocations("file:" + absoultePath + GlobalData.EDITOR_PICTURE_DIRECTORY)
                .setCachePeriod(0);
    }
    /**
     * 跨域
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // 允许跨域访问的路径
            .allowedOrigins("http://127.0.0.1:5173")  // 允许跨域访问的源
            .allowedMethods("GET", "HEAD", "POST", "PUT", "DELETE", "OPTIONS") // 允许请求方法
            .allowCredentials(true) // 是否发送Cookie
            .maxAge(3600) // 预检请求的有效期，单位为秒。
            .allowedHeaders("*"); // 允许头部设置
    }

    /**
     * @Bean 返回一个对象，这个对象应该被注册为 Spring 应用上下文中的一个 Bean。
     */
    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();// 允许跨越发送cookie
        config.addAllowedOrigin("http://127.0.0.1:5173");// 放行全部原始头信息
        config.setAllowCredentials(true);// 允许所有域名进行跨域调用
        config.addAllowedHeader("*");// 允许所有请求方法跨域调用
        config.addAllowedMethod("*");// 基于URL的跨域配置源
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
    /**
     * 定时任务
     * */
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
