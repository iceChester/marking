package com.iwyu.marking.config;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import com.iwyu.marking.entity.Authority;
import com.iwyu.marking.entity.SysToken;
import com.iwyu.marking.entity.User;
//import com.iwyu.marking.service.ShiroService;
import com.iwyu.marking.service.AuthorityService;
import com.iwyu.marking.service.ShiroService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @ClassName AuthRealm
 * @Description 自定义Realm
 * @Author XiaoMao
 * @Date 2021/4/1 21:20
 * @Version 1.0
 **/

@Component
public class AuthRealm extends AuthorizingRealm {

    @Autowired
    private ShiroService shiroService;
    @Autowired
    private AuthorityService authorityService;

    /**
     * 授权(验证权限时候调用)
     *@param
     *@return org.apache.shiro.authz.AuthorizationInfo
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        //1. 从 PrincipalCollection 中来获取登录用户的信息
        User user = (User) principals.getPrimaryPrincipal();
        //2.添加角色和权限
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.addRole(user.getRole());
        List<Authority> authorities = authorityService.findByRole(user.getRole());
        for (Authority authority : authorities) {
            System.out.println(authority.getRole());
            simpleAuthorizationInfo.addStringPermission(authority.getName());
        }
        return simpleAuthorizationInfo;
    }

    @Override
    /**
     * 认证 判断token的有效性
     *@param  [token]
     *@return org.apache.shiro.authc.AuthenticationInfo
     */
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //获取token，既前端传入的token
        String accessToken = (String) token.getPrincipal();
        //1. 根据accessToken，查询用户信息
        SysToken tokenEntity = shiroService.findByToken(accessToken);
        //2. token失效
        //Date转LocalDateTime
        Date date = tokenEntity.getExpireTime();
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        LocalDateTime localDateTime = instant.atZone(zoneId).toLocalDateTime();
        if (tokenEntity == null || localDateTime.isBefore(LocalDateTime.now())) {
            throw new IncorrectCredentialsException("token失效，请重新登录");
        }
        //3. 调用数据库的方法, 从数据库中查询 username 对应的用户记录
        User user = shiroService.findByUserId(tokenEntity.getUserId());
        //4. 若用户不存在, 则可以抛出 UnknownAccountException 异常
        if (user == null) {
            throw new UnknownAccountException("用户不存在!");
        }
        //5. 根据用户的情况, 来构建 AuthenticationInfo 对象并返回. 通常使用的实现类为: SimpleAuthenticationInfo
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, accessToken, this.getName());
        return info;
    }

}