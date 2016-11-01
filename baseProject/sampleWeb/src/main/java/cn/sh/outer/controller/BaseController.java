package cn.sh.outer.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * ACTION基类
 * 
 * @author yan
 *
 */
public class BaseController {
	
	public final static String SUCCESS_CODE = "0";
	
	public final static String ERROR_CODE = "-1";
	
	public final static String SUCCESS_MSG = "success.";
	

	public static final String STATUS_NORMAL = "1";
	
	public static final String STATUS_DELETED = "0";
	
	public String webPrefix ;
	
	enum OperateTip{
		SUCCESS("操作成功", "0"), ERROR("操作失败", "-1");
		private final String name;
		private final String code;

    	private OperateTip (String name, String code){
    		this.name = name;
    		this.code = code;
    	}

    	public String getName() {
    		return name;
    	}

    	public String getCode() {
    		return code;
    	}
	}
	
	
	@RequestMapping(value = "/page/{optType}")
	public String pageRedirect(@PathVariable String optType,Model model,HttpServletRequest request){
		String preffix = request.getParameter("preffix");
		
		for(Object key : request.getParameterMap().keySet()) {
			model.addAttribute(key.toString(), request.getParameter(key.toString()));
		}
		
		request.setAttribute("basePrefix", preffix);
		return preffix + "/" + optType;
	}
	
	/**
	 * 流->字符串
	 * @param in
	 * @return
	 * @throws IOException
	 */
	public String streamToString(InputStream in)
			throws IOException {
		Scanner scanner = new Scanner(in, "UTF-8");
		StringBuffer buffer = new StringBuffer();
		while(scanner.hasNextLine()) {
			buffer.append(scanner.nextLine());
		}
		scanner.close();
		return buffer.toString();
	}

	/**
	 * 返回成功JSON
	 * @return
	 */
	public JSONObject getSuccessJsonObj() {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("resultCode", SUCCESS_CODE);
		jsonObject.put("resultMsg", SUCCESS_MSG);
		return jsonObject;
	}
	
	public String getSuccessResult() {
		return getSuccessJsonObj().toJSONString();
	}
	
	public JSONObject getErrorJsonObj(String errmsg) {
		return getErrorJsonObj(ERROR_CODE, errmsg);
	}
	
	/**
	 * 返回失败JSON
	 * @param code
	 * @param errmsg
	 * @return
	 */
	public JSONObject getErrorJsonObj(String code, String errmsg) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("resultCode", code);
		jsonObject.put("resultMsg", errmsg);
		return jsonObject;
	}
	
	public String getErrorResult(String code, String errmsg) {
		return getErrorJsonObj(code, errmsg).toJSONString();
	}
	
	public String getErrorResult(String errmsg) {
		return getErrorResult(ERROR_CODE, errmsg);
	}
	
	public Map<String,Object> getSuccMap(){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("resultCode", SUCCESS_CODE);
		map.put("resultMsg", SUCCESS_MSG);
		
		return map;
	}
	public Map<String,Object> getSuccMap(Object data){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("resultCode", SUCCESS_CODE);
		map.put("resultMsg", SUCCESS_MSG);
		map.put("data", data);
		
		return map;
	}
	
	public Map<String,Object> getErrorMap(String msg){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("resultCode", ERROR_CODE);
		map.put("resultMsg", msg);
		
		return map;
	}
	
	public Map<String,Object> getMessageMap(OperateTip titleType, String content){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("resultCode", titleType.getCode());
		map.put("resultMsg", titleType.getName());
		map.put("data", content);
		map.put("returnUrl", this.webPrefix + "/page/init");
		
		return map;
	}
	
	public Map<String,Object> getMessageMap(OperateTip titleType){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("resultCode", titleType.getCode());
		map.put("resultMsg", titleType.getName());
		
		return map;
	}
	
	public String getMessagePage(){
		return "cpage/message";
	}
	
	@RequestMapping(value = "/returnMsgPage/{result}", method=RequestMethod.GET, produces={ "application/json;charset=UTF-8" })
	@ResponseBody
    public ModelAndView returnMsgPage(@PathVariable String result){
		ModelAndView mav = new ModelAndView( getMessagePage() ); 
		mav.addObject("message", getMessageMap(OperateTip.valueOf(result)));
		return mav;
    }
	
}