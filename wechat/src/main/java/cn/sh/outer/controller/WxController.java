package cn.sh.outer.controller;

import cn.sh.outer.util.IdGenerate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.IdGenerator;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Administrator on 2016/9/20.
 */
@Controller
@RequestMapping("/wx")
public class WxController {

    private static Logger log = LoggerFactory.getLogger(WxController.class);

    @RequestMapping(value = "/controller")
    @ResponseBody
    public void testController(HttpServletRequest request){
        System.out.println("idNum=" + IdGenerate.getIdNum());
    }
}
