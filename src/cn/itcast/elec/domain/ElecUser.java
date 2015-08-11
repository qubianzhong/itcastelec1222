package cn.itcast.elec.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * PO�־ò���󣬶�Ӧ���ݿ��Elec_User
 * @author ������
 *
 */
@SuppressWarnings("serial")
public class ElecUser implements Serializable {
	private String userID;			//����ID
	private String jctID;			//������λcode
	private String userName;		//�û�����
	private String logonName;		//��¼��
	private String logonPwd;		//����
	private Integer sexID;			//�Ա�
	private Date birthday;			//��������
	private String address;			//��ϵ��ַ
	private String contactTel;		//��ϵ�绰 
	private String email;			//��������
	private String mobile;			//�ֻ�	
	private Integer isDuty;			//�Ƿ���ְ	
	private Date onDutyDate;		//��ְʱ��
	private Date offDutyDate;		//��ְʱ��
	private String remark;			//��ע	
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getJctID() {
		return jctID;
	}
	public void setJctID(String jctID) {
		this.jctID = jctID;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getLogonName() {
		return logonName;
	}
	public void setLogonName(String logonName) {
		this.logonName = logonName;
	}
	public String getLogonPwd() {
		return logonPwd;
	}
	public void setLogonPwd(String logonPwd) {
		this.logonPwd = logonPwd;
	}
	
	public Integer getSexID() {
		return sexID;
	}
	public void setSexID(Integer sexID) {
		this.sexID = sexID;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getContactTel() {
		return contactTel;
	}
	public void setContactTel(String contactTel) {
		this.contactTel = contactTel;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Integer getIsDuty() {
		return isDuty;
	}
	public void setIsDuty(Integer isDuty) {
		this.isDuty = isDuty;
	}
	public Date getOnDutyDate() {
		return onDutyDate;
	}
	public void setOnDutyDate(Date onDutyDate) {
		this.onDutyDate = onDutyDate;
	}
	public Date getOffDutyDate() {
		return offDutyDate;
	}
	public void setOffDutyDate(Date offDutyDate) {
		this.offDutyDate = offDutyDate;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
