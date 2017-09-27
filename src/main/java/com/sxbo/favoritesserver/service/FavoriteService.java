package com.sxbo.favoritesserver.service;

import com.sxbo.favoritesserver.domain.Favorite;

/**
 * @Author xiaobo GG [https://github.com/sxbo]
 * @Date 2017/9/2610:58
 */
public interface FavoriteService {
    public Favorite saveFavorite(Long userId,Long count,String name);
}
