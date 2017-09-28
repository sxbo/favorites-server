package com.sxbo.favoritesserver.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @Author xiaobo GG [https://github.com/sxbo]
 * @Date 2017/9/89:23
 */
@Entity
public class FeedBack extends EntitySerialiaz{

    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = true)
    private Long userId;
    @Column(nullable = false)
    private String feedBackAdvice;
    @Column(nullable = true)
    private String feedBackName;
    @Column(nullable = false)
    private String phone;
    @Column(nullable = false)
    private Long createTime;
    @Column(nullable = false)
    private Long lastModifyTime;

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

    public String getFeedBackAdvice() {
        return feedBackAdvice;
    }

    public void setFeedBackAdvice(String feedBackAdvice) {
        this.feedBackAdvice = feedBackAdvice;
    }

    public String getFeedBackName() {
        return feedBackName;
    }

    public void setFeedBackName(String feedBackName) {
        this.feedBackName = feedBackName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    @Override
    public String toString() {
        return "FeedBack{" +
                "id=" + id +
                ", userId=" + userId +
                ", feedBackAdvice='" + feedBackAdvice + '\'' +
                ", feedBackName='" + feedBackName + '\'' +
                ", phone='" + phone + '\'' +
                ", createTime=" + createTime +
                ", lastModifyTime=" + lastModifyTime +
                '}';
    }
}
