package Message;

import java.util.Date;

/**
 * Indexing Object
 * @author wangzehui
 *
 */
public class Index {

	private int msgId;
	private String title;
	private String summary;
	private Date createDate;
	public int getMsgId() {
		return msgId;
	}
	/**
	 * 留言的id，若通过Attachment搜索的数据需要传递的field是parentId
	 * @param msgId
	 */
	public void setMsgId(int msgId) {
		this.msgId = msgId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * 带高亮的留言摘要
	 * @return
	 */
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	/**
	 * 留言的创建时间
	 * @return
	 */
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	
	
}
