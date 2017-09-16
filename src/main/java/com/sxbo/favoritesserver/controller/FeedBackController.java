package com.sxbo.favoritesserver.controller;

import com.sxbo.favoritesserver.domain.FeedBack;
import com.sxbo.favoritesserver.domain.result.Result;
import com.sxbo.favoritesserver.domain.result.ResultMsg;
import com.sxbo.favoritesserver.service.FeedBackService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author xiaobo GG [https://github.com/sxbo]
 * @Date 2017/9/89:32
 */
@RestController
@RequestMapping("/feedback")
public class FeedBackController {
    @Autowired
    private FeedBackService feedBackService;
    protected Logger logger = Logger.getLogger(this.getClass());

    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public Result save(@RequestBody FeedBack feedBack){
        System.err.print(feedBack);
        try{
            feedBackService.saveFeedBack(feedBack);
        }catch (Exception e){
            logger.error("feedBack failed",e);
            return new Result(ResultMsg.FAILED);
        }
        return new Result(ResultMsg.SUCCESS);
    }
}
