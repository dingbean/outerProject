package cn.sh.outer.dao;

import cn.sh.outer.model.req.RoleInfo;
import cn.sh.outer.model.security.Role;

import java.io.Serializable;


public interface RoleDao extends GenericDao<Role, Serializable>{

	void deleteUserRole(Serializable id);

	int insert(RoleInfo role);
}