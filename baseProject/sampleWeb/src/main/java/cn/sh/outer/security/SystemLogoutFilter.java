package cn.sh.outer.security;

import cn.sh.outer.util.UserUtil;
import org.apache.shiro.web.filter.authc.LogoutFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * SystemLogoutFilter
 *
 * @author Genghc
 * @date 2015/9/11
 */
@Service("systemLogoutFilter")
public class SystemLogoutFilter extends LogoutFilter {
    private static final Logger logger = LoggerFactory.getLogger(SystemLogoutFilter.class);
    /*@Value("#{config['isCas']}")
    private String isCas ;
    @Value("#{config['casLoginoutUrl']}")
    private String casLoginoutUrl;*/
    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        logger.info("use WEBSSO logout....");
        UserUtil.getSubject().logout();
        return super.preHandle(request, response);
    }
}
