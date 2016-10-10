package cn.sh.outer.security;

import cn.sh.outer.model.Constant;
import cn.sh.outer.model.User;
import cn.sh.outer.model.security.ResourceMenu;
import cn.sh.outer.model.security.Role;
import cn.sh.outer.service.SystemService;
import cn.sh.outer.util.Encodes;
import cn.sh.outer.util.UserUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

/**
 * SystemAuthorizingRealm
 * 系统安全认证实现类
 *
 * @author dk
 * @date 2016/10/10
 */
@Service("systemAuthorizingRealm")
public class SystemAuthorizingRealm extends AuthorizingRealm {
    private Logger logger = LoggerFactory.getLogger(getClass());
    /*加密方式*/
    public static final String HASH_ALGORITHM = "SHA-1";
    public static final int HASH_INTERATIONS = 1024;
    @Value("#{config['multiAccountLogin']}")
    private String multiAccountLogin;
    @Autowired
    private SystemService systemService;
    @Autowired
    private DataSource dataSource;
    @PostConstruct
    public void initSystemInfo(){
        try {
            Connection conn = dataSource.getConnection();
            ScriptRunner runner = new ScriptRunner(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
        }
    }

    /**
     * 认证回调，登录时调用
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        SystemUsernamePasswordToken token = (SystemUsernamePasswordToken) authenticationToken;
        //获取所有活动的会话数量
        int activeSessionSize = systemService.getActiveSessions(false).size();
        logger.info("login submit, active session size: {}, username: {}", activeSessionSize, token.getUsername());


        String username = token.getUsername();
        //根据用户名获取用户信息
        User user = systemService.getUserByLoginName(username);
        //校验用户名
        if (user != null) {
            if (Constant.NO.equals(user.getStatus())) {
                throw new AuthenticationException("msg:该已帐号禁止登录.");
            }
            UserUtil.getSession().setAttribute("username",user.getUserAccount());
            //对密码加密加盐处理
            byte[] salt = Encodes.decodeHex(user.getPassword().substring(0, 16));
            return new SimpleAuthenticationInfo(new Principal(user), user.getPassword().substring(16), ByteSource.Util.bytes(salt), getName());
        } else {
            return null;
        }


    }

    /**
     * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        Principal principal = (Principal) getAvailablePrincipal(principalCollection);
        // 是否允许多处登录
        if (!Constant.TRUE.equals(multiAccountLogin)) {
            Collection<Session> sessions = systemService.getActiveSessions(true, principal, UserUtil.getSession());
            if (sessions.size() > 0) {
                // 如果是登录进来的，则踢出已在线用户
                if (UserUtil.getSubject().isAuthenticated()) {
                    for (Session session : sessions) {
                        systemService.deleteSession(session);
                    }
                }
                // 记住我进来的，并且当前用户已登录，则退出当前用户提示信息。
                else {
                    UserUtil.getSubject().logout();
                    throw new AuthenticationException("msg:账号已在其它地方登录，请重新登录。");
                }
            }
        }
        User user = systemService.getUserByLoginName(principal.getLoginName());
        if (user != null) {
            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
            List<ResourceMenu> list = systemService.getMenuList(user.getId());
            for (ResourceMenu menu : list) {
                if (StringUtils.isNotBlank(menu.getPermission())) {
                    // 添加基于Permission的权限信息
                    for (String permission : StringUtils.split(menu.getPermission(), ",")) {
                        info.addStringPermission(permission);
                    }
                }
            }
            UserUtil.getSession().setAttribute("menuList",list);
            // 添加用户权限
           // info.addStringPermission("user");
            List<Role> roleList = systemService.getRoleList(user.getId());
            if (roleList != null) {
                // 添加用户角色信息
                for (Role role : roleList) {
                    info.addRole(role.getRoleName());
                }
            }

            // 更新登录IP和时间
            //   getSystemService().updateUserLoginInfo(user);
            // 记录登录日志
            //    LogUtils.saveLog(Servlets.getRequest(), "系统登录");
            return info;
        } else {
            return null;
        }
    }

    /**
     * 设定密码校验的Hash算法与迭代次数
     */
    @PostConstruct
    public void initCredentialsMatcher() {

        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher(HASH_ALGORITHM);
        matcher.setHashIterations(HASH_INTERATIONS);
        matcher.setStoredCredentialsHexEncoded(true);
        setCredentialsMatcher(matcher);
    }

    /**
     * 授权用户信息
     */
    public static class Principal implements Serializable {

        private static final long serialVersionUID = 1L;

        private String id; // 编号
        private String loginName; // 登录名
        private String name; // 姓名

//		private Map<String, Object> cacheMap;

        public Principal(User user) {
            this.id = user.getId();
            this.loginName = user.getUserAccount();
            this.name = user.getName();
        }

        public String getId() {
            return id;
        }

        public String getLoginName() {
            return loginName;
        }

        public String getName() {
            return name;
        }



//		@JsonIgnore
//		public Map<String, Object> getCacheMap() {
//			if (cacheMap==null){
//				cacheMap = new HashMap<String, Object>();
//			}
//			return cacheMap;
//		}

        /**
         * 获取SESSIONID
         */
        public String getSessionid() {
            try {
                return "";
            } catch (Exception e) {
                return "";
            }
        }

        @Override
        public String toString() {
            return id;
        }

    }
}
