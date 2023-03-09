package com.yzq.springclouddemo;

import com.alibaba.fastjson.JSON;
import com.yzq.springclouddemo.service.IJWTService;
import com.yzq.springclouddemo.util.TokenParseUtil;
import com.yzq.springclouddemo.vo.LoginUserInfo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class jwtTest {

    @Autowired
    private IJWTService ijwtService;
    @Test
    public void play() throws Exception {
     String jwtToken =    ijwtService.generateToken("yzq","e10adc3949ba59abbe56e057f20f883e");
     log.info("生成的token为：{}",jwtToken);


        LoginUserInfo loginUserInfo = TokenParseUtil.parseUserInfoFromToken(jwtToken);
        log.info("用户信息为：{}", JSON.toJSONString(loginUserInfo));
    }

}
