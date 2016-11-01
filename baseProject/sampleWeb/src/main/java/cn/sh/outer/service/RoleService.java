package cn.sh.outer.service;

import cn.sh.outer.model.User;
import cn.sh.outer.model.req.RoleInfo;
import cn.sh.outer.model.req.UserInfo;

import java.util.List;

/**
 * Created by HP on 2016/10/23.
 */
public interface RoleService {

    /**
     * 新增角色
     * @param role
     */
    public void createRole(RoleInfo role);

}
