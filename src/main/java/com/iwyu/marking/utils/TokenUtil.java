package com.iwyu.marking.utils;

/**
 * @ClassName TokenUtil
 * @Description
 * @Author XiaoMao
 * @Date 2021/4/3 15:55
 * @Version 1.0
 **/

import org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * token工具类
 */
public class TokenUtil {
    /**
     * 获取请求的token
     */
    public static String getRequestToken(HttpServletRequest httpRequest) {

        //从header中获取token
        String token = httpRequest.getHeader("token");
        //如果header中不存在token，则从参数中获取token
        if (StringUtils.isBlank(token)) {
            token = httpRequest.getParameter("token");
        }
        return token;
    }
}
