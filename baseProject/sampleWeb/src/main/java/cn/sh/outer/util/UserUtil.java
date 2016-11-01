package cn.sh.outer.util;

import org.apache.commons.lang3.Validate;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.UnavailableSecurityManagerException;
import org.apache.shiro.session.InvalidSessionException;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

import java.security.SecureRandom;


/**
 * UserUtil
 * shrio 关于用户的工具类
 *
 * @author dk
 * @date 2016/10/10
 */
public class UserUtil {
    public static final int SALT_SIZE = 8;
    private static SecureRandom random = new SecureRandom();

    /**
     * 加密 password+salt
     *  生成随机的16位salt并经过1024次 sha-1 hash
     * @param password
     * @return
     */
    /*public static String encryptPassword(String password) {
        byte[] salt = generateSalt(SALT_SIZE);
        byte[] hashPassword = Encodes.sha1(password.getBytes(), salt, SystemAuthorizingRealm.HASH_INTERATIONS);
        return Encodes.encodeHex(salt) + Encodes.encodeHex(hashPassword);
    }*/

    /**
     * 验证密码
     * @param plainPassword 明文密码
     * @param password 密文密码
     * @return 验证成功返回true
     */
    /*public static boolean validatePassword(String plainPassword, String password) {
        byte[] salt = Encodes.decodeHex(password.substring(0,16));
        byte[] hashPassword = Encodes.sha1(plainPassword.getBytes(), salt, SystemAuthorizingRealm.HASH_INTERATIONS);
        return password.equals(Encodes.encodeHex(salt)+Encodes.encodeHex(hashPassword));
    }*/

    /**
     * 生成随机的Byte[]作为salt.
     *
     * @param numBytes byte数组的大小
     */
    public static byte[] generateSalt(int numBytes) {
        Validate.isTrue(numBytes > 0, "numBytes argument must be a positive integer (1 or larger)", numBytes);

        byte[] bytes = new byte[numBytes];
        random.nextBytes(bytes);
        return bytes;
    }

    /**
     * 获取授权主要对象
     */
    public static Subject getSubject() {
        return SecurityUtils.getSubject();
    }

    /**
     * 获取当前登录者对象（身份信息）
     */
    /*public static SystemAuthorizingRealm.Principal getPrincipal() {
        try {
            Subject subject = SecurityUtils.getSubject();
            SystemAuthorizingRealm.Principal principal = (SystemAuthorizingRealm.Principal) subject.getPrincipal();
            if (principal != null) {
                return principal;
            }
        } catch (UnavailableSecurityManagerException e) {

        } catch (InvalidSessionException e) {

        }
        return null;
    }*/

    /**
     * 获取当前用户session
     *
     * @return
     */
    public static Session getSession() {
        try {
            Subject subject = SecurityUtils.getSubject();
            Session session = subject.getSession(false);
            if (session == null) {
                session = subject.getSession();
            }
            if (session != null) {
                return session;
            }
        } catch (InvalidSessionException e) {

        }
        return null;
    }



    public static void main(String[] args) {
        /*byte[] salt = generateSalt(SALT_SIZE);
        System.out.println("___" +Encodes.encodeHex(salt));
        String a = "123456";
        String p1 = UserUtil.encryptPassword(a);
        System.out.println(p1);
        System.out.println(UserUtil.validatePassword(a,"b1b8e9a691921f99a68b31b3dbd35bc5380f82f6ff9d285c3f6eebd0"));*/
    }

}
