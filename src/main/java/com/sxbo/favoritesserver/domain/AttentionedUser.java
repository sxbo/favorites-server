package com.sxbo.favoritesserver.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @Author xiaobo GG [https://github.com/sxbo]
 * @Date 2017/9/2716:41
 * 被我关注的用户
 */
@Entity
public class AttentionedUser {

    @Id
    private Long id;

    private String userName;

    private String profilePicture;

    private String attentioned;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getAttentioned() {
        return attentioned;
    }

    public void setAttentioned(String attentioned) {
        this.attentioned = attentioned;
    }

    @Override
    public String toString() {
        return "AttentionedUser{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", profilePicture='" + profilePicture + '\'' +
                ", attentioned='" + attentioned + '\'' +
                '}';
    }
}
