package com.sxbo.favoritesserver.domain;

import com.sxbo.favoritesserver.domain.enums.CollectType;
import com.sxbo.favoritesserver.domain.enums.IsDelete;

import javax.persistence.*;

/**
 * @Author xiaobo GG [https://github.com/sxbo]
 * @Date 2017/9/279:30
 * 具体某条收藏
 */
@Entity
public class Collect extends EntitySerialiaz{
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private Long favorId;

    @Column(nullable = false)
    private String url;

    @Column(nullable = false)
    private String title;

    @Column(nullable = true ,length = 65535,columnDefinition = "Text")
    private String description;

    @Column(nullable = true)
    private String logoUrl;

    @Column(nullable = true)
    private String charset;

    @Enumerated(EnumType.STRING)
    @Column(nullable = true)
    private CollectType type;

    @Column(nullable = true)
    private String remark;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private IsDelete isDelete;

    @Column(nullable = false)
    private Long createTime;

    @Column(nullable = false)
    private Long lastModifyTime;

    @Column(nullable = true)
    private String category;//种类

    @Transient
    private String collectTime;

    @Transient
    private String newFavorite;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getFavorId() {
        return favorId;
    }

    public void setFavorId(Long favorId) {
        this.favorId = favorId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    public CollectType getType() {
        return type;
    }

    public void setType(CollectType type) {
        this.type = type;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public IsDelete getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(IsDelete isDelete) {
        this.isDelete = isDelete;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getLastModifyTime() {
        return lastModifyTime;
    }

    public void setLastModifyTime(Long lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCollectTime() {
        return collectTime;
    }

    public void setCollectTime(String collectTime) {
        this.collectTime = collectTime;
    }

    public String getNewFavorite() {
        return newFavorite;
    }

    public void setNewFavorite(String newFavorite) {
        this.newFavorite = newFavorite;
    }

    @Override
    public String toString() {
        return "Collect{" +
                "id=" + id +
                ", userId=" + userId +
                ", favorId=" + favorId +
                ", url='" + url + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", logoUrl='" + logoUrl + '\'' +
                ", charset='" + charset + '\'' +
                ", type=" + type +
                ", remark='" + remark + '\'' +
                ", isDelete=" + isDelete +
                ", createTime=" + createTime +
                ", lastModifyTime=" + lastModifyTime +
                ", category='" + category + '\'' +
                ", collectTime='" + collectTime + '\'' +
                ", newFavorite='" + newFavorite + '\'' +
                '}';
    }
}
