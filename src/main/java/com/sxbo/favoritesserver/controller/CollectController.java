package com.sxbo.favoritesserver.controller;

import com.sxbo.favoritesserver.comm.R;
import com.sxbo.favoritesserver.comm.aop.LoggerManage;
import com.sxbo.favoritesserver.domain.Collect;
import com.sxbo.favoritesserver.domain.Favorite;
import com.sxbo.favoritesserver.domain.result.Result;
import com.sxbo.favoritesserver.domain.result.ResultMsg;
import com.sxbo.favoritesserver.repository.CollectRepository;
import com.sxbo.favoritesserver.service.CollectService;
import com.sxbo.favoritesserver.service.FavoriteService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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


    @RequestMapping(value = "/collect",method = RequestMethod.POST)
    @LoggerManage(description = "文章收集")
    public Result collect(@RequestBody Collect collect){

        try{
            if (StringUtils.isBlank(collect.getLogoUrl())){//设置logo图片
                collect.setLogoUrl(R.BASE_PATH+R.default_logo);
            }
            collect.setUserId(getUserId());

            if (collectService.isCollected(collect)){ //该用户是否已经收藏该网站
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


}
