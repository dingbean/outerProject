package cn.sh.outer.controller;

import cn.sh.outer.model.QQBean;
import cn.sh.outer.model.WxUser;
import cn.sh.outer.service.QQService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by Administrator on 2016/9/18.
 */
@Controller
@RequestMapping("/busi")
public class BusinessController extends BaseController{

    private static Logger log = LoggerFactory.getLogger(BusinessController.class);

    @RequestMapping(value = "/input", method= RequestMethod.POST, produces={ "application/json;charset=UTF-8" })
    @ResponseBody
    public void input(@RequestBody WxUser wxUser, HttpServletRequest request){
        log.info("============================" + wxUser.getUserName() + "___" + wxUser.getWxUserName());

    }
}
