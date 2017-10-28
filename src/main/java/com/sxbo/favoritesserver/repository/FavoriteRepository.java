package com.sxbo.favoritesserver.repository;

import com.sxbo.favoritesserver.domain.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @Author xiaobo GG [https://github.com/sxbo]
 * @Date 2017/9/2610:05
 */
public interface FavoriteRepository extends JpaRepository<Favorite,Long>{
    List<Favorite> findByUserId(Long userId);

    List<Favorite> findByUserIdOrderByIdAsc(Long userId);

    Favorite findByUserIdAndName(Long userId,String name);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("update Favorite f set f.count=(f.count+1),f.lastModifyTime=:lastModifyTime where f.id=:id")
    void increaseCountById(@Param("id") Long id,@Param("lastModifyTime") Long lastModifyTime);


    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("update Favorite f set f.count=(f.count-1),f.lastModifyTime=:lastModifyTime where f.id=:id")
    void reduceCountById (@Param("id") Long id,@Param("lastModifyTime") Long lastModifyTime);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("update Favorite set name=:name,lastModifyTime=:lastModifyTime where id=:id")
    void updateNameById(@Param("id") Long id,@Param("lastModifyTime") Long lastModifyTime,@Param("name") String name);

    @Query("select id from Favorite where name=?1")
    List<Long> findIdByName(String name);
}
