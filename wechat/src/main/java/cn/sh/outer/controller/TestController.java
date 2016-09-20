package cn.sh.outer.controller;

import cn.sh.outer.model.QQBean;
import cn.sh.outer.service.QQService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by Administrator on 2016/9/18.
 */
@Controller
@RequestMapping("/test")
public class TestController {

    private static Logger log = LoggerFactory.getLogger(TestController.class);

    @Resource(name = "qqService")
    private QQService qqService;

    @RequestMapping(value = "/controller", method= RequestMethod.GET)
    @ResponseBody
    public void testController(HttpServletRequest request){
        log.info("============================");
        System.out.println("###################111");
        QQBean qq = new QQBean();
        qq.setId(22l);
        qq.setLoginUsername("test");
        qq.setSystemId("123456");
        try {
            qqService.insert(qq);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
