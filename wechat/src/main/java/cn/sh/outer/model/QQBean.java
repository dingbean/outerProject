package cn.sh.outer.model;

import java.io.Serializable;
import java.util.List;

public class QQBean  implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long id;	

	private String systemId;
	
	private String loginUsername;
	
	private String loginPassword;

	private String userType;
	
	private String staffId;
	
	private String loginFlag;
	
	private String sessionId;
	
	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	//0:登陆成功 	1:登录失败
	private String status;
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLoginFlag() {
		return loginFlag;
	}

	public void setLoginFlag(String loginFlag) {
		this.loginFlag = loginFlag;
	}

	public String getSystemId() {
		return systemId;
	}

	public void setSystemId(String systemId) {
		this.systemId = systemId;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getStaffId() {
		return staffId;
	}

	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}
	
	public String getLoginUsername() {
		return loginUsername;
	}

	public void setLoginUsername(String loginUsername) {
		this.loginUsername = loginUsername;
	}	

	public String getLoginPassword() {
		return loginPassword;
	}

	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
