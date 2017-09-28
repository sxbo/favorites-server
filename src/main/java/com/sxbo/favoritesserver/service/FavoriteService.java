package com.sxbo.favoritesserver.service;

import com.sxbo.favoritesserver.domain.Favorite;

/**
 * @Author xiaobo GG [https://github.com/sxbo]
 * @Date 2017/9/2610:58
 */
public interface FavoriteService {
    Favorite saveFavorite(Long userId,Long count,String name);

    //收藏夹是否已经存在
    boolean favoriteIsExsited (Long userId,String name);
}
