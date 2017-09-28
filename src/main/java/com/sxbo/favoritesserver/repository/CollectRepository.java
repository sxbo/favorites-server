package com.sxbo.favoritesserver.repository;

import com.sxbo.favoritesserver.domain.Collect;
import com.sxbo.favoritesserver.domain.enums.CollectType;
import com.sxbo.favoritesserver.domain.enums.IsDelete;
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
            + "from Collect c,User u,Favorite f WHERE c.userId=u.id and c.favorId=f.id and c.isDelete='YES'";


    @Transactional
    Long deleteById(Long id);

    Long countByUserIdAndIsDelete(Long userId, IsDelete isDelete);

    Long countByUserIdAndTypeAndIsDelete(Long userId, CollectType type,IsDelete isDelete);

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

    List<Collect> findByUserIdAndIsDelete (Long userId,IsDelete isDelete);

}
