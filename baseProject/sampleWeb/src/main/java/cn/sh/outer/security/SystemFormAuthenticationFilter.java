package cn.sh.outer.security;

import cn.sh.outer.util.RequestUtil;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.stereotype.Service;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * SystemFormAuthenticationFilter
 * 表单验证过滤器
 * @author Genghc
 * @date 2015/7/8
 */
@Service("systemFormAuthenticationFilter")
public class SystemFormAuthenticationFilter extends FormAuthenticationFilter{
    /*执行登录操作*/
    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
        return super.executeLogin(request, response);
    }

    /**
     * 创建自定义token
     * @param request
     * @param response
     * @return
     */
    protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) {
        String username = getUsername(request);
        String password = getPassword(request);
        if (password==null){
            password = "";
        }
        boolean rememberMe = isRememberMe(request);
        String host = RequestUtil.getRemoteAddr((HttpServletRequest) request);
        return new SystemUsernamePasswordToken(username,password.toCharArray(),rememberMe,host);

    }

    /**
     * 登录失败后调用事件
     * @param token
     * @param e
     * @param request
     * @param response
     * @return
     */
    @Override
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request, ServletResponse response) {
        return super.onLoginFailure(token, e, request, response);
    }

    /**
     * 登录成功之后跳转成功页面
     * @param request
     * @param response
     * @throws Exception
     */
    @Override
    protected void issueSuccessRedirect(ServletRequest request, ServletResponse response) throws Exception {
        //request.getSess
        WebUtils.issueRedirect(request, response, getSuccessUrl(), null, true);
    }
}
