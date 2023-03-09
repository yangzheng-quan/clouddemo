package com.yzq.springclouddemo.service;

import com.yzq.springclouddemo.vo.UsernameAndPassword;

/**
 * jwt相关接口定义
 */
public interface IJWTService {

    /**
     * 生成token使用默认的token过期时间
     * @param username
     * @param password
     * @return
     */
    String generateToken(String username,String password) throws Exception;


    /**
     * 生成token指定过期时间
     * @param username
     * @param password
     * @param expire
     * @return
     * @throws Exception
     */
    String generateToken(String username,String password,int expire) throws Exception;


    /**
     * 用户注册并生成token返回
     * @param usernameAndPassword
     * @return
     * @throws Exception
     */
    String registerUserAndGenerateToken(UsernameAndPassword usernameAndPassword) throws Exception;
}
