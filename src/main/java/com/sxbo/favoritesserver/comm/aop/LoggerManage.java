package com.sxbo.favoritesserver.comm.aop;

import java.lang.annotation.*;

/**
 * 日志注解
 * @Author xiaobo GG [https://github.com/sxbo]
 * @Date 2017/9/611:16
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface  LoggerManage {
    public String description();
}
