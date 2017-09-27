package com.sxbo.favoritesserver.domain;

import com.sxbo.favoritesserver.domain.enums.AttentionStatus;

import javax.persistence.*;

/**
 * @Author xiaobo GG [https://github.com/sxbo]
 * @Date 2017/9/2716:38
 * 关注
 */
@Entity
public class Attention {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private Long AttentionedUserId;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private AttentionStatus status;

    @Column(nullable = false)
    private Long createTime;

    @Column(nullable = false)
    private Long lastModifyTime;

    @Transient
    private String name;

    public Attention() {
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

    public Long getAttentionedUserId() {
        return AttentionedUserId;
    }

    public void setAttentionedUserId(Long attentionedUserId) {
        AttentionedUserId = attentionedUserId;
    }

    public AttentionStatus getStatus() {
        return status;
    }

    public void setStatus(AttentionStatus status) {
        this.status = status;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
