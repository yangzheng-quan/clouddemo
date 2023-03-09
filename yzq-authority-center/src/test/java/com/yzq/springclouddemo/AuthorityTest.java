package com.yzq.springclouddemo;

import cn.hutool.crypto.digest.MD5;
import com.yzq.springclouddemo.dao.UserMapper;
import com.yzq.springclouddemo.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AuthorityTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void play(){
        User user  = new User();
        user.setUsername("yzq");
        user.setPassword(MD5.create().digestHex("123456"));
        userMapper.insert(user);
    }
}
