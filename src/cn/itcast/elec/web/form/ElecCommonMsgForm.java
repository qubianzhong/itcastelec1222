package cn.itcast.elec.web.form;

import java.io.Serializable;
import java.util.Date;
/**
 * VO ���󣬶�Ӧҳ���������ֵ
 * PO ������VO����Ĺ�ϵ��
 * 	��ͬ�㣺
 *		PO�����е����Թ������ݿ���ֶ�
 *		VO�����е����Կ����������ӡ��޸ġ�ɾ������Ӧ����ҳ��ı�����
 */
@SuppressWarnings("serial")
public class ElecCommonMsgForm implements Serializable{
	private String comID;			//����ID
	private String stationRun;		//վ���������
	private String devRun;			//�豸�������
	private String createDate;		//��������
	public String getComID() {
		return comID;
	}
	public void setComID(String comID) {
		this.comID = comID;
	}
	public String getStationRun() {
		return stationRun;
	}
	public void setStationRun(String stationRun) {
		this.stationRun = stationRun;
	}
	public String getDevRun() {
		return devRun;
	}
	public void setDevRun(String devRun) {
		this.devRun = devRun;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	
}
