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
     * @param menu
     */
    public void insetMenu(ResourceMenu menu);

    /**
     * 更新菜单
     * @param menu
     */
    public void updateMenu(ResourceMenu menu);

    /**
     * 获取菜单
     * @param menu
     * @return
     */
    public ResourceMenu getMenu(ResourceMenu menu);

    /**
     * 删除菜单
     * @param menu
     */
    public void deleteMenu(ResourceMenu menu);

    /**
     * 批量添加角色菜单关联
     * @param roleMap
     */
    public void batchInsertRoleMenu(Map<String, Object> roleMap);

    /**
     * 插入角色菜单关联
     * @param roleMenu
     */
    public void insertRoleMenu(RoleMenu roleMenu);

    /**
     * 删除角色菜单关联
     * @param roleMenu
     */
    public void deleteRoleMenu(RoleMenu roleMenu);

    /**
     * 获取菜单列表
     * @param menu
     * @return
     */
    public List<ResourceMenu> getMenuList(ResourceMenu menu);
    /**
     * 获取级联菜单列表（包含父子关系）
     * @param menu
     * @return
     */
    public List<ResourceMenu> getCascadeMenuList(ResourceMenu menu);

    /**
     * 根据用户id获取菜单列表
     * @param userId
     * @return
     */
    public List<ResourceMenu> getMenuListByUserId(String userId);

    /**
     * 根据用户id获取角色列表
     * @param userId
     * @return
     */
    public List<Role> getRoleListByUserId(String userId);
}
