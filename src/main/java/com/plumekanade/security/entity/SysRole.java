package com.plumekanade.security.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

import static com.plumekanade.security.constant.ProjectConstant.DATE_TIME_PATTERN;
import static com.plumekanade.security.constant.ProjectConstant.TIME_ZONE;

/**
 * 系统角色Entity
 *
 * @author kanade
 * @date 2020-06-22 16:03
 */
@Data
@TableName("sys_role")
public class SysRole implements Serializable {

    private static final long serialVersionUID = -1827516627881002851L;

    /**
     * 主键id
     */
    @TableId
    private Long id;
    /**
     * 角色名称
     */
    private String name;
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
}
