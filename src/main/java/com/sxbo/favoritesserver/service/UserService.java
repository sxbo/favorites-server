package com.sxbo.favoritesserver.service;

public interface UserService {

    /**
     *@Author xiaobo GG [https://github.com/sxbo]
     *@Date 2017/9/5 11:41
     * @param name
     * @param age
     */
    void create (String name,Integer age);
    /**
     *@Author xiaobo GG [https://github.com/sxbo]
     *@Date 2017/9/5 13:50
     * 根据名字删除用户
     */
    void deleteByName(String name);
    /**
     *@Author xiaobo GG [https://github.com/sxbo]
     *@Date 2017/9/5 13:50
     * 得到所有用户
     */
    void getAllUsers();
    /**
     *@Author xiaobo GG [https://github.com/sxbo]
     *@Date 2017/9/5 13:51
     * 删除所有用户
     */
    void deleteAllUsers();
}
