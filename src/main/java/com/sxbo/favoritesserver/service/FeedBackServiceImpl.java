package com.sxbo.favoritesserver.service;

import com.sxbo.favoritesserver.domain.FeedBack;
import com.sxbo.favoritesserver.repository.FeedBackRepository;
import com.sxbo.favoritesserver.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author xiaobo GG [https://github.com/sxbo]
 * @Date 2017/9/89:39
 */
@Service("feedBackService")
public class FeedBackServiceImpl implements FeedBackService {

    @Autowired
    private FeedBackRepository feedBackRepository;
    @Override
    public void saveFeedBack(FeedBack feedBack) {
        feedBack.setCreateTime(DateUtils.getCurrentTime());
        feedBack.setLastModifyTime(DateUtils.getCurrentTime());
        feedBackRepository.save(feedBack);
    }
}
