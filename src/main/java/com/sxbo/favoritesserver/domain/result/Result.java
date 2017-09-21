package com.sxbo.favoritesserver.domain.result;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;

/**
 * @Author xiaobo GG [https://github.com/sxbo]
 * @Date 2017/9/613:57
 */
public class Result extends ResponseEntity {
    public Result(HttpStatus status) {
        super(status);
    }

    public Result(ResultMsg msg) {
        super(new ResultResponse(msg.getCode(),msg.getMsg(),""), HttpStatus.OK);
    }
    public Result(ResultMsg msg,Object o){
        super(new ResultResponse(msg.getCode(),msg.getMsg(),o),HttpStatus.OK);
    }

    public Result(MultiValueMap headers, HttpStatus status) {
        super(headers, status);
    }

    public Result(Object body, MultiValueMap headers, HttpStatus status) {
        super(body, headers, status);
    }
}
