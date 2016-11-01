package cn.sh.outer.service.impl;

import cn.sh.outer.dao.ResourceMenuDao;
import cn.sh.outer.dao.RoleDao;
import cn.sh.outer.dao.RoleMenuDao;
import cn.sh.outer.model.req.MenuInfo;
import cn.sh.outer.model.req.RoleInfo;
import cn.sh.outer.model.security.RoleMenu;
import cn.sh.outer.service.ResourceMenuService;
import cn.sh.outer.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by HP on 2016/10/24.
 */
@Service("resourceMenuService")
public class ResourceMenuServiceImpl implements ResourceMenuService{

    @Autowired
    private ResourceMenuDao resourceMenuDao;

    @Autowired
    private RoleMenuDao roleMenuDao;

    @Override
    public void createResourceMenu(MenuInfo menuInfo) {
        resourceMenuDao.insert(menuInfo);
        RoleMenu rm = new RoleMenu();
        rm.setMenuId(menuInfo.getId());
        rm.setRoleId(menuInfo.getRoleId());
        roleMenuDao.insert(rm);
    }
}
