package com.plumekanade.security.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 系统接口Entity
 *
 * @author kanade
 * @date 2020-07-07 16:52
 */
@Data
@TableName("sys_interface")
public class SysInterface implements Serializable {

    private static final long serialVersionUID = 2094170935681373256L;
    /**
     * 主键id
     */
    @TableId
    private Long id;
    /**
     * 父级id
     */
    private Long parentId;
    /**
     * 接口权限名称
     */
    private String pmsName;
    /**
     * 接口路径
     */
    private String url;
}
