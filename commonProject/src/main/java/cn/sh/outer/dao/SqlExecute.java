package cn.sh.outer.dao;

import java.util.ArrayList;
import java.util.List;

import cn.sh.outer.model.SysParam;
import cn.sh.outer.model.SysRule;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * sql执行类
 * 
 * @author dk
 * 2016-10-12
 * @since v1.1.6 
 *
 */
@Repository("sqlExecute")
public class SqlExecute {
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	 
	public List<SysParam> getSysParam(){
		List<SysParam> params = new ArrayList<SysParam>();
		for(Object o :sqlSessionTemplate.selectList("cn.sh.outer.dao.SysInitMapper.getSysParam")){
			params.add((SysParam)o);
		}
		return params;
	}

	public List<SysParam> getRedisParam(){
		return sqlSessionTemplate.selectList("cn.sh.outer.dao.SysInitMapper.getRedisParam");
	}
	
	public SysRule getRuleById(String id){
		return sqlSessionTemplate.selectOne("cn.sh.outer.dao.SysInitMapper.getRuleById",id);
	}
	public SysRule getRuleByCode(String code){
		return sqlSessionTemplate.selectOne("cn.sh.outer.dao.SysInitMapper.getRuleByCode",code);
	}
}
