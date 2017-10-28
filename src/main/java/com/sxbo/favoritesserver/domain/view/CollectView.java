package com.sxbo.favoritesserver.domain.view;

/**
 * @Author xiaobo GG [https://github.com/sxbo]
 * @Date 2017/10/1815:13
 */
public interface CollectView {
    Long getId();
    Long getUserId();
    String getProfilePicture();
    String getTitle();
    String getType();
    String getUrl();
    String getLogoUrl();
    String getRemark();
    String getDescription();
    Long getLastModifyTime();
    Long getCreateTime();
    String getUserName();
    Long getFavoriteId();
    String getFavoriteName();
    String getOperId();



}
