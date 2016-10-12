/**
 * 
 */
package cn.sh.outer.boot;

import java.io.FileNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.Log4jConfigurer;


import com.google.common.util.concurrent.AbstractIdleService;


/**
 * @project MGW
 * @Package cn.sh.ideal.mgw.boot
 * @typeName Bootstrap
 * @author Stanley Zhou
 * @Description:  
 * @date 2016年4月8日 下午5:41:32
 * @version 
 */
public class Bootstrap extends AbstractIdleService {
	private static Logger log = LoggerFactory.getLogger(Bootstrap.class);
    private ClassPathXmlApplicationContext context;
    
    
    static {
		try {
			Log4jConfigurer.initLogging("classpath:conf/log4j.properties");
		} catch (FileNotFoundException ex) {
			System.err.println("Cannot Initialize log4j");
		}
	}

    public static void main(String[] args) {
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.startAsync();
        try {
            Object lock = new Object();
            synchronized (lock) {
                while (true) {
                    lock.wait();
                }
            }
        } catch (InterruptedException ex) {
            log.error("ignore interruption");
        }
    }

    @Override
    protected void startUp() throws Exception {
        context = new ClassPathXmlApplicationContext(new String[]{"spring/spring.xml"});
        context.start();
        context.registerShutdownHook();
        log.info("mgw service started successfully!");
    }

    @Override
    protected void shutDown() throws Exception {
        context.stop();
        log.info("mgw service stop successfully!");
    }
}

