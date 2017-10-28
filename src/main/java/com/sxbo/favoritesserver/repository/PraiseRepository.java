package com.sxbo.favoritesserver.repository;

import com.sxbo.favoritesserver.domain.Comment;
import com.sxbo.favoritesserver.domain.Praise;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author xiaobo GG [https://github.com/sxbo]
 * @Date 2017/10/1816:51
 */
public interface PraiseRepository extends JpaRepository<Praise,Long>{

    Long countByCollectId(Long collectId);

    Praise findByUserIdAndCollectId(Long userId,Long collectId);
}
