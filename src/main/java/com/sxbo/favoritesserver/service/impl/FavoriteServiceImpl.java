package com.sxbo.favoritesserver.service.impl;

import com.sxbo.favoritesserver.domain.Favorite;
import com.sxbo.favoritesserver.repository.FavoriteRepository;
import com.sxbo.favoritesserver.service.FavoriteService;
import com.sxbo.favoritesserver.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author xiaobo GG [https://github.com/sxbo]
 * @Date 2017/9/2611:02
 */
@Service("favoriteService")
public class FavoriteServiceImpl implements FavoriteService {



    @Autowired
    private FavoriteRepository favoriteRepository;

    /**
     * 保存
     * @param userId
     * @param count
     * @param name
     * @return
     */
    public Favorite saveFavorite(Long userId, Long count, String name) {
        Favorite favorite = new Favorite();
        favorite.setName(name);
        favorite.setUserId(userId);
        favorite.setCount(count);
        favorite.setCreateTime(DateUtils.getCurrentTime());
        favorite.setLastModifyTime(DateUtils.getCurrentTime());
        favoriteRepository.save(favorite);
        System.err.println(favorite);
        return favorite;
    }

    //收藏夹是否已经存在
    @Override
    public boolean favoriteIsExsited(Long userId, String name) {
        Favorite favorite = favoriteRepository.findByUserIdAndName(userId,name);
        if (null != favorite){
            return true;
        }
        return false;
    }
}
