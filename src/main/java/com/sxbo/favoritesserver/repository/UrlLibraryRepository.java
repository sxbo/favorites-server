package com.sxbo.favoritesserver.repository;

import com.sxbo.favoritesserver.domain.UrlLibrary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Author xiaobo GG [https://github.com/sxbo]
 * @Date 2017/10/1917:18
 */
public interface UrlLibraryRepository extends JpaRepository<UrlLibrary,Long>{

    //找到所有count小于给定参数的urllibrary
    List<UrlLibrary> findByCountLessThanAndLogoUrl(int count,String logoUrl);

    UrlLibrary findByUrl(String url);

}
