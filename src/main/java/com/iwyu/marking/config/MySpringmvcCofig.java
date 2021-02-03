package com.iwyu.marking.config;/**
 * Created by Chester on 3/2/2021.
 */

import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.charset.Charset;
import java.util.List;

/**
 * @ClassName MySpringmvcCofig
 * @Description 配置消息转换器
 * @Author XiaoMao
 * @Date 3/2/2021 上午10:01
 * @Version 1.0
 **/
@Configuration
@EnableWebMvc
public class MySpringmvcCofig implements WebMvcConfigurer {
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        StringHttpMessageConverter stringHttpMessageConverter = new StringHttpMessageConverter(Charset.forName("UTF-8"));
        converters.add(stringHttpMessageConverter);
    }
}
