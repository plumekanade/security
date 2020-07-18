package com.plumekanade.security.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.Data;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import static com.plumekanade.security.constant.ProjectConstant.DATE_TIME_PATTERN;
import static com.plumekanade.security.constant.ProjectConstant.TIME_ZONE;

/**
 * 系统用户Entity
 *
 * @author kanade
 * @date 2020-06-22 11:30
 */
@Data
@TableName("sys_user")
public class SysUser implements Serializable {

    private static final long serialVersionUID = -8701998655233593109L;

    /**
     * 主键id
     */
    @TableId
    private Long id;
    /**
     * 用户名(手机号)
     */
    private String username;
    /**
     * 加盐密码
     */
    private String password;
    /**
     * 用户名称
     */
    private String name;
    /**
     * 用户状态 1启用 2停用
     */
    private Integer userStatus;
    /**
     * 预留状态 1正常 2锁定
     */
    private Integer lockStatus;
    /**
     * 最后登录时间
     */
    @JsonFormat(pattern = DATE_TIME_PATTERN, timezone = TIME_ZONE)
    private Date loginTime;
    /**
     * 创建时间
     */
    @JsonFormat(pattern = DATE_TIME_PATTERN, timezone = TIME_ZONE)
    private Date createTime;
    /**
     * 创建人
     */
    private String createBy;
    /**
     * 更新时间
     */
    @JsonFormat(pattern = DATE_TIME_PATTERN, timezone = TIME_ZONE)
    private Date updateTime;
    /**
     * 更新人
     */
    private String updateBy;
    /**
     * 用户拥有的角色列表
     */
    @TableField(exist = false)
    private List<SimpleGrantedAuthority> authorities;

    @JsonIgnore
    public String getPassword() {
        return password;
    }

    @JsonProperty
    public void setPassword(String password) {
        this.password = password;
    }
}
