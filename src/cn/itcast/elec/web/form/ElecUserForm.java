package cn.itcast.elec.web.form;

import java.io.Serializable;
import java.util.Date;

/**
 * PO�־ò���󣬶�Ӧ���ݿ��Elec_SystemDDl
 * @author ������
 *
 */
public class ElecUserForm implements Serializable {

	private String userID;			//����ID
	private String jctID;			//������λcode
	private String userName;		//�û�����
	private String logonName;		//��¼��
	private String logonPwd;		//����
	private String sexID;			//�Ա�
	private String birthday;			//��������
	private String address;			//��ϵ��ַ
	private String contactTel;		//��ϵ�绰 
	private String email;			//��������
	private String mobile;			//�ֻ�	
	private String isDuty;			//�Ƿ���ְ	
	private String onDutyDate;		//��ְʱ��
	private String offDutyDate;		//��ְʱ��
	private String remark;			//��ע
	/**
	 * ʹ��ViewFlag�ֶ�
	 * �жϵ�ǰ�û��������Ǳ༭ҳ�滹����ϸҳ��
	 * ���viewflag==null:˵����ǰ�������Ǳ༭ҳ��
	 * ���viewflag==1��˵����ǰ����������ϸҳ��
	 */
	private String viewflag;
	/**
	 * ʹ��flagziduan ���жϽ�ɫ�༭��ҳ���У����û��Ƿ�ѡ��
	 * ���flag = 0����ʾ�ý�ɫ��ӵ�д��û�����ҳ���еĸ�ѡ�򲻱�ѡ��
	 * ���flag = 1����ʾ�ý�ɫӵ�д��û�����ҳ���еĸ�ѡ��ѡ��
	 */
	private String flag;
	/**
	 * 	   * ����ǰ�û��Ƿ��޸������룬����md5flag���б�ʶ�ж�
	         * �����ǰ�û��޸������룬�򱣴�����ʱ����Ҫ��������м��ܣ�Ȼ�󱣴���ܺ������
	            * ����md5flag == null
	         * �����ǰ�û�û���޸����룬�򱣴�����ʱ������Ҫ��������м��ܣ�ֱ�ӱ��浱ǰ���뼴��
	            * ����md5falg == 1
	 */
	private String md5flag;
	/**
	 * �����жϵ�ǰ�����߾��еĽ�ɫ�Ƿ��� ϵͳ����Ա �� �߼�����Ա�ı�ʶ��
	 * 		�����ǰ��������ϵͳ����Ա���߼�����Ա���� ��� ���û����� ʱ��
	 * 		����ת��userIndex.jsp�����Բ鿴�û��б���Ϣ��
	 * 		�����ǰ�����߲���ϵͳ����Ա���߼�����Ա��ʱ�򣬵�� ���û����� ʱ��
	 * 		����ת��userEdit.jsp�����ԶԵ�ǰ��¼�˽��б༭������
	 *  		�����ת��userEdit.jsp ����������桱��ʱ����Ҫ�ض���userEdit.jsp
	 *  		��ʱ�����ض���ı�ʶʹ��roleflag��
	 *  			��roleflag==1ʱ����Ҫ��ת��userEdit.jsp
	 *  			��roleflag==nullʱ����Ҫ��ת��userIndex.jsp
	 */
	private String roleflag;
	
	public String getRoleflag() {
		return roleflag;
	}
	public void setRoleflag(String roleflag) {
		this.roleflag = roleflag;
	}
	public String getMd5flag() {
		return md5flag;
	}
	public void setMd5flag(String md5flag) {
		this.md5flag = md5flag;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getViewflag() {
		return viewflag;
	}
	public void setViewflag(String viewflag) {
		this.viewflag = viewflag;
	}
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
	public String getSexID() {
		return sexID;
	}
	public void setSexID(String sexID) {
		this.sexID = sexID;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
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
	public String getIsDuty() {
		return isDuty;
	}
	public void setIsDuty(String isDuty) {
		this.isDuty = isDuty;
	}
	public String getOnDutyDate() {
		return onDutyDate;
	}
	public void setOnDutyDate(String onDutyDate) {
		this.onDutyDate = onDutyDate;
	}
	public String getOffDutyDate() {
		return offDutyDate;
	}
	public void setOffDutyDate(String offDutyDate) {
		this.offDutyDate = offDutyDate;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
