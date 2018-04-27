package com.gl365.app.remote.message.request;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
public class MsgReq implements Serializable {
	private static final long serialVersionUID = 2331911885517645926L;
	@ApiModelProperty("主键ID(过滤条件)")
	private Long id;
	@ApiModelProperty("删除时传入的id列表")
	private List<Long> idList;
	@ApiModelProperty("推送对象别名")
	private String alias;
	@ApiModelProperty("app类型 ：cfront, bfront, sfront")
	private String appType;
	@ApiModelProperty("消息内容类型,前端和app定义类型(过滤条件,此条件不可与消息内容类型列表一起使用)")
	private String messageResultType;
	@ApiModelProperty("消息分类 00:支付,01账单,02:系统公告,03:激活粉丝(过滤条件,此条件不可与消息分类列表一起使用)")
	private String messageType;
	@ApiModelProperty("消息业务类型,1(交易成功通知),后续需要自己加")
	private Integer messageBusinessType;
	@ApiModelProperty("消息读取标志,00(未读),01(已读)(过滤条件)")
	private String messageRead;
	@ApiModelProperty("消息内容类型列表(过滤条件)")
	private List<String> messageResultTypeList;
	@ApiModelProperty("不接受消息分类列表(过滤条件)")
	private List<String> messageTypeList;
	@ApiModelProperty("消息删除标志,00(未删除),01(已删除)(过滤条件)")
	private String messageDel;
	@ApiModelProperty("备注")
	private String remark;
	@ApiModelProperty("消息内容")
	private String message;
	@ApiModelProperty("创建时间")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createTime;
	@ApiModelProperty("修改时间")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime updateTime;
	@ApiModelProperty("页码,为空或者0时默认为1")
	private Integer curPage;
	@ApiModelProperty("页大小,为空或者0时默认为20")
	private Integer pageSize;

	public List<Long> getIdList() {
		return idList;
	}

	public void setIdList(List<Long> idList) {
		this.idList = idList;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getAppType() {
		return appType;
	}

	public void setAppType(String appType) {
		this.appType = appType;
	}

	public String getMessageType() {
		return messageType;
	}

	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}

	public Integer getMessageBusinessType() {
		return messageBusinessType;
	}

	public void setMessageBusinessType(Integer messageBusinessType) {
		this.messageBusinessType = messageBusinessType;
	}

	public List<String> getMessageResultTypeList() {
		return messageResultTypeList;
	}

	public void setMessageResultTypeList(List<String> messageResultTypeList) {
		this.messageResultTypeList = messageResultTypeList;
	}

	public List<String> getMessageTypeList() {
		return messageTypeList;
	}

	public void setMessageTypeList(List<String> messageTypeList) {
		this.messageTypeList = messageTypeList;
	}

	public String getMessageDel() {
		return messageDel;
	}

	public void setMessageDel(String messageDel) {
		this.messageDel = messageDel;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Integer getCurPage() {
		return curPage;
	}

	public void setCurPage(Integer curPage) {
		this.curPage = curPage;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public LocalDateTime getCreateTime() {
		return createTime;
	}

	public void setCreateTime(LocalDateTime createTime) {
		this.createTime = createTime;
	}

	public LocalDateTime getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(LocalDateTime updateTime) {
		this.updateTime = updateTime;
	}

	public String getMessageResultType() {
		return messageResultType;
	}

	public void setMessageResultType(String messageResultType) {
		this.messageResultType = messageResultType;
	}

	public String getMessageRead() {
		return messageRead;
	}

	public void setMessageRead(String messageRead) {
		this.messageRead = messageRead;
	}
}
