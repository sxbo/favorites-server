package com.sxbo.favoritesserver.service.impl;

import com.sxbo.favoritesserver.domain.Collect;
import com.sxbo.favoritesserver.domain.Favorite;
import com.sxbo.favoritesserver.domain.Praise;
import com.sxbo.favoritesserver.domain.enums.CollectType;
import com.sxbo.favoritesserver.domain.enums.IsDelete;
import com.sxbo.favoritesserver.domain.view.CollectView;
import com.sxbo.favoritesserver.domain.view.CollectViewSummary;
import com.sxbo.favoritesserver.repository.*;
import com.sxbo.favoritesserver.service.CollectService;
import com.sxbo.favoritesserver.service.FavoriteService;
import com.sxbo.favoritesserver.utils.DateUtils;
import com.sxbo.favoritesserver.utils.Res;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author xiaobo GG [https://github.com/sxbo]
 * @Date 2017/9/289:23
 */
@Service("collectService")
public class CollectServiceImpl implements CollectService {

    @Autowired
    private CollectRepository collectRepository;
    @Autowired
    private FavoriteRepository favoriteRepository;
    @Autowired
    private FavoriteService favoriteService;
    @Autowired
    private AttentionRepository attentionRepository;
    @Autowired
    private PraiseRepository praiseRepository;
    @Autowired
    private CommentRepository commentRepository;

    @Override
    public List<CollectViewSummary> getCollects(String type, Long userId, Pageable pageable, Long favoriteId, Long specUserId) {
        Page<CollectView> page = null;

        if ("my".equals(type)){
            List<Long> userIds = attentionRepository.findMyAttentionIdByUserId(userId);
            if (userIds ==null || userIds.size() ==0){
                page = collectRepository.finViewByUserId(userId,pageable);
            }else {
                page = collectRepository.findViewByUserIdAndFollows(userId,userIds,pageable);
            }
        }else if ("myself".equals(type)){
            page = collectRepository.finViewByUserId(userId,pageable);
        }else if ("explore".equals(type)){
            page = collectRepository.findViewByUserIdExceptUserId(userId,pageable);
        }else if ("others".equals(type)){
            page = collectRepository.findViewByUserIdAndType(userId,CollectType.PUBLIC,pageable);
            if (null != specUserId){
                userId = specUserId;
            }
        }else if ("otherpublic".equals(type)){
            page = collectRepository.findViewByUserIdAndTypeAndFavorId(userId,CollectType.PUBLIC,favoriteId,pageable);
            if (null !=specUserId){
                userId = specUserId;
            }
        }else if ("garbage".equals(type)){
            page = collectRepository.findViewByUserIdAndIsDelete(userId,pageable);
        }else {
            page = collectRepository.findViewByFavorId(favoriteId,pageable);
        }
        return convertCollect(page,userId);
    }

    /**
     * 获取某个用户的所有collect含公开和私有
     *@Author xiaobo GG [https://github.com/sxbo]
     *@Date 2017/10/18 10:38
     */
    public List<CollectViewSummary> getAllCollects(Long userId,Pageable pageable){
        Page<CollectView> page = collectRepository.finViewByUserId(userId,pageable);
        return convertCollect(page,userId);
    }

    /**
     * 获取某个用户的所有公开收藏h或者私有收藏
     * userId :当前登录用户的id
     * @return
     */
    public List<CollectViewSummary> getTypeCollects(Long userId,CollectType type,Pageable pageable){
        Page<CollectView> page = collectRepository.findViewByUserIdAndType(userId,type,pageable);
        return convertCollect(page,userId);
    }

    /**
     *获取某个收藏夹下的所有collects
     */
    public List<CollectViewSummary> getCollectsInFavorite(Long userId,Long favoriteId,Pageable pageable){
        Page<CollectView> page = collectRepository.findViewByFavorId(favoriteId,pageable);
        return convertCollect(page,userId);
    }

    /**
     * 获取其他用户的所有公开收藏
     * @return
     */
    public List<CollectViewSummary> getOthersPublicCollects(Long userId,Pageable pageable){
        Page<CollectView> page = collectRepository.findViewByUserIdExceptUserId(userId,pageable);
        return convertCollect(page,userId);
    }

    /**
     * 获取某个用户和他关注的所有人的所有collects
     * @return
     */
    public List<CollectViewSummary> getUserAndAttCollects(Long userId,List<Long> userIds,Pageable pageable){
        Page<CollectView> page = collectRepository.findViewByUserIdAndFollows(userId,userIds,pageable);
        return convertCollect(page,userId);
    }

    private List<CollectViewSummary> convertCollect(Page<CollectView> views,Long userId){
        List<CollectViewSummary> summaries = new ArrayList<>();
        List<CollectView> viewList = views.getContent();
        for (CollectView view:viewList){
            CollectViewSummary summary = new CollectViewSummary(view);
            summary.setPraiseCount(praiseRepository.countByCollectId(view.getId()));
            summary.setCommentCount(commentRepository.countByCollectId(view.getId()));
            Praise praise = praiseRepository.findByUserIdAndCollectId(userId,view.getId());
            if (praise !=null){
                summary.setPraise(true);
            }else {
                summary.setPraise(false);
            }
            summary.setCollectTime(DateUtils.getTimeFormatText(view.getCreateTime()));
            summaries.add(summary);
        }
        return summaries;
    }

    /**
     * 这里设计为只要存储一条collect，就在该collect对应的收藏夹中数目加一
     * @param collect
     */
    @Override
    public void saveCollect(Collect collect) {

        if(collect.getType() == null){
            collect.setType(CollectType.PUBLIC);
        }else {
            collect.setType(CollectType.PRIVATE);
        }

        if (StringUtils.isBlank(collect.getDescription())){
            collect.setDescription(collect.getTitle());
        }
        collect.setIsDelete(IsDelete.NO);
        collect.setCreateTime(DateUtils.getCurrentTime());
        collect.setLastModifyTime(DateUtils.getCurrentTime());
        //存储一个collect时，给对应的favorite收藏夹，数量增1
        favoriteRepository.increaseCountById(collect.getFavorId(),collect.getLastModifyTime());
        collectRepository.save(collect);

    }

    /**
     * 这里可以修改的属性有title，description，lastModifyTime，type:私密状态
     * 另外添加move功能，可以修改所属收藏夹，或者可以移除到回收站，
     * @param newCollect
     */
    @Override
    public void updateCollect(Collect newCollect) {
        Collect collect = collectRepository.findOne(newCollect.getId());
        if (newCollect.getType() ==null){
            collect.setType(CollectType.PUBLIC);
        }
        if (StringUtils.isBlank(newCollect.getDescription())){
            collect.setDescription(newCollect.getTitle());
        }

        //改为只修改以下几个属性，其他属性暂时不动
        collect.setTitle(newCollect.getTitle());
        collect.setDescription(newCollect.getDescription());
        collect.setType(newCollect.getType());
        collect.setLastModifyTime(DateUtils.getCurrentTime());
        collectRepository.save(collect);
    }


    /**
     * @Author xiaobo
     * @param collect
     * @return true:已经收藏 flase:还未收藏
     * 这里根据，该用户是否已经存在相同url的收藏，来判断
     */
    public boolean isCollected(Long user,Collect collect) {
        Collect collect1 = collectRepository.findByUserIdAndUrl(user,collect.getUrl());
        if (null != collect1){
            return true;
        }
        return false;
    }

    /**
     * 后边完善
     * @param map
     * @param favorId
     * @param userId
     * @param type
     */
    @Override
    public void importHtml(Map<String, String> map, Long favorId, Long userId, String type) {

    }

    @Override
    public StringBuilder exportToHtml(Long favorId) {
        return null;
    }


    @Override
    public List<CollectViewSummary> searchMy(Long userId, String key,Pageable pageable) {
        Page<CollectView> page = collectRepository.serachMyByKey(userId,"%"+key+"%",pageable);
        return convertCollect(page,userId);
    }

    @Override
    public List<CollectViewSummary> searchOther(Long userId, String key,Pageable pageable) {
        Page<CollectView> page = collectRepository.searchOtherByKey(userId,"%"+key+"%",pageable);
        return convertCollect(page,userId);
    }

    /**
     * 收藏别人的文章
     * @param myId
     * @param collect
     * @return true，收藏成功；false，收藏失败
     */
    @Override
    public boolean collectOther(Long myId,Collect collect) {
        //如果我还没收藏该collect，那么把该collect的userId改成我的id并收藏；
        if (!isCollected(myId,collect)){
            collect.setUserId(myId);
            saveCollect(collect);
            return true;
        }
        return false;
    }

    public void noticeFriends(Collect collect){

    }


    @Override
    public void praise(Long userId, Long cid) {
        Praise praise = praiseRepository.findByUserIdAndCollectId(userId,cid);
        if(praise == null){ //如果serId还没赞这条收藏
            Praise praise1= new Praise();
            praise1.setUserId(userId);
            praise1.setCollectId(cid);
            praise1.setCreateTime(DateUtils.getCurrentTime());
            praiseRepository.save(praise1);
        }else { //已经赞过，该操作取消点赞
            praiseRepository.delete(praise.getId());
        }
    }
}
