package com.yzq.springclouddemo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsernameAndPassword {

    private String username;

    private String password;

    private JWTToken jwtToken;

    private LoginUserInfo loginUserInfo;
}
