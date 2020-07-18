package com.plumekanade.security.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.plumekanade.security.entity.SysRole;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author  kanade
 * @date  2020-07-03 15:23
 */
@Repository
public interface ISysRoleMapper extends BaseMapper<SysRole> {

    /**
     * 根据用户id查找对应的角色id列表
     */
    @Select("select ur.sys_role_id from sys_user_role ur where ur.sys_user_id = #{userId}")
    List<Long> roleIdsByUserId(@Param("userId") Long userId);

    /**
     * 根据请求地址url查找对应的角色列表
     */
    @Select("select r.name from sys_role r, sys_role_interface ri, sys_interface i " +
            "where i.url = #{url} and i.id = ri.sys_interface_id and r.id = ri.sys_role_id")
    List<String> rolesByUrl(@Param("url") String url);

    /**
     * 根据用户id查找角色列表
     */
    @Select("select r.name from sys_role r,sys_user_role ur where ur.sys_user_id = #{userId} and r.id = ur.sys_role_id")
    List<String> rolesByUserId(@Param("userId") Long userId);
}
