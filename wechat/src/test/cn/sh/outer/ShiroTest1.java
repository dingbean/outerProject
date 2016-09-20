
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Assert;
import org.junit.Test;


/**
 * Created by Administrator on 2016/9/14.
 */
public class ShiroTest1 {
    @Test
    public void test1(){
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:conf/shiro-realm.ini");
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        //3、得到Subject及创建用户名/密码身份验证Token（即用户身份/凭证）
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("wang", "123");

        try {
            //4、登录，即身份验证
            subject.login(token);
        } catch (AuthenticationException e) {
            //5、身份验证失败
        }
        System.out.println(subject.getPrincipal().toString());
        Assert.assertEquals(true, subject.isAuthenticated()); //断言用户已经登录

        //6、退出
        subject.logout();
    }

    @Test
    public void test2(){
        System.out.println("222222");
    }
}
