package cn.sh.outer.util.xml;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.alibaba.fastjson.JSONObject;

public class XmlParser {
	
	
	public static Map<String, String> getXmlElmentValue(String xml) throws Exception {
		Document doc = DocumentHelper.parseText(xml);
		Element el = doc.getRootElement();
		Map<String, String> map = new HashMap<String, String>();
		xmlSearch(el, map);
		return map;
	}

	private static void xmlSearch(Element elemParent, Map<String, String> map) {

		String strValue = "";
		List elemsChild = elemParent.elements();

	
		if (elemsChild.size() > 0) {
			for (int i = 0; i < elemsChild.size(); i++) {
				xmlSearch((Element) elemsChild.get(i), map);
			}
		}
		
		else {

			strValue = (String) (elemParent.getData());
			map.put(elemParent.getName(), strValue);

		}
	}
	
	public static String resultXML(String xml,String toUser,String fromUser){
		
		StringBuffer sb = new StringBuffer();
		sb.append("<xml>");
		sb.append("<ToUserName><![CDATA["+toUser+"]]></ToUserName>");
		sb.append("<FromUserName><![CDATA["+fromUser+"]]></FromUserName>");
		sb.append("<CreateTime>"+new Date().getTime()+"</CreateTime>");
		sb.append(xml);
		sb.append("</xml>");
		return sb.toString();
	}
	
	public static String getJsonResult(String json,String code) throws Exception{
		JSONObject jsonObj = JSONObject.parseObject(json);
		return (String) jsonObj.getString(code);
	}
	
	
	
}
