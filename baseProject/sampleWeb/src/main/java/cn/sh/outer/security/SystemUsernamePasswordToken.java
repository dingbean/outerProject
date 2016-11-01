package cn.sh.outer.security;

import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * SystemUsernamePasswordToken
 * 自定义token
 * 用户和密码（包含验证码）令牌类
 * @author dk
 * @date 2016/10/9
 */
public class SystemUsernamePasswordToken extends UsernamePasswordToken {
    
    public SystemUsernamePasswordToken(String username, char[] password, boolean rememberMe, String host){
        super(username,password,rememberMe, host);
    }



}
