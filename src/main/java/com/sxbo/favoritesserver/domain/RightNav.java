package com.sxbo.favoritesserver.domain;

import javax.persistence.*;

/**
 * @Author xiaobo GG [https://github.com/sxbo]
 * @Date 2017/9/279:38
 * 右侧导航，这里在换了源码名字，源码类名为Config
 */
@Entity
public class RightNav {
    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false)
    private Long userId;
    @Column(nullable = false)
    private String defaultFavortie;//设置默认收藏夹
    @Column(nullable = false)
    private String defaultCollectType; //设置默认收藏，专业模式，还是简单模式
    @Column(nullable = false)
    private String defaultModel;
    @Column(nullable = false)
    private Long createTime;
    @Column(nullable = false)
    private Long lastModifyTime;
    @Transient
    private String collectTypeName;
    @Transient
    private String modelName;

    public RightNav() {
        super();
    }

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

    public String getDefaultFavortie() {
        return defaultFavortie;
    }

    public void setDefaultFavortie(String defaultFavortie) {
        this.defaultFavortie = defaultFavortie;
    }

    public String getDefaultCollectType() {
        return defaultCollectType;
    }

    public void setDefaultCollectType(String defaultCollectType) {
        this.defaultCollectType = defaultCollectType;
    }

    public String getDefaultModel() {
        return defaultModel;
    }

    public void setDefaultModel(String defaultModel) {
        this.defaultModel = defaultModel;
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

    public String getCollectTypeName() {
        return collectTypeName;
    }

    public void setCollectTypeName(String collectTypeName) {
        this.collectTypeName = collectTypeName;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }
}
