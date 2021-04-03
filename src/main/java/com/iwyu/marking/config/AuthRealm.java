package com.iwyu.marking.config;

import java.util.ArrayList;
import java.util.List;

import com.iwyu.marking.entity.User;
import com.iwyu.marking.service.ShiroService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
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

    /**
     * 授权(验证权限时候调用)
     *@param
     *@return org.apache.shiro.authz.AuthorizationInfo
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        //1. 从 PrincipalCollection 中来获取登录用户的信息
        String userName = (String) principals.getPrimaryPrincipal();
        User user = shiroService.findByUsername(userName);
        //2.添加角色和权限
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        for (String role : user.getAuthority()) {
            //2.1添加角色
            simpleAuthorizationInfo.addRole(role.getRoleName());
            for (Permission permission : role.getPermissions()) {
                //2.1.1添加权限
                simpleAuthorizationInfo.addStringPermission(permission.getPermission());
            }
        }
        return simpleAuthorizationInfo;
    }

    /**
     * 认证(登陆时候调用)
     *@param  [token]
     *@return org.apache.shiro.authc.AuthenticationInfo
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        // 1. 获得username
        String username = (String)token.getPrincipal();
        // 2. 通过username查询用户
        User user = shiroService.findUserByUsername(username);
        // 3. 如果用户为空抛出用户不存在异常
        if (user == null) {
            throw new UnknownAccountException("用户名或密码错误");
        }

        // 4. 根据用户的情况, 来构建 AuthenticationInfo 对象并返回. 通常使用的实现类为: SimpleAuthenticationInfo 这里可以实现密码加密，这里为了测试方便便不加了
        //以下信息是从数据库中获取的.
        // 4.1 principal: 认证的实体信息. 可以是 username, 也可以是数据表对应的用户的实体类对象.
        // 4.2 credentials: 密码.
        // 4.3 realmName: 当前 realm 对象的 name. 调用父类的 getName() 方法即可
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(username, user.getPassword(), this.getName());

        return info;
    }
}