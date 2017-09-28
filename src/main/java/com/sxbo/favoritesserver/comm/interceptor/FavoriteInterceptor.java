package com.sxbo.favoritesserver.comm.interceptor;

import com.sxbo.favoritesserver.comm.R;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author xiaobo GG [https://github.com/sxbo]
 * @Date 2017/9/219:10
 */
@Component
public class FavoriteInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.err.println(request.getRequestURI());//添加的RequestMapping 例如"/user/login"
        System.err.println(request.getRequestURL());//整个请求路径带ip地址
        System.err.println(request.getSession().getAttribute(R.LOGIN_SESSION_KEY));
        //response.setStatus(401);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
