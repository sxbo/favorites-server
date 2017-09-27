package com.sxbo.favoritesserver.service;

import com.sxbo.favoritesserver.domain.Collect;

import java.util.List;

/**
 * @Author xiaobo GG [https://github.com/sxbo]
 * @Date 2017/9/2716:03
 */
public interface CollectService {
    void saveCollect(Collect collect);

    void updateCollect(Collect newCollect);

    void checkCollect(Collect collect);


}
