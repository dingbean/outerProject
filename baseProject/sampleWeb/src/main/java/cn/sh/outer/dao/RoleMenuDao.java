package cn.sh.outer.dao;


import cn.sh.outer.model.security.ResourceMenu;
import cn.sh.outer.model.security.Role;
import cn.sh.outer.model.security.RoleMenu;

import java.util.List;
import java.util.Map;

/**
 * RoleMenuDao
 * 角色菜单关联dao(权限管理)
 * @author Genghc
 * @date 2015/7/13
 */
public interface RoleMenuDao {
    /**
     * 插入菜单
     * @param roleMenu
     */
    public void insert(RoleMenu roleMenu);


}
