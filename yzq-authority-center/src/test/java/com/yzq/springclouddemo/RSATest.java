package com.yzq.springclouddemo;

import cn.hutool.core.codec.Base64;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class RSATest {

    @Test
    public void generateKeyByte() throws NoSuchAlgorithmException {

        /**
         * 选取算法种类
         */
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        //最小字节数
        keyPairGenerator.initialize(2048);
        //实例化产生公私钥对的对象
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        //生成公钥河私钥
        RSAPublicKey publicKey = (RSAPublicKey)keyPair.getPublic();
        RSAPrivateKey privateKey = (RSAPrivateKey)keyPair.getPrivate();

        log.info("私钥: [{}]", Base64.encode(privateKey.getEncoded()));

        log.info("公钥: [{}]", Base64.encode(publicKey.getEncoded()));


    }
}
