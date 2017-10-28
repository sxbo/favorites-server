package com.sxbo.favoritesserver.repository;

import com.sxbo.favoritesserver.domain.Collect;
import com.sxbo.favoritesserver.domain.enums.CollectType;
import com.sxbo.favoritesserver.domain.enums.IsDelete;
import com.sxbo.favoritesserver.domain.view.CollectView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @Author xiaobo GG [https://github.com/sxbo]
 * @Date 2017/9/2710:43
 */
public interface CollectRepository extends JpaRepository<Collect,Long>{

    public String baseSql = "select c.id as id,c.title as title, c.type as type,c.url as url,c.logoUrl as logoUrl,c.userId as userId, "
            + "c.remark as remark,c.description as description,c.lastModifyTime as lastModifyTime,c.createTime as createTime, "
            + "u.userName as userName,u.profilePicture as profilePicture,f.id as favoriteId,f.name as favoriteName "
            + "from Collect c,User u,Favorite f WHERE c.userId=u.id and c.favorId=f.id and c.isDelete='NO'" ;

    public String isDeleteBaseSql="select c.id as id,c.title as title, c.type as type,c.url as url,c.logoUrl as logoUrl,c.userId as userId, "
            + "c.remark as remark,c.description as description,c.lastModifyTime as lastModifyTime, "
            + "u.userName as userName,u.profilePicture as profilePicture,f.id as favoriteId,f.name as favoriteName "
            + "from Collect c,User u,Favorite f WHERE c.userId=u.id and c.favorId=f.id and c.isDelete='YES' ";


    @Query(baseSql+ " and c.type='public' and c.category=?1 ")
    Page<CollectView> findAllByCategory(String category, Pageable pageable);

    @Query(baseSql+ " and c.type='public' ")
    Page<CollectView> findAllIsPublic(Pageable pageable);

    /**
     * 找到某个用户所有公开和私有的collects view
     * @param userId
     * @param pageable
     * @return
     */
    @Query(baseSql+ " and c.userId=?1")
    Page<CollectView> finViewByUserId(Long userId,Pageable pageable);

    /**
     * 找到不是userId的所有公开的收藏，并分页，发现
     * @param userId
     * @param pageable
     * @return
     */
    @Query(baseSql+ " and c.type = 'public' and c.userId !=?1")
    Page<CollectView> findViewByUserIdExceptUserId(Long userId,Pageable pageable);

    @Query(isDeleteBaseSql+ " and c.userId=?1")
    Page<CollectView> findViewByUserIdAndIsDelete(Long userId,Pageable pageable);

    @Query(baseSql+ " and c.userId=?1 and c.type=?2")
    Page<CollectView> findViewByUserIdAndType(Long userId,CollectType type,Pageable pageable);

    @Query(baseSql+ " and c.userId=?1 and c.type=?2 and c.favorId=?3")
    Page<CollectView> findViewByUserIdAndTypeAndFavorId(Long userId,CollectType type,Long favoriteId,Pageable pageable);

    @Query(baseSql+ " and (c.userId=?1 or ( c.userId in ?2 and c.type='PUBLIC' )) ")
    Page<CollectView> findViewByUserIdAndFollows(Long userId,List<Long> follows,Pageable pageable);

    @Query(baseSql+" and c.favorId = ?1 ")
    Page<CollectView> findViewByFavorId(Long favorId,Pageable pageable);

    @Transactional
    Long deleteById(Long id);

    Long countByUserIdAndIsDelete(Long userId, IsDelete isDelete);

    Long countByUserIdAndTypeAndIsDelete(Long userId, CollectType type,IsDelete isDelete);

    Long countByFavorIdAndTypeAndIsDelete(Long favorId,CollectType type,IsDelete isDelete);

    Long countByFavorIdAndIsDelete(Long favorId,IsDelete isDelete);

    List<Collect> findByFavorIdAndIsDelete(Long favorId, IsDelete isDelete);

    List<Collect> findByFavorIdAndUrlAndUserIdAndIsDelete(Long favorId,String url,Long userId,IsDelete isDelete);

    Collect findByIdAndUserId(Long id,Long userId);

    Collect findByUserIdAndUrl(Long userId,String url);

    @Transactional
    @Modifying
    @Query("update Collect c set c.type = ?1 where c.id = ?2 and c.userId =?3")
    int modifyTypeByIdAndUserId(CollectType type,Long id,Long userId);

    @Transactional
    @Modifying
    @Query("delete from Collect where favorId = ?1")
    void deleteByFavorId(Long favorId);

    @Transactional
    @Modifying
    @Query("update Collect c set c.isDelete = ?1,c.lastModifyTime = ?2 where c.id = ?3")
    int modifyIsDeleteById(IsDelete isDelete,Long lastModifyTime,Long id);

    @Transactional
    @Modifying
    @Query("update Collect c set c.logoUrl = ?1,c.lastModifyTime = ?2 where c.url = ?3")
    int updateLogoUrlByUrl(String logoUrl,Long lastModifyTime,String url);

    @Query(baseSql+" and c.userId = ?1 and (c.title like ?2 or c.description like ?2) ")
    Page<CollectView> serachMyByKey(Long userId,String key,Pageable pageable);

    @Query(baseSql+" and c.type='public' and c.userId != ?1 and (c.title like ?2 or c.description like ?2) ")
    Page<CollectView> searchOtherByKey(Long userId,String key,Pageable pageable);

    /**
     * 查询某个用户的所有collect
     * @param userId
     * @return
     */
    @Query(baseSql+" and c.userId = ?1 ")
    List<Collect> findCollectByUserId(Long userId);

    /**
     * 查询某个收藏夹下的所有collect，无需其他参数
     * @param favorId
     * @return
     */
//    @Query(baseSql+" and c.favorId = ?1 ")
//    List<CollectView> findCollectByFavorId(Long favorId,Pageable pageable);

    List<Collect> findByUserIdAndIsDelete (Long userId,IsDelete isDelete);

}
