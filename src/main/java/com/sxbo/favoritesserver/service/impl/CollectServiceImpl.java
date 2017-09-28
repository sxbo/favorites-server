package com.sxbo.favoritesserver.service.impl;

import com.sxbo.favoritesserver.domain.Collect;
import com.sxbo.favoritesserver.domain.enums.CollectType;
import com.sxbo.favoritesserver.domain.enums.IsDelete;
import com.sxbo.favoritesserver.repository.CollectRepository;
import com.sxbo.favoritesserver.repository.FavoriteRepository;
import com.sxbo.favoritesserver.service.CollectService;
import com.sxbo.favoritesserver.utils.DateUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        collectRepository.save(collect);
        favoriteRepository.increaseCountById(collect.getFavorId(), DateUtils.getCurrentTime());


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
     * 这里判断，该用户是否已经存在相同url的收藏，来判断
     */
    public boolean isCollected(Collect collect) {
        Collect collect1 = collectRepository.findByUserIdAndUrl(collect.getUserId(),collect.getUrl());
        if (null != collect1){
            return true;
        }
        return false;
    }

    @Override
    public void importHtml(Map<String, String> map, Long favorId, Long userId, String type) {

    }

    @Override
    public StringBuilder exportToHtml(Long favorId) {
        return null;
    }

    @Override
    public List<Collect> searchMy(Long userId, String key) {
        return null;
    }

    @Override
    public List<Collect> searchOther(Long userId, String key) {
        return null;
    }

    @Override
    public void otherCollect(Collect collect) {

    }

    @Override
    public void praise(Long userId, Long id) {

    }
}
