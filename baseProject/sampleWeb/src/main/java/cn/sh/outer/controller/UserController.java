package cn.sh.outer.controller;

import cn.sh.outer.model.User;
import cn.sh.outer.model.req.UserInfo;
import cn.sh.outer.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by Administrator on 2016/9/20.
 */
@Controller
@RequestMapping("/user")
public class UserController extends BaseController {

    private static Logger log = LoggerFactory.getLogger(UserController.class);

    @Resource(name="userService")
    private UserService userService;

    @RequestMapping(value = "/createUser",method= RequestMethod.POST,produces={ "application/json;charset=UTF-8" })
    @ResponseBody
    public Map<String,Object> createUser(@RequestBody UserInfo userInfo, HttpServletRequest request){
//        System.out.println("idNum=" + IdGenerate.getIdNum());
        try{
            log.info("#######################1111" + userInfo.toString());
            userInfo.setStatus("1");
            userInfo.setGender("1");
            userService.createUser(userInfo);
            return getSuccMap();
        }catch(Exception ex){
            ex.printStackTrace();
            return getErrorMap(ex.getMessage());
        }

    }

}
