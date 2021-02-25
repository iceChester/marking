package com.iwyu.marking.config;/**
 * Created by Chester on 6/2/2021.
 */

import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName MybatisPlusConfig
 * @Description
 * @Author XiaoMao
 * @Date 6/2/2021 下午4:24
 * @Version 1.0
 **/
@Configuration
public class MybatisPlusConfig {

    	 /**
         * 分页插件
         * @return
         */
    @Bean
	public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
    //配置乐观锁
    @Bean
    public OptimisticLockerInterceptor optimisticLockerInterceptor(){
        return new OptimisticLockerInterceptor();
    }
}
