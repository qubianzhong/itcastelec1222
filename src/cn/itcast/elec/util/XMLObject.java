package cn.itcast.elec.util;
/**
 * ���Function.xml �ļ���
 * ��ȡ��Ȩ�� Ȩ��code Ȩ������ ����Ȩ��code ����Ȩ������
 * @author Administrator
 *
 */
public class XMLObject {

	private String code;		//Ȩ��code
	private String name;		//Ȩ������
	private String parentCode;	//����Ȩ��code
	private String parentName;	//����Ȩ������
	/**
	 * �ж�ҳ���еĽ�ɫ�༭ʱ��Ȩ�޵ĸ�ѡ���Ƿ�ѡ�еı�ʶ
	 * ��� flag = 0����ʾ�ý�ɫ������Ȩ�ޣ���ҳ����Ȩ�޸�ѡ�򲻱�ѡ��
	 * ���flag = 1����ʾ�ý�ɫ���д�Ȩ�ޣ���������Ȩ�޸�ѡ��ѡ��
	 */
	private String flag;
	
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getParentCode() {
		return parentCode;
	}
	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}
	public String getParentName() {
		return parentName;
	}
	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
}
