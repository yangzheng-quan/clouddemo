package com.yzq.springclouddemo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 授权中心鉴权之后返回给客户端的token
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JWTToken {
    private String token;
}
