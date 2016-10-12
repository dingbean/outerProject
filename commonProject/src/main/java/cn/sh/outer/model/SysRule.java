package cn.sh.outer.model;

import java.io.Serializable;
/**
 * 
 * 
 * @author wangyuanyuan
 *
 */
public class SysRule implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7825793860810842058L;
	
	private String id;
	//规则名称
	private String ruleName;
	//规则类型 0:路由规则 1:技能组规则 2:排序规则 3:分配规则
	private String ruleType;
	//调用方式 1:接口 2:实现类
	private String ruleMethod;
	//规则code
	private String ruleCode;
	//第三方接口地址
	private String interfaceUrl;
	//是否是全局配置 1 是 0 否
	private String isGlobal;
	//备注
	private String remark;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getRuleName() {
		return ruleName;
	}
	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}
	public String getRuleType() {
		return ruleType;
	}
	public void setRuleType(String ruleType) {
		this.ruleType = ruleType;
	}
	public String getRuleMethod() {
		return ruleMethod;
	}
	public void setRuleMethod(String ruleMethod) {
		this.ruleMethod = ruleMethod;
	}
	public String getRuleCode() {
		return ruleCode;
	}
	public void setRuleCode(String ruleCode) {
		this.ruleCode = ruleCode;
	}
	
	public String getIsGlobal() {
		return isGlobal;
	}
	public void setIsGlobal(String isGlobal) {
		this.isGlobal = isGlobal;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getInterfaceUrl() {
		return interfaceUrl;
	}
	public void setInterfaceUrl(String interfaceUrl) {
		this.interfaceUrl = interfaceUrl;
	}
}
