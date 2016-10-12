package cn.sh.outer.util;


/**
 * 常量工具类
 *  
 * @author dk
 *
 */
public class Constants {
	/*************************  接口参名   ***************************/
	
	public static final String PARAM_PID = "pid";
	
	public static final String PARAM_ID = "id";
	
	public static final String PARAM_APPID = "appId";
	
	public static final String PARAM_QUEUE_ID = "queueId";
	
	public static final String PARAM_TARGET = "target";
	
	public static final String PARAM_MESSAGE_ID = "messageId";
	
	public static final String PARAM_USER_ID = "userId";
	
	public static final String PARAM_SESSION_ID = "sessionId";

	
	public static final String PARAM_SEND_ACCOUNT = "sendAccout";
	
	public static final String PARAM_MSG = "msg";
	
	public static final String PARAM_DATA = "data";
	
	public static final String PARAM_STATUS = "status";

	public static final String PARAM_CONTENT = "content";
	
	public static final String PARAM_CHANNEL = "channel";
	public static final String PARAM_PARAMS = "params";
	public static final String PARAM_ACCOUNT = "account";

	
	public static final String PARAM_NICKNAME= "nickname";
	
	public static final String PARAM_TYPE = "type";
	
	public static final String PARAM_FROMUSER = "fromuser";
	
	public static final String PARAM_TOUSER = "touser";
	
	public static final String PARAM_IS_FORCE = "isForce";
	
	public static final String PARAM_REMARK = "remark";
	public static final String PARAM_TRANSFER_TYPE = "transferType";
	
	public static final String PARAM_TEXT = "text";
	public static final String PARAM_EMAIL = "email";
	public static final String PARAM_IMAGE = "image";
	public static final String PARAM_AUDIO = "audio";
	public static final String PARAM_VIDEO = "video";
	public static final String PARAM_NEWS = "news";
	public static final String PARAM_ARTICLES = "articles";
	
	public static final String PARAM_URL = "url";
	
	public static final String PARAM_LIST = "list";
	public static final String PARAM_COUNTER = "counter";
	public static final String PARAM_BUSY_TYPE = "busyType";
	public static final String PARAM_USERNAME = "userName";
	public static final String PARAM_PASSWORD = "passWord";
	public static final String PARAM_CHANNEL_ARR = "channelArr";
	public static final String PARAM_BUSINESS_ARR = "businessArr";


	public static final String PARAM_WEBSOCKET_SESSIONID_OLD = "websocketSessionIdOld";
	public static final String PARAM_WEBSOCKET_SESSIONID_NEW = "websocketSessionIdNew";
	public static final String PARAM_WEBSOCKET_IP_NEW = "websocketIPNew";
	public static final String PARAM_VERIFYCODE = "verifyCode";

	public static final String PARAM_LOCATION_X = "X";
	public static final String PARAM_LOCATION_Y = "Y";
	public static final String PARAM_LABEL = "label";
	
	public static final String PARAM_CLIENT_SESSION_ID = "clientSessionId";
	
	public static final String PARAM_YES = "1";
	public static final String PARAM_NO = "0";
	
	/*
	 * redis key 
	 */
	public static final String SYS_PARAM = "SYS_PARAM";
	public static final String CHANNELINFO = "CHANNELINFO";
	public static final String QUEUELIST = "QUEUELIST";
	public static final String ALLOCATE_QUEUE = ":ALLOCATE"; //分配队列key
	public static final String CACHE_QUEUE = ":CACHE";  //缓存队列key
	
	
	/*
	 * redis lock key
	 */
	public static final String CHECK_MISS_LOCK_KEY = "checkmiss";
	
	/*
	 * 操作类型
	 */
	public final static String HANDLE_ADD = "add";
	public final static String HANDLE_UPDATE = "update";
	public final static String HANDLE_DEL = "del";
	
	/*
	 * http 请求实体类型
	 */
	public final static String CONTENT_TYPE_JSON = "application/json;charset=UTF-8";
	public final static String CONTENT_TYPE_HTML = "text/html;charset=UTF-8";
	public final static String CONTENT_TYPE_PLAIN = "text/plain;charset=UTF-8";

	public static final String DEFAULT_ROUTING_RULE = "localMessageHandler";
	public static final String DEFAULT_SKILLQUEUE_RULE = "channelBusinessRoutingRule";
	public static final String DEFAULT_SORT_RULE = "prioritySortRule";
	public static final String DEFAULT_ALLOCATE_RULE = "defaultAllocationRule";
	
	public static String [] artificialStatus = {"5", "6", "8","11"};

	public static String [] sortingStatus = {"2", "3", "4"};
	
	public static final String LVCODE="LVCODE@@"; 
	
}
