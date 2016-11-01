package cn.sh.outer.dao;

import cn.sh.outer.model.req.MenuInfo;
import cn.sh.outer.model.req.RoleInfo;
import cn.sh.outer.model.security.ResourceMenu;
import cn.sh.outer.model.security.Role;

import java.io.Serializable;


public interface ResourceMenuDao extends GenericDao<ResourceMenu, Serializable>{

	void deleteUserRole(Serializable id);

	int insert(MenuInfo menuInfo);
}