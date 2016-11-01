package cn.sh.outer.service;

import cn.sh.outer.model.User;
import cn.sh.outer.model.security.ResourceMenu;
import cn.sh.outer.model.security.Role;
import org.apache.shiro.session.Session;

import java.util.Collection;
import java.util.List;

/**
 * SystemService
 *  系统管理（权限）
 * @author DK
 * @date 2016/10/9
 */
public interface SystemService {
    /**
     * 根据用户ID获取菜单资源列表
     * @param userId
     * @return
     */
    public List<ResourceMenu> getMenuList(String userId);

    /**
     * 获取菜单资源列表
     * @param resourceMenu
     * @return
     */
    public List<ResourceMenu> getMenuList(ResourceMenu resourceMenu);

    /**
     * 根据用户ID获取角色列表
     * @param userId
     * @return
     */
    public List<Role> getRoleList(String userId);
    /**
     * 删除 会话session
     * @param session
     */
    public void deleteSession(Session session);

    /**
     * 根据用户名获取用户
     * @param username
     * @return
     */
    public User getUserByLoginName(String username);

    /**
     * 查询活动会话
     * @param includeLeave
     * @return
     */
    public Collection<Session> getActiveSessions(boolean includeLeave);
    /**
     * 获取活动会话
     * @param includeLeave 是否包括离线（最后访问时间大于3分钟为离线会话）
     * @param principal 根据登录者对象获取活动会话
     * @param filterSession 不为空，则过滤掉（不包含）这个会话。
     * @return
     */
    public Collection<Session> getActiveSessions(boolean includeLeave, Object principal, Session filterSession);
}
