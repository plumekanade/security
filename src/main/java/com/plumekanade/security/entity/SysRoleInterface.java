package com.plumekanade.security.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 角色与接口路径关联Entity
 *
 * @author kanade
 * @date 2020-07-07 17:11
 */
@Data
@TableName("sys_role_interface")
public class SysRoleInterface implements Serializable {

    private static final long serialVersionUID = -4296030993352708110L;
    /**
     * 主键id
     */
    @TableId
    private Long id;
    /**
     * 角色id
     */
    private Long sysRoleId;
    /**
     * 接口路径id
     */
    private Long sysInterfaceId;
}
