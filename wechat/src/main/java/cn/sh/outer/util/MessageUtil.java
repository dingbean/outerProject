package cn.sh.outer.util;

import java.io.IOException;
import java.io.Reader;
import java.util.Date;

import cn.sh.outer.model.resp.BaseMessage;
import cn.sh.outer.model.resp.MusicMessage;
import cn.sh.outer.model.resp.NewsMessage;
import cn.sh.outer.model.resp.TextMessage;
import cn.sh.outer.util.xml.XML2Json;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;


/**
 * 消息工具类
 * 
 * @author liufeng
 * @date 2013-05-19
 */
public class MessageUtil {

	public static Logger log = Logger.getLogger(MessageUtil.class.getName());

	/**
	 * 返回消息类型：文本
	 */
	public static final String RESP_MESSAGE_TYPE_TEXT = "text";

	/**
	 * 返回消息类型：音乐
	 */
	public static final String RESP_MESSAGE_TYPE_MUSIC = "music";

	/**
	 * 返回消息类型：图文
	 */
	public static final String RESP_MESSAGE_TYPE_NEWS = "news";

	/**
	 * 请求消息类型：文本
	 */
	public static final String REQ_MESSAGE_TYPE_TEXT = "text";

	/**
	 * 请求消息类型：图片
	 */
	public static final String REQ_MESSAGE_TYPE_IMAGE = "image";

	/**
	 * 请求消息类型：链接
	 */
	public static final String REQ_MESSAGE_TYPE_LINK = "link";

	/**
	 * 请求消息类型：地理位置
	 */
	public static final String REQ_MESSAGE_TYPE_LOCATION = "location";

	/**
	 * 请求消息类型：音频
	 */
	public static final String REQ_MESSAGE_TYPE_VOICE = "voice";

	/**
	 * 请求消息类型：推送
	 */
	public static final String REQ_MESSAGE_TYPE_EVENT = "event";

	/**
	 * 事件类型：subscribe(订阅)
	 */
	public static final String EVENT_TYPE_SUBSCRIBE = "subscribe";

	/**
	 * 事件类型：unsubscribe(取消订阅)
	 */
	public static final String EVENT_TYPE_UNSUBSCRIBE = "unsubscribe";

	/**
	 * 事件类型：CLICK(自定义菜单点击事件)
	 */
	public static final String EVENT_TYPE_CLICK = "CLICK";

	public static String jsonToWechat(String xmlStr) {
		XML2Json xmlSerializer = new XML2Json();
		// XMLSerializer xmlSerializer = new XMLSerializer();
		System.out.println("xmlStr======"+xmlStr);
		JSONObject json = (JSONObject) xmlSerializer.read(xmlStr);

		String msgtype = (String) json.get("msgtype");
		// 多图文消息处理
		if ("news".equals(msgtype)) {
			if (json.get("articles") instanceof JSONObject) {
				JSONObject articles = (JSONObject) json.get("articles");
				JSONArray item = new JSONArray();
				item.add(articles.get("item"));
				articles.remove("item");
				JSONObject news = new JSONObject();
				news.put("articles", item);
				json.remove("articles");
				json.put(msgtype, news);

			} else {

				JSONObject obj = new JSONObject();

				obj.put("articles", json.get("articles"));
				json.remove("articles");
				json.put(msgtype, obj);
			}
			// 文本消息处理
		} else if ("text".equals(msgtype)) {
			JSONObject content = new JSONObject();
			content.put("content", json.get("content"));
			json.remove("content");
			json.put(msgtype, content);
			// 处理语音消息
		} else if ("voice".equals(msgtype)) {
			JSONObject media_id = new JSONObject();
			media_id.put("media_id", json.get("media_id"));
			json.remove("media_id");
			json.put(msgtype, media_id);
		}
		return json.toString();
	}




	public static String  getResult(String toUser,String fromUser,String result){
		long time = new Date().getTime();
		String xml = "<xml>" +
				"<ToUserName><![CDATA["+toUser+"]]></ToUserName>" +
				"<FromUserName><![CDATA["+fromUser+"]]></FromUserName>" +
				"<CreateTime>"+time+"</CreateTime>" +result+
				"</xml>";
		return xml;
	}
	
	public static String getNickName(String jsonStr){
		String str = "";
		try{
			String [] res = jsonStr.split(",");
			for(int i=0;i<res.length;i++){
				if(res[i].indexOf("nickname")>0){
					String val [] = res[i].split(":");
					str = val[1];
				}
			}
			str = str.replace("\"", "");
			return str;
		}catch(Exception e){
			return str;
		}
	}
	
	 public static void main(String[] args) {
//		 AgentInfo agentInfo = new AgentInfo();
//		 agentInfo.setToUser("dc7c5f98719c62dd");
//		 agentInfo.setOpenid("b263e9557aca8770");
//		 agentInfo.setCreateTime("23542345356");
//		 agentInfo.setPlatform("YX");
//		 agentInfo.setMessage("20131017测试10");	 
////		 agentInfo.setPicUrl("http://d.hiphotos.baidu.com/pic/w%3D2048/sign=49046bc6f3d3572c66e29bdcbe2b6227/8644ebf81a4c510f9a2cde356159252dd42aa530.jpg");
//		 agentInfo.setMsgType("text");
//		 agentInfo.setMsgId("2345234534653456356");
//		 agentInfo.setNickname("测试");
//		 String res =  MessageUtil.sendAgentInfo(agentInfo);
//		 System.out.println(res);
//		 String o = "YXb263e9557aca8770";
//		 System.out.println(new Date().getTime()/1000);
//		 
//		 for(int i=1;i<20;i++){
//			 AgentInfo agentInfo = new AgentInfo();
//			 agentInfo.setToUser("dc7c5f98719c62dd");
//			 agentInfo.setOpenid("b263e9557aca8770");
//			 agentInfo.setCreateTime("23542345356");
//			 agentInfo.setPlatform("YX");
//			 agentInfo.setMessage("20131017格式错了"+i);	 
////			 agentInfo.setPicUrl("http://d.hiphotos.baidu.com/pic/w%3D2048/sign=49046bc6f3d3572c66e29bdcbe2b6227/8644ebf81a4c510f9a2cde356159252dd42aa530.jpg");
//			 agentInfo.setMsgType("text");
//			 agentInfo.setMsgId("2345234534653456356");
//			 String res =  MessageUtil.sendAgentInfo(agentInfo);
//			 System.out.println(res); 
//		 }
		
	 }
	 
	 public static String streamToString(Reader reader) throws IOException {
			char[] buf = new char[1024];
			int len = 0;
			StringBuffer buffer = new StringBuffer();
			while ((len = reader.read(buf)) != -1) {
				buffer.append(buf, 0, len);
			}
			return buffer.toString();
		}


}
