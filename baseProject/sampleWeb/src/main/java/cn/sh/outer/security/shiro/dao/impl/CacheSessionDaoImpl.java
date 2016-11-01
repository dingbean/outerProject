package cn.sh.outer.security.shiro.dao.impl;

import cn.sh.outer.security.shiro.dao.SessionDao;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.support.DefaultSubjectContext;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * CacheSessionDAO
 * shrio缓存 会话操作的dao
 * @author dk
 * @date 2016/10/10
 */
public class CacheSessionDaoImpl extends EnterpriseCacheSessionDAO implements SessionDao {
    @Override
    protected Serializable doCreate(Session session) {
        super.doCreate(session);
        //logger.debug("doCreate {} {}", session, request != null ? request.getRequestURI() : "");
        return session.getId();
    }

    @Override
    protected Session doReadSession(Serializable sessionId) {
        return super.doReadSession(sessionId);
    }

    @Override
    protected void doUpdate(Session session) {
        if (session == null || session.getId() == null) {
            return;
        }

       /* HttpServletRequest request = Servlets.getRequest();
        if (request != null){
            String uri = request.getServletPath();
            // 如果是静态文件，则不更新SESSION
            if (Servlets.isStaticFile(uri)){
                return;
            }
            // 如果是视图文件，则不更新SESSION
            if (StringUtils.startsWith(uri, Global.getConfig("web.view.prefix"))
                    && StringUtils.endsWith(uri, Global.getConfig("web.view.suffix"))){
                return;
            }
            // 手动控制不更新SESSION
            String updateSession = request.getParameter("updateSession");
            if (Global.FALSE.equals(updateSession) || Global.NO.equals(updateSession)){
                return;
            }
        }*/
        super.doUpdate(session);
      //  logger.debug("update {} {}", session.getId(), request != null ? request.getRequestURI() : "");
    }

    @Override
    protected void doDelete(Session session) {
        if (session == null || session.getId() == null) {
            return;
        }

        super.doDelete(session);
    }

    @Override
    public Collection<Session> getActiveSessions(boolean includeLeave) {
        return getActiveSessions(includeLeave,null,null);
    }

    @Override
    public Collection<Session> getActiveSessions(boolean includeLeave, Object principal, Session filterSession) {
        // 如果包括离线，并无登录者条件。
        if (includeLeave && principal == null){
            return getActiveSessions();
        }
        Set<Session> sessions = new HashSet<Session>();
        for (Session session : getActiveSessions()){
            boolean isActiveSession = false;
            long t = new Date().getTime()-session.getLastAccessTime().getTime();
            // 不包括离线并符合最后访问时间小于等于3分钟条件。
            if (includeLeave || t/(60*1000) <= 3){
                isActiveSession = true;
            }
            // 符合登陆者条件。
            if (principal != null){
                PrincipalCollection pc = (PrincipalCollection)session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
                if (principal.toString().equals(pc != null ? pc.getPrimaryPrincipal().toString() : StringUtils.EMPTY)){
                    isActiveSession = true;
                }
            }
            // 过滤掉的SESSION
            if (filterSession != null && filterSession.getId().equals(session.getId())){
                isActiveSession = false;
            }
            if (isActiveSession){
                sessions.add(session);
            }
        }
        return sessions;
    }
}
