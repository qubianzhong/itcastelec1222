package cn.itcast.elec.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * PO�־ò���󣬶�Ӧ���ݿ��Elec_SystemDDl
 * @author ������
 *
 */
public class ElecSystemDDl implements Serializable {

	private Integer seqID;		//����ID(������)
	private String keyword;		//��ѯ�ؼ���
	private Integer ddlCode;		//�����ֵ��code
	private String ddlName;		//�����ֵ��value
	
	
	public Integer getSeqID() {
		return seqID;
	}
	public void setSeqID(Integer seqID) {
		this.seqID = seqID;
	}

	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public Integer getDdlCode() {
		return ddlCode;
	}
	public void setDdlCode(Integer ddlCode) {
		this.ddlCode = ddlCode;
	}
	public String getDdlName() {
		return ddlName;
	}
	public void setDdlName(String ddlName) {
		this.ddlName = ddlName;
	}
	
	
	
	
}
