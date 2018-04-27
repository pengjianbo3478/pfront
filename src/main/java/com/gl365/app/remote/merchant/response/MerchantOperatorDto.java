package com.gl365.app.remote.merchant.response;
import java.io.Serializable;
import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
/**
 * < Api响应参数Dto >
 * 
 * @author hui.li 2017年5月4日 - 下午5:42:48
 * @Since 1.0
 */
public class MerchantOperatorDto implements Serializable {
	private static final long serialVersionUID = 6204501327280881446L;
	private String operatorId;
	private String userId;
	private String operatorName;
	private String merchantNo;
	private String password;
	private String telphone;
	private String roleId;
	private String neteastPwd;
	private String neteastId;
	private String status;
	private Byte isDel;
	private String resetPwd;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime lastLoginTime;
	private String remark;
	private Integer loginFailCount;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime lockTime;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createTime;
	private String image;
	private String permitService;
	private String permitDynamic;
	private Integer quit; // 离职状态0在职 1离职
	private Integer operatorSign; // 操作标志：0无1收银 2员工管理3收银和员工管理
									// 9999管理员(管理员不收该权限限制)
	private String roleName;// 角色名称
	private String parentId; // 店长ID
	private String parentName; // 店长姓名
	private String idCard;// 身份证号
	private String operatorNo; // 操作员编号
	private int linkType;// 连锁结算类型：0非连锁;1统一结算连锁;2单独结算连锁
	private String parentMerchantNo;// 上级商家：用于连锁商家
	private String merchantType;// 1商家总店2商家门店3分销商4品牌店

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getPermitService() {
		return permitService;
	}

	public void setPermitService(String permitService) {
		this.permitService = permitService;
	}

	public String getPermitDynamic() {
		return permitDynamic;
	}

	public void setPermitDynamic(String permitDynamic) {
		this.permitDynamic = permitDynamic;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getOperatorNo() {
		return operatorNo;
	}

	public void setOperatorNo(String operatorNo) {
		this.operatorNo = operatorNo;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public String getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getOperatorName() {
		return operatorName;
	}

	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}

	public String getMerchantNo() {
		return merchantNo;
	}

	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTelphone() {
		return telphone;
	}

	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getNeteastPwd() {
		return neteastPwd;
	}

	public void setNeteastPwd(String neteastPwd) {
		this.neteastPwd = neteastPwd;
	}

	public String getNeteastId() {
		return neteastId;
	}

	public void setNeteastId(String neteastId) {
		this.neteastId = neteastId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Byte getIsDel() {
		return isDel;
	}

	public void setIsDel(Byte isDel) {
		this.isDel = isDel;
	}

	public String getResetPwd() {
		return resetPwd;
	}

	public void setResetPwd(String resetPwd) {
		this.resetPwd = resetPwd;
	}

	public LocalDateTime getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(LocalDateTime lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getLoginFailCount() {
		return loginFailCount;
	}

	public void setLoginFailCount(Integer loginFailCount) {
		this.loginFailCount = loginFailCount;
	}

	public LocalDateTime getLockTime() {
		return lockTime;
	}

	public void setLockTime(LocalDateTime lockTime) {
		this.lockTime = lockTime;
	}

	public LocalDateTime getCreateTime() {
		return createTime;
	}

	public void setCreateTime(LocalDateTime createTime) {
		this.createTime = createTime;
	}

	public Integer getQuit() {
		return quit;
	}

	public void setQuit(Integer quit) {
		this.quit = quit;
	}

	public Integer getOperatorSign() {
		return operatorSign;
	}

	public void setOperatorSign(Integer operatorSign) {
		this.operatorSign = operatorSign;
	}

	public int getLinkType() {
		return linkType;
	}

	public void setLinkType(int linkType) {
		this.linkType = linkType;
	}

	public String getParentMerchantNo() {
		return parentMerchantNo;
	}

	public void setParentMerchantNo(String parentMerchantNo) {
		this.parentMerchantNo = parentMerchantNo;
	}

	public String getMerchantType() {
		return merchantType;
	}

	public void setMerchantType(String merchantType) {
		this.merchantType = merchantType;
	}
}