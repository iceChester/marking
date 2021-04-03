package com.iwyu.marking.config;


import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.iwyu.marking.filter.*;

import javax.servlet.Filter;

/**
 * @ClassName ShiroConfig
 * @Description spring整合shiro配置
 * @Author XiaoMao
 * @Date 2021/4/1 21:21
 * @Version 1.0
 **/

@Configuration
public class ShiroConfig {

    @Bean("securityManager")
    public SecurityManager securityManager(AuthRealm authRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(authRealm);
        securityManager.setRememberMeManager(null);
        return securityManager;
    }


    @Bean("shiroFilter")
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
        shiroFilter.setSecurityManager(securityManager);
        //自定义过滤（关键）
        Map<String, Filter> filters = new HashMap<>();
        filters.put("auth", new AuthFilter());
        shiroFilter.setFilters(filters);
        shiroFilter.setLoginUrl("/userLogin");
        Map<String, String> filterMap = new LinkedHashMap<>();
        //主要是这部分： 不要用这种方法，最好用注解的方法
//        filterMap.put("/add", "roles[admin]");
//        filterMap.put("/list", "roles[admin,user]");
//        filterMap.put("/delete", "perms[admin:delete]");
        filterMap.put("/**", "auth");
        shiroFilter.setFilterChainDefinitionMap(filterMap);

        return shiroFilter;
    }

    @Bean("lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }
}