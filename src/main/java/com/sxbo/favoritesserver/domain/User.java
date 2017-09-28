package com.sxbo.favoritesserver.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class User extends EntitySerialiaz{


    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false,unique = true)
    private String userName;
    @Column(nullable = false)
    private String passWord;
    @Column(nullable = false,unique = true)
    private String email;
    @Column(nullable = true)
    private String profilePicture;
    @Column(nullable = true,length = 65535,columnDefinition = "text")
    private String introduction;
    @Column(nullable = false)
    private Long createTime;
    @Column(nullable = false)
    private Long lastModifyTime;
    @Column(nullable = true)
    private String outDate;
    @Column(nullable = true)
    private String validataCode;
    @Column(nullable = true)
    private String backgroundPicture;

   public User(){
    super();
   }

   public User(String emial,String nickName,String passWord,String userName){
       super();
       this.email = emial;
       this.passWord=passWord;
       this.userName = userName;
   }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }
    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getIntroduction() {
        return introduction;
    }
    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }
    public Long getCreateTime() {
        return createTime;
    }

    public void setLastModifyTime(Long lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }

    public Long getLastModifyTime() {
        return lastModifyTime;
    }
    public void setOutDate(String outDate) {
        this.outDate = outDate;
    }

    public String getOutDate() {
        return outDate;
    }

    public void setValidataCode(String validataCode) {
        this.validataCode = validataCode;
    }

    public String getValidataCode() {
        return validataCode;
    }

    public void setBackgroundPicture(String backgroundPicture) {
        this.backgroundPicture = backgroundPicture;
    }

    public String getBackgroundPicture() {
        return backgroundPicture;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", passWord='" + passWord + '\'' +
                ", email='" + email + '\'' +
                ", profilePicture='" + profilePicture + '\'' +
                ", introduction='" + introduction + '\'' +
                ", createTime=" + createTime +
                ", lastModifyTime=" + lastModifyTime +
                ", outDate='" + outDate + '\'' +
                ", validataCode='" + validataCode + '\'' +
                ", backgroundPicture='" + backgroundPicture + '\'' +
                '}';
    }
}
