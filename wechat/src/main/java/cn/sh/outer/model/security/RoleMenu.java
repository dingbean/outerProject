package cn.sh.outer.model.security;


/**
 * RoleMenu
 *
 * @author Genghc
 * @date 2015/7/13
 */
public class RoleMenu {
    private int roleId;
    private int menuId;
    private Role role;
    private ResourceMenu menu;

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public int getMenuId() {
        return menuId;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public ResourceMenu getMenu() {
        return menu;
    }

    public void setMenu(ResourceMenu menu) {
        this.menu = menu;
    }
}
