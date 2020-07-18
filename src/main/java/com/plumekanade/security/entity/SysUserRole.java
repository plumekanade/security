package com.plumekanade.security.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 系统用户与角色关联Entity
 *
 * @author kanade
 * @date 2020-06-23 10:12
 */
@Data
@TableName("sys_user_role")
public class SysUserRole implements Serializable {

    private static final long serialVersionUID = 994641608028447156L;
    /**
     * 主键id
     */
    @TableId
    private Long id;
    /**
     * 系统用户id
     */
    private Long sysUserId;
    /**
     * 系统角色id
     */
    private Long sysRoleId;
}
