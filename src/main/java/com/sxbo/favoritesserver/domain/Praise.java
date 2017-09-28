package com.sxbo.favoritesserver.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @Author xiaobo GG [https://github.com/sxbo]
 * @Date 2017/9/2716:40
 * 点赞
 */

@Entity
public class Praise {
    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false)
    private Long collectId;
    @Column(nullable = false)
    private Long userId;
    @Column(nullable = false)
    private Long createTime;

    public Praise() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCollectId() {
        return collectId;
    }

    public void setCollectId(Long collectId) {
        this.collectId = collectId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Praise{" +
                "id=" + id +
                ", collectId=" + collectId +
                ", userId=" + userId +
                ", createTime=" + createTime +
                '}';
    }
}
