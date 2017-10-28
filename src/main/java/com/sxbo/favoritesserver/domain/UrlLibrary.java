package com.sxbo.favoritesserver.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @Author xiaobo GG [https://github.com/sxbo]
 * @Date 2017/10/1917:14
 */
@Entity
public class UrlLibrary extends EntitySerialiaz{
    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false)
    private String url;
    @Column(nullable = false)
    private String logoUrl;
    @Column(columnDefinition = "INT default 0")
    private int count;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
