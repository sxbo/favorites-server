package com.sxbo.favoritesserver.service;

import com.sxbo.favoritesserver.domain.Collect;
import com.sxbo.favoritesserver.domain.enums.CollectType;
import com.sxbo.favoritesserver.domain.view.CollectViewSummary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

/**
 * @Author xiaobo GG [https://github.com/sxbo]
 * @Date 2017/9/2716:03
 */
public interface CollectService {

    public List<CollectViewSummary> getCollects(String type, Long userId, Pageable pageable, Long favoriteId, Long specUserId);

    public List<CollectViewSummary> getAllCollects(Long userId,Pageable pageable);

    public List<CollectViewSummary> getTypeCollects(Long userId, CollectType type,Pageable pageable);

    public List<CollectViewSummary> getCollectsInFavorite(Long userId,Long favoriteId,Pageable pageable);

    public List<CollectViewSummary> getOthersPublicCollects(Long userID,Pageable pageable);

    public List<CollectViewSummary> getUserAndAttCollects(Long userId,List<Long> userIds,Pageable pageable);

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
    boolean isCollected(Long user,Collect collect);

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
    List<CollectViewSummary> searchMy(Long userId,String key,Pageable pageable);

    /**
     * 从别人的收藏中查找
     * @param userId
     * @param key
     * @return
     */
    List<CollectViewSummary> searchOther(Long userId, String key, Pageable pageable);

    /**
     * 收藏别人的收藏,返回是否收藏成功
     * @param collect
     */
    boolean collectOther (Long myId,Collect collect);

    void noticeFriends(Collect collect);

    /**
     * 点赞
     * @param userId
     * @param id
     */
    void praise (Long userId,Long id);



}
