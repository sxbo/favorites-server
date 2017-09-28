package com.sxbo.favoritesserver.service;

import com.sxbo.favoritesserver.domain.Collect;

import java.util.List;
import java.util.Map;

/**
 * @Author xiaobo GG [https://github.com/sxbo]
 * @Date 2017/9/2716:03
 */
public interface CollectService {

    /**
     * 收藏一条记录
     * @param collect
     */
    void saveCollect(Collect collect);

    /**
     * 跟新一条收藏
     * @param newCollect
     */
    void updateCollect(Collect newCollect);

    /**
     * 验证该收藏是否已经被 己收藏
     * @param collect
     * @return
     */
    boolean isCollected(Collect collect);

    /**
     * 把html导入到收藏夹中
     * @param map
     * @param favorId
     * @param userId
     * @param type
     */
    void importHtml(Map<String,String> map,Long favorId,Long userId,String type);

    /**
     * 把收藏夹导出为Html
     * @param favorId
     * @return
     */
    StringBuilder exportToHtml(Long favorId);

    /**
     * 从自己的收藏中查找
     * @param userId
     * @param key
     * @return
     */
    List<Collect> searchMy(Long userId,String key);

    /**
     * 从别人的收藏中查找
     * @param userId
     * @param key
     * @return
     */
    List<Collect> searchOther(Long userId,String key);

    /**
     * 收藏别人的收藏
     * @param collect
     */
    void otherCollect (Collect collect);

    /**
     * 点赞
     * @param userId
     * @param id
     */
    void praise (Long userId,Long id);



}
