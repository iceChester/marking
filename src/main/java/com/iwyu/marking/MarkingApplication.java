package com.iwyu.marking;

import com.spring4all.swagger.EnableSwagger2Doc;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@EnableSwagger2Doc
@SpringBootApplication
@MapperScan("com.iwyu.marking.mapper")
public class MarkingApplication extends WebMvcConfigurationSupport {
/**
*@Description 定制URL访问规则
*@Author XiaoMao
*@Date 3/2/2021 上午9:31
*@Param [configurer]
*Return void
**/
    @Override
    protected void configurePathMatch(PathMatchConfigurer configurer) {
        configurer.setUseTrailingSlashMatch(true);
    }

    public static void main(String[] args) {
        SpringApplication.run(MarkingApplication.class, args);
    }
    @Bean
    public RequestContextListener requestContextListener(){
        return new RequestContextListener();
    }

}
