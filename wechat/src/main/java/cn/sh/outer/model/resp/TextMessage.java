package cn.sh.outer.model.resp;

import cn.sh.outer.util.MessageUtil;

/**
 * 文本消息
 * 
 */
public class TextMessage extends BaseMessage {
	// 回复的消息内容
	private String Content;

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}
	

	public TextMessage() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TextMessage(String content) {
		super();
		super.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
		Content = content;
	}

	@Override
	public String toString() {
		return "TextMessage [Content=" + Content + ", toString()="
				+ super.toString() + "]";
	}
	
	
	
}