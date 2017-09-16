package com.sxbo.favoritesserver.domain.result;

/**
 * @Author xiaobo GG [https://github.com/sxbo]
 * @Date 2017/9/610:10
 */

public class ResultResponse {
    //http状态码
    private String code;
    //提示消息
    private String msg;
    ///返回的数据
    private Object data;

    public ResultResponse() {

    }

    public ResultResponse(ResultMsg resultMsg,Object data){
        this.code = resultMsg.getCode();
        this.msg = resultMsg.getMsg();
        this.data = data;
    }

    public ResultResponse(String code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
