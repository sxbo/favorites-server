package com.sxbo.favoritesserver.comm.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @Author xiaobo GG [https://github.com/sxbo]
 * @Date 2017/9/219:23
 */
@Configuration
public class FavInterceptorConfig extends WebMvcConfigurerAdapter {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new FavoriteInterceptor()).addPathPatterns("/**");
    }
}
