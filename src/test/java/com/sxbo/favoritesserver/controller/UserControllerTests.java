package com.sxbo.favoritesserver.controller;

import com.sxbo.favoritesserver.domain.User;
import org.json.JSONObject;
import org.json.JSONString;
import org.json.JSONStringer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashMap;

/**
 * @Author xiaobo GG [https://github.com/sxbo]
 * @Date 2017/9/617:16
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class UserControllerTests {
    private MockMvc cli;

    @Before
    public void setUp () throws Exception{
        cli = MockMvcBuilders.standaloneSetup(new UserController()).build();
    }

    @Test
    public void registTest() throws Exception {
//        User user = new User();
//        user.setUserName("xiaobo");
//        user.setPassWord("lkj");
//        user.setEmail("xiaobo@123.com");
//
//
//        JSONObject jsonObject = new JSONObject("{'userName':'xiaobo',passWord':'123','email':'xiao@123.com'}");
//        System.out.print(jsonObject.toString());
//
//        cli.perform(MockMvcRequestBuilders.post("/user/regist")
//                    .contentType(MediaType.APPLICATION_JSON_UTF8)
//                    .param("userName","xiaobo").param("passWord","123").param("email","xiao@123.com")
//                    .accept(MediaType.APPLICATION_JSON_UTF8)
//        ).andExpect(MockMvcResultMatchers.status().isOk())
//                .andDo(MockMvcResultHandlers.print())
//                .andReturn();
    }

}
