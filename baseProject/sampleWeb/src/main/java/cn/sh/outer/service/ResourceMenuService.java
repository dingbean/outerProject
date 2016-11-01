package cn.sh.outer.service;

import cn.sh.outer.model.req.MenuInfo;
import cn.sh.outer.model.req.RoleInfo;

/**
 * Created by HP on 2016/10/23.
 */
public interface ResourceMenuService {

    /**
     * 新增权限
     * @param menuInfo
     */
    public void createResourceMenu(MenuInfo menuInfo);

}
