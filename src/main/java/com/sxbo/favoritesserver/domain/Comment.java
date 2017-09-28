package com.sxbo.favoritesserver.domain;

import javax.persistence.*;

/**
 * @Author xiaobo GG [https://github.com/sxbo]
 * @Date 2017/9/2716:37
 * 评论
 */
@Entity
public class Comment {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private Long collectId;

    @Column(nullable = false,length = 65535,columnDefinition = "Text")
    private String content;

    @Column(nullable = true)
    private Long replyUserId;//回复者

    @Column(nullable = false)
    private Long createTime;

    @Transient
    private String commentTime;//评论时间

    @Transient
    private String userName;

    @Transient
    private String replyUserName;

    @Transient
    private String profilePicture;

    public Comment() {
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

    public Long getCollectId() {
        return collectId;
    }

    public void setCollectId(Long collectId) {
        this.collectId = collectId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getReplyUserId() {
        return replyUserId;
    }

    public void setReplyUserId(Long replyUserId) {
        this.replyUserId = replyUserId;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public String getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(String commentTime) {
        this.commentTime = commentTime;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getReplyUserName() {
        return replyUserName;
    }

    public void setReplyUserName(String replyUserName) {
        this.replyUserName = replyUserName;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", userId=" + userId +
                ", collectId=" + collectId +
                ", content='" + content + '\'' +
                ", replyUserId=" + replyUserId +
                ", createTime=" + createTime +
                ", commentTime='" + commentTime + '\'' +
                ", userName='" + userName + '\'' +
                ", replyUserName='" + replyUserName + '\'' +
                ", profilePicture='" + profilePicture + '\'' +
                '}';
    }
}
