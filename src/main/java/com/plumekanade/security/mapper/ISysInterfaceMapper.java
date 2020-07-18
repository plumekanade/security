package com.plumekanade.security.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.plumekanade.security.entity.SysInterface;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author  kanade
 * @date  2020-07-07 17:19
 */
@Repository
public interface ISysInterfaceMapper extends BaseMapper<SysInterface> {

    /**
     * 根据角色id查找路径
     */
    @Select("select i.url from sys_interface i, sys_role_interface ri " +
            "where ri.sys_role_id = #{roleId} and i.id = ri.sys_interface_id")
    List<String> pathByRoleId(@Param("roleId") Long roleId);
}
