package com.sxbo.favoritesserver.domain;

import javax.persistence.*;

/**
 * 收藏夹
 * @Author xiaobo GG [https://github.com/sxbo]
 * @Date 2017/9/269:09
 */
@Entity
public class Favorite extends EntitySerialiaz{

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Long count;

    @Column(nullable = false)
    private Long createTime;

    @Column(nullable = false)
    private Long lastModifyTime;

    public void setId(Long id) {
        this.id = id;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public void setLastModifyTime(Long lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }

    public void setPublicCount(Long publicCount) {
        this.publicCount = publicCount;
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public Long getCount() {
        return count;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public Long getLastModifyTime() {
        return lastModifyTime;
    }

    public Long getPublicCount() {
        return publicCount;
    }

    @Transient
    private Long publicCount;

}
