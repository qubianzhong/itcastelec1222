package cn.itcast.elec.web.form;

import java.io.Serializable;
import java.util.Date;

/**
 * PO�־ò���󣬶�Ӧ���ݿ��Elec_SystemDDl
 * @author ������
 *
 */
public class ElecRoleForm implements Serializable {

	private String role;			//��ɫ ID
	private String roleid;			//��ɫ ID
	private String [] selectoper;	//Ȩ�ޱ�ţ�Ȩ��code��
	private String [] selectuser;	//�û�ID
	
	public String getRoleid() {
		return roleid;
	}

	public void setRoleid(String roleid) {
		this.roleid = roleid;
	}

	public String[] getSelectoper() {
		return selectoper;
	}

	public void setSelectoper(String[] selectoper) {
		this.selectoper = selectoper;
	}

	public String[] getSelectuser() {
		return selectuser;
	}

	public void setSelectuser(String[] selectuser) {
		this.selectuser = selectuser;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
}
