package com.yzq.springclouddemo.util;

import com.alibaba.fastjson.JSON;
import com.yzq.springclouddemo.constant.CommonConstant;
import com.yzq.springclouddemo.vo.LoginUserInfo;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import sun.misc.BASE64Decoder;

import java.io.IOException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Calendar;

public class TokenParseUtil {

    public static LoginUserInfo parseUserInfoFromToken(String token) throws NoSuchAlgorithmException, IOException, InvalidKeySpecException {
        if(null == token){
            return null;
        }

        Jws<Claims>  claimsJws = parseToken(token,getPublicKey());
        Claims body = claimsJws.getBody();
        if(body.getExpiration().before(Calendar.getInstance().getTime())){
            return null;
        }

        //没有过期的话则返回token中的用户信息
        return JSON.parseObject(body.get(CommonConstant.JWT_USER_INFO_KEY).toString(),LoginUserInfo.class);


    }

    /**
     * 通过公钥解析jwttoken
     */
    private static Jws<Claims>  parseToken(String token,PublicKey publicKey){
        return Jwts.parser().setSigningKey(publicKey).parseClaimsJws(token);
    }
    /**
     * 根据本地存贮的公钥获取公钥对象
     * @return
     */
    private static PublicKey getPublicKey() throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(new BASE64Decoder().decodeBuffer(CommonConstant.PUBLIC_KEY));
        return KeyFactory.getInstance("RSA").generatePublic(keySpec);

    }


}
