package cn.itcast.elec.web.form;

import java.io.Serializable;
import java.util.Date;

/**
 * VO�־ò����,��Ӧҳ���ϵ���־������
 * @author ������
 *
 */
public class ElecLogForm implements Serializable {

	private String LogID;		//����ID
	private String IpAddress;	//IP��ַ
	private String OpeName;		//������
	private String OpeTime;	//����ʱ��
	private String Details;		//������ϸ
	
	public String getLogID() {
		return LogID;
	}
	public void setLogID(String logID) {
		LogID = logID;
	}
	public String getIpAddress() {
		return IpAddress;
	}
	public void setIpAddress(String ipAddress) {
		IpAddress = ipAddress;
	}
	public String getOpeName() {
		return OpeName;
	}
	public void setOpeName(String opeName) {
		OpeName = opeName;
	}
	public String getOpeTime() {
		return OpeTime;
	}
	public void setOpeTime(String opeTime) {
		OpeTime = opeTime;
	}
	public String getDetails() {
		return Details;
	}
	public void setDetails(String details) {
		Details = details;
	}
	
}
