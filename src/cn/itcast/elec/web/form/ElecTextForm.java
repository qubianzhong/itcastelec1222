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
public class ElecTextForm implements Serializable{
	private String textID;
	private String textName;
	private String textDate;
	private String textRemark;
	public String getTextID() {
		return textID;
	}
	public void setTextID(String textID) {
		this.textID = textID;
	}
	public String getTextName() {
		return textName;
	}
	public void setTextName(String textName) {
		this.textName = textName;
	}
	public String getTextDate() {
		return textDate;
	}
	public void setTextDate(String textDate) {
		this.textDate = textDate;
	}
	public String getTextRemark() {
		return textRemark;
	}
	public void setTextRemark(String textRemark) {
		this.textRemark = textRemark;
	}

}
