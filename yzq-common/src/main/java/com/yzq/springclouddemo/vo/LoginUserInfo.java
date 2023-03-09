package com.yzq.springclouddemo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginUserInfo {

    /**
     * 用户id
     */
    private Long id;

    /**
     * 用户名（冗余设计不要再次查询库）
     */
    private String username;
}
