package com.sxbo.favoritesserver.controller;

import com.sxbo.favoritesserver.comm.aop.LoggerManage;
import com.sxbo.favoritesserver.domain.Favorite;
import com.sxbo.favoritesserver.domain.enums.CollectType;
import com.sxbo.favoritesserver.domain.enums.IsDelete;
import com.sxbo.favoritesserver.domain.result.Result;
import com.sxbo.favoritesserver.domain.result.ResultMsg;
import com.sxbo.favoritesserver.repository.CollectRepository;
import com.sxbo.favoritesserver.repository.FavoriteRepository;
import com.sxbo.favoritesserver.service.FavoriteService;
import com.sxbo.favoritesserver.utils.DateUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author xiaobo GG [https://github.com/sxbo]
 * @Date 2017/9/2610:54
 */
@RestController
@RequestMapping("/fav")
public class FavoriteController extends BaseCotroller{

    @Autowired
    private FavoriteRepository favoriteRepository;

    @Autowired
    private FavoriteService favoriteService;
    @Autowired
    private CollectRepository collectRepository;
    @PostMapping(value = "/add")
    @LoggerManage( description = "添加收藏夹")

    public Result addFavorite(String name){
        if (StringUtils.isNotBlank(name)){
            Favorite favorite = favoriteRepository.findByUserIdAndName(getUserId(),name);
            if (null !=favorite){
                logger.info("收藏夹已经被创建");
                return new Result(ResultMsg.FavoritesNameUsed);
            }else {
                try{
                    favoriteService.saveFavorite(getUserId(),0l,name);
                }catch (Exception e){
                    logger.error("异常",e);
                    return new Result(ResultMsg.FAILED);
                }
                return new Result(ResultMsg.SUCCESS);
            }
        }else {
            logger.info("收藏夹名称为空");
            return new Result(ResultMsg.FavoritesNameIsNull);
        }
    }

    @RequestMapping(value = "/addImport",method = RequestMethod.POST)
    @LoggerManage(description = "创建导入收藏夹")
    public Result addImportFavorite(){
        Favorite favorite = favoriteRepository.findByUserIdAndName(getUserId(),"导入自浏览器");
        if (null ==favorite){
            try{
                favorite = favoriteService.saveFavorite(getUserId(),0l,"导入自浏览器");
            }catch (Exception e){
                logger.error("异常",e);
                return  new Result(ResultMsg.FAILED);
            }
        }
        return new Result(ResultMsg.SUCCESS,favorite.getUserId());
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    @LoggerManage(description = "修改收藏夹")
    public Result updateFavorite(@RequestBody String name,Long id){
        if (StringUtils.isNotBlank(name) && null == id){
            Favorite favorite = favoriteRepository.findByUserIdAndName(getUserId(),name);
            if (null !=favorite){
                logger.info("收藏夹名称已被创建");
                return new Result(ResultMsg.FavoritesNameUsed);
            }else {
                try {
                    favoriteRepository.updateNameById(id, DateUtils.getCurrentTime(),name);
                }catch (Exception e){
                    logger.error("修改失败",e);
                    return new Result(ResultMsg.FAILED);
                }
            }
        }else {
            logger.info("参数错误name"+name+"-----"+"id"+id);
            return new Result(ResultMsg.FAILED);
        }
        return null;
    }


    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    @LoggerManage(description = "删除收藏夹")
    public Result delFavorite(@RequestBody Long id){
        if (null ==id){
            return new Result(ResultMsg.FAILED);
        }

        try{
            Favorite favorite = favoriteRepository.findOne(id);
            if (null !=favorite && getUserId().longValue() == favorite.getUserId().longValue()){
                favoriteRepository.delete(id);
                //删除该收藏夹下的文章,过段时间加

                return new Result(ResultMsg.SUCCESS);
            }

        }catch (Exception e){
            logger.error("删除异常",e);
            return new Result(ResultMsg.FAILED);
        }
        return null;
    }

    @RequestMapping(value = "/get/{userId}",method = RequestMethod.POST)
    @LoggerManage(description = "获取收藏夹")
    public Result getFavorites (@PathVariable("userId") Long userId){
        List<Favorite> favorites = null;
        try{
            Long id = getUserId();
            if (null !=userId && 0 != userId){
                id = userId;
            }
            favorites = favoriteRepository.findByUserIdOrderByIdAsc(id);
            if (!getUserId().equals(userId)) {
                for (Favorite fav : favorites) { //给每个收藏夹设置这两个值
                    fav.setPublicCount(collectRepository.countByFavorIdAndTypeAndIsDelete(fav.getId(), CollectType.PUBLIC, IsDelete.NO));
                    fav.setCount(collectRepository.countByFavorIdAndIsDelete(fav.getId(), IsDelete.NO));
                }
            }
        }catch (Exception e){
            logger.error("获取收藏夹异常",e);
            return  new Result(ResultMsg.FAILED);
        }
        return new Result(ResultMsg.SUCCESS,favorites);
    }

}
