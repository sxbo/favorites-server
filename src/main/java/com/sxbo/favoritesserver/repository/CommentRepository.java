package com.sxbo.favoritesserver.repository;

import com.sxbo.favoritesserver.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author xiaobo GG [https://github.com/sxbo]
 * @Date 2017/10/1816:51
 */
public interface CommentRepository extends JpaRepository<Comment,Long>{

    Long countByCollectId(Long collectId);
}
