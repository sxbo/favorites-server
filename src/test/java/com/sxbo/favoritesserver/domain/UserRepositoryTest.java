package com.sxbo.favoritesserver.domain;

import com.sxbo.favoritesserver.repository.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author xiaobo GG [https://github.com/sxbo]
 * @Date 2017/9/614:59
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void test() throws  Exception {
//        User user = new User("aa@126.com", "aa", "aa", "aa123456");
//        user.setCreateTime(System.currentTimeMillis());
//        user.setLastModifyTime(System.currentTimeMillis());
//        userRepository.save(user);
//        //userRepository.save(new User("bb@126.com", "bb", "bb", "bb123456"));
//        //userRepository.save(new User("cc@126.com", "cc", "cc", "cc123456"));
//
//        Assert.assertEquals(1, userRepository.findAll().size());
//
//        Assert.assertEquals("aa", userRepository.findByUserName("aa").getUserName());
//
//		/*Assert.assertEquals(60, userRepository.findUser("FFF").getAge().longValue());
//
//		Assert.assertEquals("FFF", userRepository.findByNameAndAge("FFF", 60).getName());
//
//		userRepository.delete(userRepository.findByName("AAA"));
//
//		Assert.assertEquals(9, userRepository.findAll().size());
    }

}
