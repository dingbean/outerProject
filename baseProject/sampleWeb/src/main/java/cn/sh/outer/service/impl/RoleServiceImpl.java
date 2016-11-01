package cn.sh.outer.service.impl;

import cn.sh.outer.dao.RoleDao;
import cn.sh.outer.model.req.RoleInfo;
import cn.sh.outer.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by HP on 2016/10/24.
 */
@Service("roleService")
public class RoleServiceImpl implements RoleService{

    @Autowired
    private RoleDao roleDao;

    @Override
    public void createRole(RoleInfo role) {
        roleDao.insert(role);
    }
}
