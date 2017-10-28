package com.sxbo.favoritesserver.controller;

import com.sxbo.favoritesserver.cache.UrlCacheService;
import com.sxbo.favoritesserver.comm.R;
import com.sxbo.favoritesserver.comm.aop.LoggerManage;
import com.sxbo.favoritesserver.domain.Collect;
import com.sxbo.favoritesserver.domain.Favorite;
import com.sxbo.favoritesserver.domain.result.Result;
import com.sxbo.favoritesserver.domain.result.ResultMsg;
import com.sxbo.favoritesserver.domain.view.CollectViewSummary;
import com.sxbo.favoritesserver.repository.CollectRepository;
import com.sxbo.favoritesserver.repository.FavoriteRepository;
import com.sxbo.favoritesserver.service.CollectService;
import com.sxbo.favoritesserver.service.FavoriteService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author xiaobo GG [https://github.com/sxbo]
 * @Date 2017/9/289:08
 */
@RestController
@RequestMapping("/collect")
public class CollectController extends BaseCotroller{

    @Autowired
    private CollectRepository collectRepository;

    @Autowired
    private CollectService collectService;
    @Autowired
    private FavoriteService favoriteService;
    @Autowired
    private FavoriteRepository favoriteRepository;
    @Autowired
    private UrlCacheService urlCacheService;


    @RequestMapping(value = "/collect",method = RequestMethod.POST)
    @LoggerManage(description = "文章收集")
    public Result collect(@RequestBody Collect collect){

        try{
            if (StringUtils.isBlank(collect.getLogoUrl())){//设置logo图片
                collect.setLogoUrl(R.default_logo);
            }
            if(getUserId() == null)
                return new Result(HttpStatus.UNAUTHORIZED);
            collect.setUserId(getUserId());

            if (collectService.isCollected(getUserId(),collect)){ //该用户是否已经收藏该网站
                return new Result(ResultMsg.CollectExist);
            }

            if (StringUtils.isNotBlank(collect.getNewFavorite())){ //要重新创建一个Favorite
                if (favoriteService.favoriteIsExsited(collect.getUserId(),collect.getNewFavorite())){ //已经存在
                    return new Result(ResultMsg.FavoritesNameUsed);
                }
                Favorite favorite = favoriteService.saveFavorite(collect.getUserId(),0l,collect.getNewFavorite());
                collect.setFavorId(favorite.getId());
                collectService.saveCollect(collect);
                return new Result(ResultMsg.SUCCESS);
            }

            //具备创建条件,无需重新创建收藏夹
            if (null ==collect.getFavorId()){ //favorId是否为空
                return new Result(ResultMsg.FavoritesNameIsNull);
            }
            collectService.saveCollect(collect);
            return new Result(ResultMsg.SUCCESS);

        }catch (Exception e){
            logger.error("收集失败！",e);
            return new Result(ResultMsg.FAILED);
        }
    }

    /**
     * 根据某网站的url访问地址，返回该网站的logoUrl
     * @param url
     * @return
     */
    @PostMapping(value = "/getCollectLogoUrl")
    @LoggerManage(description = "获取收藏页面的Logourl")
    public Result getCollectLogoUrl(String url){
        if (StringUtils.isNotBlank(url)){
            try {

                String logoUrl = urlCacheService.getLogoInMap(url);
                if (StringUtils.isNotBlank(logoUrl)){
                    return new Result(ResultMsg.SUCCESS,logoUrl);
                }else {
                    return new Result(ResultMsg.SUCCESS,R.default_logo);
                }
            }catch (Exception e){
                logger.error("获取收藏图标失败",e);
                e.printStackTrace();
            }
        }
        return new Result(ResultMsg.SUCCESS,R.default_logo);
    }

    /**
     *根据收藏的文章标题和描述推介收藏夹
     */
    @PostMapping(value = "/getFavoriteResult")
    @LoggerManage(description = "获取推荐收藏夹")
    public Result getFavoriteResult(String title,String description){
        Long result = null;
        int faultPosition = 0;
        Map<String,Object> maps = new HashMap<String,Object>();
        List<Favorite> favorites = favoriteRepository.findByUserId(getUserId());
        for (int i = 0; i < favorites.size(); i++){
            Favorite favorite = favorites.get(i);
            if (favorite.getName().indexOf(title) > 0 || favorite.getName().indexOf(description) > 0){
                result = favorite.getId();
            }
            if("未读列表".equals(favorite.getName())){
                faultPosition = i;
            }
        }
        result = result == null ? favorites.get(faultPosition).getId() : result;
        maps.put("favoriteResult",result == null ?0 :result);
        maps.put("favoriteList",favorites);
        return new Result(ResultMsg.SUCCESS,maps);
    }

    @PostMapping(value = "/getMyCollects")
    @LoggerManage(description = "收藏的网站列表")
    public Result getMyCollects(@RequestParam(value = "page",defaultValue = "0") Integer page){
        try{
            Sort sort = new Sort(Sort.Direction.DESC,"id");
            Pageable pageable = new PageRequest(page,10,sort);
            List<CollectViewSummary> list = collectService.getAllCollects(getUserId(),pageable);
            return new Result(ResultMsg.SUCCESS,list);
        }catch(Exception e){
             logger.error("获取失败",e);
             return new Result(ResultMsg.FAILED);
        }
    }

}
