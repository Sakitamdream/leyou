package com.leyou.cart.interceptor;

import com.leyou.auth.entity.UserInfo;
import com.leyou.auth.utils.JwtUtils;
import com.leyou.cart.config.JwtProperties;
import com.leyou.common.enums.ExceptionEnum;
import com.leyou.common.exception.LyException;
import com.leyou.common.utils.CookieUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@EnableConfigurationProperties(JwtProperties.class)
@Slf4j
public class UserInterceptor implements HandlerInterceptor {

    private JwtProperties prop;

    private static final ThreadLocal<UserInfo> threadLocal = new ThreadLocal<>();

    public UserInterceptor(){

    }

    public UserInterceptor(JwtProperties jwtProperties){
        this.prop = jwtProperties;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //获取cookie
        String token = CookieUtils.getCookieValue(request, prop.getCookieName());
        //解析token
        try{
            UserInfo userInfo = JwtUtils.getInfoFromToken(token, prop.getPublicKey());
            //将用户信息传递到controller
            threadLocal.set(userInfo);
            return true;
        }catch(Exception e){
            log.info("[购物车微服务] 解析用户身份失败",e);
            throw new LyException(ExceptionEnum.UNAUTHORIZED);
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        threadLocal.remove();
    }

    public static UserInfo getUser(){
        return threadLocal.get();
    }
}
