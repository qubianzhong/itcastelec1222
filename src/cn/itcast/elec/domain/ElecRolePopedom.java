package cn.itcast.elec.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * PO�־ò���󣬶�Ӧ���ݿ��Elec_Role_Popedom
 * @author ������
 *
 */
@SuppressWarnings("serial")
public class ElecRolePopedom implements Serializable {
	
	private String roleID;			//����ID
	private String popedomcode;		//����web�ļ���Ȩ�޵ı���code���ַ�������
	private String remark;			//��ע
	
	public String getRoleID() {
		return roleID;
	}
	public void setRoleID(String roleID) {
		this.roleID = roleID;
	}
	public String getPopedomcode() {
		return popedomcode;
	}
	public void setPopedomcode(String popedomcode) {
		this.popedomcode = popedomcode;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
