package com.sxbo.favoritesserver.cache;

import com.sxbo.favoritesserver.domain.UrlLibrary;
import com.sxbo.favoritesserver.repository.UrlLibraryRepository;
import com.sxbo.favoritesserver.utils.HtmlUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 一个简单的获取和存放网站logourl的缓存机制，
 * 避免多次操作同一个网站时，获取urllogo进行大量的操作
 *
 * 先从map中去找，map中找不到就去urlLibaryRepository数据库中找，数据库中找不到，就去url对应的网站中获取，并加入map和数据库中；
 * @Author xiaobo GG [https://github.com/sxbo]
 * @Date 2017/10/1917:01
 */

@Component
public class UrlCacheService {

    @Autowired
    UrlLibraryRepository urlLibraryRepository;

    private Map<String,String> map = new HashMap<>();


    public String getLogoInMap(String url){

        String logo = map.get(url);
        if (null == logo){ //map中不存在，从数据库中找
            UrlLibrary urlLibrary = urlLibraryRepository.findByUrl(url);
            if (urlLibrary !=null){
                logo = urlLibrary.getLogoUrl();
            }else {
                //使用HtmlUtil工具从网站获取
                logo = HtmlUtil.getImge(url);
                addLogoInMap(url,logo);
            }
        }
        return logo;
    }

    public void addLogoInMap(String url,String logoUrl){
        map.put(url,logoUrl);
        UrlLibrary urlLibrary = new UrlLibrary();
        urlLibrary.setUrl(url);
        urlLibrary.setLogoUrl(logoUrl);
        urlLibraryRepository.save(urlLibrary);
    }
}
