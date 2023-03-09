package com.yzq.springclouddemo.service.Impl;

import cn.hutool.core.codec.Base64Decoder;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yzq.springclouddemo.constant.AuthorityConstant;
import com.yzq.springclouddemo.constant.CommonConstant;
import com.yzq.springclouddemo.dao.UserMapper;
import com.yzq.springclouddemo.entity.User;
import com.yzq.springclouddemo.service.IJWTService;
import com.yzq.springclouddemo.vo.LoginUserInfo;
import com.yzq.springclouddemo.vo.UsernameAndPassword;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.misc.BASE64Decoder;


import java.io.IOException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class IJWTServiceImpl implements IJWTService {
    @Autowired
    private UserMapper userMapper;


    @Override
    public String generateToken(String username, String password) throws Exception {
        return generateToken(username,password,0);
    }

    @Override
    public String generateToken(String username, String password, int expire) throws Exception {
        User user = userMapper.selectOne(new QueryWrapper<User>().eq("username",username)
                .eq("password",password)
        );
        //判断用户是否存在
        if(null == user){
            log.error("用户不存在or用户名或密码错误用户名:{},密码:{}",username,password);
            return null;
        }
        //将用户信息塞到
        LoginUserInfo loginUserInfo = new LoginUserInfo(user.getId(),user.getUsername());

        if(expire<=0){
            expire = AuthorityConstant.DEFAULT_EXPIRE_DAY;
        }
        //计算超时时间
        ZonedDateTime zdt = LocalDate.now().plus(expire, ChronoUnit.DAYS)
                .atStartOfDay(ZoneId.systemDefault());
        Date expireTime = Date.from(zdt.toInstant());


        return Jwts.builder()
                //jwt payload ky
                .claim(CommonConstant.JWT_USER_INFO_KEY, JSON.toJSONString(loginUserInfo))
                //jwt id
                .setId(UUID.randomUUID().toString())
                //jwt 过期时间
                .setExpiration(expireTime)
                //签名密钥及算法
                .signWith(getPrivateKey(), SignatureAlgorithm.RS256)
                .compact();
    }

    @Override
    public String registerUserAndGenerateToken(UsernameAndPassword usernameAndPassword) throws Exception {
        //校验用户名是否存在，存在不可以重复注册
        User oldUser = userMapper.selectOne(new QueryWrapper<User>().eq("username",usernameAndPassword.getUsername()));
        if(null!= oldUser){
            log.error("用户名已经被注册 {}",usernameAndPassword.getUsername());
            return null;
        }
        User user = new User();
        user.setPassword(usernameAndPassword.getPassword());
        user.setUsername(usernameAndPassword.getUsername());
        user.setExtraInfo("{}");
        userMapper.insert(user);

        log.info("用户注册成功用户信息：{}",JSON.toJSONString(user));
        return generateToken(usernameAndPassword.getUsername(),usernameAndPassword.getPassword());
    }

    /**
     * 根据本地的私钥串生成私钥对象
     * @return
     * @throws IOException
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     */
    private PrivateKey getPrivateKey() throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        PKCS8EncodedKeySpec pkcs8EncodedKeySpec =  new PKCS8EncodedKeySpec(
                new BASE64Decoder().decodeBuffer(AuthorityConstant.PRIVATE_KEY)
        );
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePrivate(pkcs8EncodedKeySpec);


    }
}
