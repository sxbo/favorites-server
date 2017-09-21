package com.sxbo.favoritesserver.controller;

import com.sxbo.favoritesserver.comm.R;
import com.sxbo.favoritesserver.domain.User;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @Author xiaobo GG [https://github.com/sxbo]
 * @Date 2017/9/1916:57
 */

@Controller
public class BaseCotroller {
    protected Logger logger = Logger.getLogger(this.getClass());

    protected HttpServletRequest getRequest() {
        return ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
    }

    protected HttpSession getSession(){
        return getRequest().getSession();
    }

    protected User getUser() {
        return (User) getSession().getAttribute(R.LOGIN_SESSION_KEY);
    }

    protected  Long getUserId(){
        Long id = null;
        User user = getUser();
        if (user!=null){
            id = user.getId();
        }
        return id;
    }

    protected String getUserName(){
        String userName = null;
        User user = getUser();
        if (user!=null){
            userName = user.getUserName();
        }
        return userName;
    }


}
