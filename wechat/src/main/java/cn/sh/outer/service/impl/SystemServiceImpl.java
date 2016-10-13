package cn.sh.outer.service.impl;

import cn.sh.outer.dao.RedisDao;
import cn.sh.outer.dao.RoleMenuDao;
import cn.sh.outer.dao.UserDao;
import cn.sh.outer.model.User;
import cn.sh.outer.model.security.ResourceMenu;
import cn.sh.outer.model.security.Role;
import cn.sh.outer.security.shiro.dao.SessionDao;
import cn.sh.outer.service.SystemService;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * SystemServiceImpl
 *
 * @author dk
 * @date 2016/10/9
 */
@Service("systemService")
public class SystemServiceImpl implements SystemService {
    @Autowired
    private UserDao userDao;
    @Resource(name="shiroSessionDao")
    private SessionDao sessionDao;
    @Autowired
    private RoleMenuDao roleMenuDao;
    private String sessionKeyPrefix = "shiro_session_";

    @Autowired
    private RedisDao redisDao;

    @Override
    public List<ResourceMenu> getMenuList(ResourceMenu resourceMenu) {
        return roleMenuDao.getMenuList(resourceMenu);
    }

    @Override
    public User getUserByLoginName(String username) {
        User u = new User();
        u.setUserAccount(username);
        List<User> users = userDao.query(u);
        if (users != null && users.size() > 0) {
            return users.get(0);
        }
        return null;
    }

    @Override
    public Collection<Session> getActiveSessions(boolean includeLeave) {
        return sessionDao.getActiveSessions(includeLeave);
    }

    @Override
    public Collection<Session> getActiveSessions(boolean includeLeave, Object principal, Session filterSession) {
      return sessionDao.getActiveSessions(includeLeave,principal,filterSession);
    }

    @Override
    public List<ResourceMenu> getMenuList(String userId) {
        return roleMenuDao.getMenuListByUserId(userId);
    }

    @Override
    public List<Role> getRoleList(String userId) {
        return roleMenuDao.getRoleListByUserId(userId);
    }

    @Override
    public void deleteSession(Session session) {
        sessionDao.delete(session);
    }

    public SessionDao getSessionDao() {
        return sessionDao;
    }

    public void setSessionDao(SessionDao sessionDao) {
        this.sessionDao = sessionDao;
    }
}
