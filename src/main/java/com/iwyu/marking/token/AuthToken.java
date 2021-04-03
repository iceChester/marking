package com.iwyu.marking.token;
import org.apache.shiro.authc.UsernamePasswordToken;
/**
 * @ClassName AuthToken
 * @Description
 * @Author XiaoMao
 * @Date 2021/4/3 15:58
 * @Version 1.0
 **/


public class AuthToken extends UsernamePasswordToken {

    private String token;

    public AuthToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
