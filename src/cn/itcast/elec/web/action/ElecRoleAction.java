package cn.itcast.elec.web.action;

import java.util.List;

import cn.itcast.elec.container.ServiceProvider;
import cn.itcast.elec.service.IElecRoleService;
import cn.itcast.elec.service.IElecSystemDDlService;
import cn.itcast.elec.util.XMLObject;
import cn.itcast.elec.web.form.ElecRoleForm;
import cn.itcast.elec.web.form.ElecSystemDDlForm;
import cn.itcast.elec.web.form.ElecUserForm;

import com.opensymphony.xwork2.ModelDriven;

@SuppressWarnings("serial")
public class ElecRoleAction extends BaseAction implements ModelDriven<ElecRoleForm>{

	private ElecRoleForm elecRoleForm = new ElecRoleForm();
	
	private IElecRoleService elecRoleService = (IElecRoleService) ServiceProvider.getService(IElecRoleService.SERVICE_NAME);
	
	private IElecSystemDDlService elecSystemDDlService = (IElecSystemDDlService) ServiceProvider.getService(IElecSystemDDlService.SERVICE_NAME);
	@Override
	public ElecRoleForm getModel() {
		// TODO Auto-generated method stub
		return elecRoleForm;
	}

	/**
	 * @Name:home
	 * @Description : ��ѯ���еĽ�ɫ���ͣ��������ֵ��л�ȡ��
	 * 				  ��Function.xml�ļ��в�ѯϵͳ���еĹ���Ȩ��
	 * @author ������
	 * @version :V1.0.0(�汾��)
	 * @Create Date ��2015-08-08
	 * @Parameters: null
	 * @return: String home ��ת�� roleIndex.jsp
	 */
	public String home(){
		//��ȡ���еĽ�ɫ����
		List<ElecSystemDDlForm> systemList = elecSystemDDlService.findElecSystemDDLListByKeyWord("��ɫ����");
		request.setAttribute("systemList", systemList);
		//��Function.xml�л�ȡȨ�޵ļ���
		List<XMLObject> xmlList = elecRoleService.readXml();
		request.setAttribute("xmlList", xmlList);
		return "home";
	}

	/**
	 * @Name: edit
	 * @Description :1�� ʹ�ý�ɫID��ѯ�ý�ɫ�¾��е�Ȩ�ޣ�����ϵͳ�����е�Ȩ�޽���ƥ��
	 * 				 2��  ʹ�ý�ɫID��ѯ�ý�ɫ��ӵ�е��û�
	 * @author ������
	 * @version :V1.0.0(�汾��)
	 * @Create Date ��2015-08-08
	 * @Parameters: null
	 * @return: String edit ��ת�� roleEdit.jsp
	 */
	public String edit(){
		String roleID = elecRoleForm.getRole();
		//��ѯȨ�޼���
		List<XMLObject> xmlList = elecRoleService.readEditXml(roleID);
		request.setAttribute("xmlList", xmlList);
		//��ѯ�û�����
		List<ElecUserForm> userList = elecRoleService.findElecUserByRoleID(roleID);
		request.setAttribute("userList", userList);
		return "edit";
	}
	
	/**
	 * @Name: save
	 * @Description : ִ�б����ɫ��Ȩ�޵Ĺ�����
	 * 					�����û��ͽ�ɫ�Ĺ�����
	 * @author ������
	 * @version :V1.0.0(�汾��)
	 * @Create Date ��2015-08-08
	 * @Parameters: null
	 * @return: String save �ض��� roleIndex.jsp
	 */
	public String save(){
		elecRoleService.saveRole(elecRoleForm);
		return "save";
	}
}
