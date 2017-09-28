package com.sxbo.favoritesserver.domain;

import com.sxbo.favoritesserver.domain.enums.LetterType;

import javax.persistence.*;

/**
 * @Author xiaobo GG [https://github.com/sxbo]
 * @Date 2017/9/2716:39
 * 私信
 */
@Entity
public class Letter {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private Long sendUserId;

    @Column(nullable = false,length = 65535,columnDefinition = "Text")
    private String content;

    @Column(nullable = false)
    private Long receiveUserId;

    @Column(nullable = true)
    private Long pid;

    @Column(nullable = true)
    @Enumerated(EnumType.STRING)
    private LetterType type;

    @Column(nullable = false)
    private Long createTime;

    @Transient
    private String sendType;

    public Letter() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSendUserId() {
        return sendUserId;
    }

    public void setSendUserId(Long sendUserId) {
        this.sendUserId = sendUserId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getReceiveUserId() {
        return receiveUserId;
    }

    public void setReceiveUserId(Long receiveUserId) {
        this.receiveUserId = receiveUserId;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public LetterType getType() {
        return type;
    }

    public void setType(LetterType type) {
        this.type = type;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public String getSendType() {
        return sendType;
    }

    public void setSendType(String sendType) {
        this.sendType = sendType;
    }

    @Override
    public String toString() {
        return "Letter{" +
                "id=" + id +
                ", sendUserId=" + sendUserId +
                ", content='" + content + '\'' +
                ", receiveUserId=" + receiveUserId +
                ", pid=" + pid +
                ", type=" + type +
                ", createTime=" + createTime +
                ", sendType='" + sendType + '\'' +
                '}';
    }
}
