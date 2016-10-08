package cn.sh.outer.model.resp;


import cn.sh.outer.util.MessageUtil;

import java.util.List;


/**
 * 文本消息
 * 
 */
public class NewsMessage extends BaseMessage {
	// 图文消息个数，限制为10条以内
	private int ArticleCount;
	// 多条图文消息信息，默认第一个item为大图
	private List<Article> Articles;

	public int getArticleCount() {
		return ArticleCount;
	}

	public void setArticleCount(int articleCount) {
		ArticleCount = articleCount;
	}

	public List<Article> getArticles() {
		return Articles;
	}

	public void setArticles(List<Article> articles) {
		Articles = articles;
	}

	
	
	public NewsMessage() {
		super();
		// TODO Auto-generated constructor stub
	}

	public NewsMessage(int articleCount, List<Article> articles) {
		super();
		super.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
		ArticleCount = articleCount;
		Articles = articles;
	}

	@Override
	public String toString() {
		return "NewsMessage [ArticleCount=" + ArticleCount + ", Articles="
				+ Articles + ", toString()=" + super.toString() + "]";
	}
	
	
}