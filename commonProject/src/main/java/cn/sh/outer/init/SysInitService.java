package cn.sh.outer.init;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import cn.sh.outer.dao.RedisDao;
import cn.sh.outer.dao.SqlExecute;
import cn.sh.outer.model.SysParam;
import cn.sh.outer.util.Constants;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;



/**
 * system initial class
 * 
 * @author dk
 * 2016-10-12
 *
 * @since 
 */

@Component("sysInitService")
@SuppressWarnings("unchecked")
public class SysInitService {
	private static final Logger logger = LoggerFactory.getLogger(SysInitService.class);
	
	@Autowired
	private SqlExecute sqlExecute;
	
	@Autowired
	private RedisDao<String, Serializable> redisDao;
	
	
	/**
	 * the main init method
	 * 
	 * @param initSp whether init sysparam
	 */
	public void init(boolean initSp){
		if(initSp) initSysParam();
	}
	
	/**
	 * init sysparam
	 * 
	 * redis key:SYS_PARAM
	 * redis vallue:HashMap<String,SysParam>
	 * map key:paramCode
	 */
    public void initSysParam(){
		logger.info("初始化系统参数……");
		HashMap<String,SysParam> sysParams  = new HashMap<String,SysParam>();
		try{
			List<SysParam> params = sqlExecute.getSysParam();
	    	
	    	for(SysParam sysParam : params){
	    		sysParams.put(sysParam.getParamCode(), sysParam);
	    	}
	    	redisDao.saveValue(Constants.SYS_PARAM, sysParams);
	    	
		}catch(Exception e){
			logger.error("初始化系统参数异常:",e);
		}
    }

	/**
	 * assemble url
	 * 
	 * @param paramCode
	 * @param suffixUrl
	 * @return
	 */
	public String assemblyUrl(String paramCode,String suffixUrl) {
		if(null == suffixUrl || "".equals(suffixUrl.trim())){
			throw new NullPointerException("the suffix of url is null");
		}
		return getSysParam(paramCode).getParamValue().concat(suffixUrl);
	}
	
	/**
	 * return the sysparam with given paramcode
	 * 
	 * @param paramCode
	 * @return
	 */
	public SysParam getSysParam(String paramCode) {
		Map<String,SysParam> sysParams = (HashMap<String,SysParam>)redisDao.readValue(Constants.SYS_PARAM);
		//如果缓存中没数据尝试重新加载一次
		if(null == sysParams){
			this.initSysParam();
			sysParams = (HashMap<String,SysParam>)redisDao.readValue(Constants.SYS_PARAM);
		}
				
		if(null == sysParams || null == sysParams.get(paramCode)){
			throw new NullPointerException("the sys param is null");
		}
		
		return sysParams.get(paramCode);
	}

}
