package com.sxbo.favoritesserver.controller;

import com.sxbo.favoritesserver.comm.R;
import com.sxbo.favoritesserver.comm.aop.LoggerManage;
import com.sxbo.favoritesserver.domain.Favorite;
import com.sxbo.favoritesserver.domain.User;
import com.sxbo.favoritesserver.domain.result.Result;
import com.sxbo.favoritesserver.domain.result.ResultMsg;
import com.sxbo.favoritesserver.repository.UserRepository;
import com.sxbo.favoritesserver.service.FavoriteService;
import com.sxbo.favoritesserver.utils.DateUtils;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/**
 * @Author xiaobo GG [https://github.com/sxbo]
 * @Date 2017/9/515:06
 */

@RestController
@RequestMapping("/user")
public class UserController extends BaseCotroller{

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private FavoriteService favoriteService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @LoggerManage(description = "login")
    public Result login(@RequestBody User user){
        try{
            User loginuser = userRepository.findByUserNameOrEmail(user.getUserName(),user.getEmail());
            if (loginuser == null) {
                return new Result(ResultMsg.LoginNameNotExists,"");
            }else if(!user.getPassWord().equals(loginuser.getPassWord())){
                return  new Result(ResultMsg.LoginNameOrPassWordError,"");
            }
            getSession().setAttribute(R.LOGIN_SESSION_KEY,loginuser);
            getSession().setMaxInactiveInterval(R.COOKIE_TIMEOUT);
            return new Result(ResultMsg.SUCCESS,loginuser);
        }catch (Exception e){
            logger.error("login failed",e);
            return new Result(ResultMsg.FAILED,"");
        }
    }

    @RequestMapping(value = "/regist" ,method = RequestMethod.POST)
    @LoggerManage(description = "注册")
    public Result create(@RequestBody User user){
        try{
            System.err.print(getSession().getAttribute(R.LOGIN_SESSION_KEY));
            User registUser = userRepository.findByEmail(user.getEmail());
            if (null != registUser){
                return new Result(ResultMsg.EmailUsed);
            }
            User userNameUser = userRepository.findByUserName(user.getUserName());
            if (null != userNameUser){
                return new Result(ResultMsg.UserNameUsed);
            }
            user.setCreateTime(DateUtils.getCurrentTime());
            user.setLastModifyTime(DateUtils.getCurrentTime());
            user.setProfilePicture("img/favicon.png");
            userRepository.save(user);
            //添加未读列表默认呢收藏夹
            Favorite favorite = favoriteService.saveFavorite(user.getId(),0l,"未读列表");
            return new Result(ResultMsg.SUCCESS);
        }catch (Exception e){
            logger.error("",e);
            return new Result(ResultMsg.FAILED);
        }
    }

    @RequestMapping(value = "updatePassWord",method = RequestMethod.POST)
    @LoggerManage(description = "修改密码")
    public Result updatePassword(String userName ,String oldPassWord, String newPassWord){
        try{
            User user = userRepository.findByUserName(userName);
            if (user.getPassWord().equals(oldPassWord)){
                userRepository.setNewPassword(newPassWord,user.getEmail());
                user.setPassWord(newPassWord);
                return new Result(ResultMsg.SUCCESS);
            }else {
                return new Result(ResultMsg.PassWordError);
            }
        }catch (Exception e){
            logger.error("update password failed",e);
            return new Result(ResultMsg.FAILED);
        }
    }

    @RequestMapping(value = "/updateIntroduction",method = RequestMethod.POST)
    @LoggerManage(description = "修改个人简历")
    public Result updateIntroduction(String introduction,Long id){
        try{
            User user = userRepository.getOne(id);
            if (user == null){
                return new Result(ResultMsg.LoginNameNotExists);
            }
            userRepository.setIntroduction(introduction,user.getEmail());
            user.setIntroduction(introduction);
            return new Result(ResultMsg.SUCCESS,user);
        }catch (Exception e){
            logger.error("修改失败",e);
            return new Result(ResultMsg.FAILED);
        }
    }

    /**
     * 修改昵称
     * @param newUserName
     * @return
     */
    @RequestMapping(value = "/updateUserName", method = RequestMethod.POST)
    @LoggerManage(description="修改昵称")
    public Result updateUserName(String oldUserName, String newUserName) {
        try {
            User loginUser = userRepository.findByUserName(oldUserName);
            if(newUserName.equals(loginUser.getUserName())){
                return new Result(ResultMsg.UserNameSame);
            }
            User user = userRepository.findByUserName(newUserName);
            if(null != user && user.getUserName().equals(newUserName)){
                return new Result(ResultMsg.UserNameUsed);
            }
            if(newUserName.length()>12){
                return new Result(ResultMsg.UserNameLengthLimit);
            }
            userRepository.setUserName(newUserName, loginUser.getEmail());
            loginUser.setUserName(newUserName);
            return new Result(ResultMsg.SUCCESS, newUserName);
        } catch (Exception e) {
            // TODO: handle exception
            logger.error("updateUserName failed, ", e);
            return new Result(ResultMsg.FAILED);
        }
    }

//    /**
//     * 上传头像
//     * @param dataUrl
//     * @return
//     */
//    @RequestMapping(value = "/uploadHeadPortrait", method = RequestMethod.POST)
//    public Result uploadHeadPortrait(String dataUrl){
//        logger.info("执行 上传头像 开始");
//        try {
//            String filePath=staticUrl+fileProfilepicturesUrl;
//            String fileName= UUID.randomUUID().toString()+".png";
//            String savePath = fileProfilepicturesUrl+fileName;
//            String image = dataUrl;
//            String header ="data:image";
//            String[] imageArr=image.split(",");
//            if(imageArr[0].contains(header)){
//                image=imageArr[1];
//                BASE64Decoder decoder = new BASE64Decoder();
//                byte[] decodedBytes = decoder.decodeBuffer(image);
//                FileUtil.uploadFile(decodedBytes, filePath, fileName);
//                User user = getUser();
//                userRepository.setProfilePicture(savePath, user.getId());
//                user.setProfilePicture(savePath);
//                getSession().setAttribute(Const.LOGIN_SESSION_KEY, user);
//            }
//            logger.info("头像地址：" + savePath);
//            logger.info("执行 上传头像 结束");
//            return new ResponseData(ExceptionMsg.SUCCESS, savePath);
//        } catch (Exception e) {
//            logger.error("upload head portrait failed, ", e);
//            return new ResponseData(ExceptionMsg.FAILED);
//        }
//    }
//
//    /**
//     * 上传背景
//     * @param dataUrl
//     * @return
//     */
//    @RequestMapping(value = "/uploadBackground", method = RequestMethod.POST)
//    @LoggerManage(description="上传背景")
//    public ResponseData uploadBackground(String dataUrl){
//        try {
//            String filePath=staticUrl+fileBackgroundpicturesUrl;
//            String fileName=UUID.randomUUID().toString()+".png";
//            String savePath = fileBackgroundpicturesUrl+fileName;
//            String image = dataUrl;
//            String header ="data:image";
//            String[] imageArr=image.split(",");
//            if(imageArr[0].contains(header)){
//                image=imageArr[1];
//                BASE64Decoder decoder = new BASE64Decoder();
//                byte[] decodedBytes = decoder.decodeBuffer(image);
//                FileUtil.uploadFile(decodedBytes, filePath, fileName);
//                User user = getUser();
//                userRepository.setBackgroundPicture(savePath, user.getId());
//                user.setBackgroundPicture(savePath);
//                getSession().setAttribute(Const.LOGIN_SESSION_KEY, user);
//            }
//            logger.info("背景地址：" + savePath);
//            return new ResponseData(ExceptionMsg.SUCCESS, savePath);
//        } catch (Exception e) {
//            logger.error("upload background picture failed, ", e);
//            return new ResponseData(ExceptionMsg.FAILED);
//        }
//    }
//

}
