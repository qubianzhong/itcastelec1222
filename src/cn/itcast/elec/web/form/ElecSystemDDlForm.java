package cn.itcast.elec.web.form;

import java.io.Serializable;
import java.util.Date;

/**
 * PO�־ò���󣬶�Ӧ���ݿ��Elec_SystemDDl
 * @author ������
 *
 */
public class ElecSystemDDlForm implements Serializable {

	private String seqID;		//����ID(������)
	private String keyword;		//��ѯ�ؼ���
	private String ddlCode;		//�����ֵ��code
	private String ddlName;		//�����ֵ��value
	private String keywordname; //����������ֵ�Ĺؼ���
	/**
	 * ���������ֵ��״̬��ʶ
	 * ֵ=new �½�һ���������ͣ��������������б���
	 * ֵ=add ��ԭ�е��������͵Ļ����Ͻ����޸ĺͱ༭���༭��Ӧ����������б���
	 */
	private String typeflag;    
	//���������ֵ�������������
	private String [] itemname;
	
	
	
	public String getKeywordname() {
		return keywordname;
	}
	public void setKeywordname(String keywordname) {
		this.keywordname = keywordname;
	}
	public String getTypeflag() {
		return typeflag;
	}
	public void setTypeflag(String typeflag) {
		this.typeflag = typeflag;
	}
	public String[] getItemname() {
		return itemname;
	}
	public void setItemname(String[] itemname) {
		this.itemname = itemname;
	}
	public String getSeqID() {
		return seqID;
	}
	public void setSeqID(String seqID) {
		this.seqID = seqID;
	}
	
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getDdlCode() {
		return ddlCode;
	}
	public void setDdlCode(String ddlCode) {
		this.ddlCode = ddlCode;
	}
	public String getDdlName() {
		return ddlName;
	}
	public void setDdlName(String ddlName) {
		this.ddlName = ddlName;
	}
	
	
	
	
}
