package com.sxbo.favoritesserver.repository;

import com.sxbo.favoritesserver.domain.Attention;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @Author xiaobo GG [https://github.com/sxbo]
 * @Date 2017/10/1719:11
 */
public interface AttentionRepository extends JpaRepository<Attention,Long>{

    //得到userId关注的所有人的userName
    @Query("select u.userName from Attention a ,User u where a.userId=:userId and a.attentionedUserId = u.id and a.status = 'FOLLOW'")
    List<String> findByUserId(@Param("userId") Long userId);

    //得到userId关注的所有人的id
    @Query("select att.attentionedUserId from User u,Attention att where u.id = att.userId and att.status='FOLLOW' and u.id=:userId")
    List<Long> findMyAttentionIdByUserId(@Param("userId") Long userId);
}
