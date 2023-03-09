package com.yzq.springclouddemo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户表
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "t_user")
public class User implements Serializable {
    private Long id;

    private String username;

    private String password;

    /**
     * json字符串，用户信息的拓展
     */
    private String extraInfo;

    private Date createTime;

    private Date updateTime;
}
