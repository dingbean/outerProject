package cn.sh.outer.controller;

import cn.sh.outer.model.req.MenuInfo;
import cn.sh.outer.model.req.RoleInfo;
import cn.sh.outer.model.req.UserInfo;
import cn.sh.outer.service.ResourceMenuService;
import cn.sh.outer.service.RoleService;
import cn.sh.outer.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@RequestMapping("/role")
public class RoleController extends BaseController {

    private static Logger log = LoggerFactory.getLogger(RoleController.class);

    @Resource(name="userService")
    private UserService userService;

    @Resource(name="roleService")
    private RoleService roleService;

    @Resource(name="resourceMenuService")
    private ResourceMenuService resourceMenuService;

    @RequestMapping(value = "/createRole",method= RequestMethod.POST,produces={ "application/json;charset=UTF-8" })
    @ResponseBody
    public Map<String,Object> createRole(@RequestBody RoleInfo roleInfo, HttpServletRequest request){
//        System.out.println("idNum=" + IdGenerate.getIdNum());
        try{
            log.info("#######################createRole=" + roleInfo.toString());
            roleInfo.setStatus("1");
            roleService.createRole(roleInfo);
            return getSuccMap();
        }catch(Exception ex){
            ex.printStackTrace();
            return getErrorMap(ex.getMessage());
        }
    }

    @RequestMapping(value = "/createMenu",method= RequestMethod.POST,produces={ "application/json;charset=UTF-8" })
    @ResponseBody
    public Map<String,Object> createMenu(@RequestBody MenuInfo menuInfo, HttpServletRequest request){
//        System.out.println("idNum=" + IdGenerate.getIdNum());
        try{
            log.info("#######################createMenu=" + menuInfo.toString());
            menuInfo.setStatus("1");
            resourceMenuService.createResourceMenu(menuInfo);
            return getSuccMap();
        }catch(Exception ex){
            ex.printStackTrace();
            return getErrorMap(ex.getMessage());
        }
    }
}
