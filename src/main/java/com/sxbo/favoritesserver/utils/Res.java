package com.sxbo.favoritesserver.utils;

/**
 * @Author xiaobo GG [https://github.com/sxbo]
 * @Date 2017/10/1514:36
 */
public class Res<T> {

    private int code=0;
    private T data;
    private String msg;
    private String error;

    public static Res ok=new Res();

    public static Res build(){
        return new Res();
    }
    public static <T> Res build(T data){
        return Res.build().data(data);
    }

    public Res data(T data){
        this.data=data;
        return this;
    }
    public Res code(int code){
        this.code=code;
        return this;
    }
    public Res msg(String msg){
        this.msg=msg;
        return this;
    }
    public Res error(String error){
        this.error=error;
        return this;
    }
}
