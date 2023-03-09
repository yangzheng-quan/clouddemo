package com.yzq.springclouddemo.controller;

import com.yzq.springclouddemo.annotation.IgnoreResponseAdvice;
import com.yzq.springclouddemo.service.IJWTService;
import com.yzq.springclouddemo.vo.JWTToken;
import com.yzq.springclouddemo.vo.UsernameAndPassword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authority")
public class AuthorityController {

    @Autowired
    private IJWTService ijwtService;

    @IgnoreResponseAdvice
    @PostMapping("/token")
    public JWTToken token(@RequestBody UsernameAndPassword usernameAndPassword) throws Exception {
        return new JWTToken(ijwtService.generateToken(usernameAndPassword.getUsername(),
                usernameAndPassword.getPassword()));
    }

    @IgnoreResponseAdvice
    @PostMapping("/register")
    public JWTToken register(@RequestBody UsernameAndPassword usernameAndPassword) throws Exception {
        return new JWTToken(ijwtService.registerUserAndGenerateToken(usernameAndPassword));
    }
}
