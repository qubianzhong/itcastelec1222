package cn.itcast.elec.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * PO�־ò���󣬶�Ӧ���ݿ��Elec_Log
 * @author ������
 * @create Date 2015-08-11
 */
@SuppressWarnings("serial")
public class ElecLog implements Serializable {
	private String LogID;		//����ID
	private String IpAddress;	//IP��ַ
	private String OpeName;		//������
	private Date OpeTime;	//����ʱ��
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
	public Date getOpeTime() {
		return OpeTime;
	}
	public void setOpeTime(Date opeTime) {
		OpeTime = opeTime;
	}
	public String getDetails() {
		return Details;
	}
	public void setDetails(String details) {
		Details = details;
	}
	
}
