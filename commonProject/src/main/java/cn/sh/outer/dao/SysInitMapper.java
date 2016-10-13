package cn.sh.outer.dao;

import cn.sh.outer.model.SysParam;
import cn.sh.outer.model.SysRule;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 初始化dao
 * 
 * @author dk
 * 2016-10-12
 * @since v1.1.6 
 *
 */
public interface SysInitMapper {
	/**
	 * 获取所有系统参数
	 * 
	 * @return
	 */
	public List<SysParam> getSysParam();

	/**
	 * 获取redis配置参数
	 * 
	 * @return
	 */
	@Select("select auto_id id,param_code paramCode,param_value paramValue,param_info paramInfo,\n" +
			"\t \t\t   param_desc paramDesc,order_type orderType,remark\n" +
			"\t    from t_sys_param\n" +
			"\t    where param_code like '%REDIS%'")
	public List<SysParam> getRedisParam();
	
	/**
	 * 根据ID获取系统规则
	 * 
	 * @param id
	 * @return
	 */
	public SysRule getRuleById(String id);
	
	/**
	 * 根据CODE获取系统规则
	 * 
	 * @param code
	 * @return
	 */
	public SysRule getRuleByCode(String code);
}
