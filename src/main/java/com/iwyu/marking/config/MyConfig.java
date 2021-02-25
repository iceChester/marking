package com.iwyu.marking.config;/**
 * Created by Chester on 3/2/2021.
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/**
 * @ClassName MyConfig
 * @Description 配置静态资源
 * @Author XiaoMao
 * @Date 3/2/2021 上午10:08
 * @Version 1.0
 **/
@Configuration
@EnableWebMvc
public class MyConfig implements WebMvcConfigurer {

    //日志管理
    private Logger logger = LoggerFactory.getLogger(MyConfig.class);

    //解决前后端应用不同端口访问的跨域问题
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOriginPatterns("*")
                .allowedMethods("GET","HEAD","POST","PUT","DELETE")
                .allowCredentials(true)
                .maxAge(3600)
                .allowedHeaders("*");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        logger.info("配置静态资源");
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }
}
