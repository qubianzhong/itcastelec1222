package cn.itcast.elec.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * PO�־ò���󣬶�Ӧ���ݿ��Elec_User_Role
 * @author ������
 *
 */
@SuppressWarnings("serial")
public class ElecUserRole implements Serializable {
	private Integer seqID;		//����ID
	private String userID;		//�û�ID
	private String roleID;		//��ɫID
	private String remark;		//��ע
	
	public Integer getSeqID() {
		return seqID;
	}
	public void setSeqID(Integer seqID) {
		this.seqID = seqID;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getRoleID() {
		return roleID;
	}
	public void setRoleID(String roleID) {
		this.roleID = roleID;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
