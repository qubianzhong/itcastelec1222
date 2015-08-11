package cn.itcast.elec.web.action;

import java.util.ArrayList;
import java.util.List;

import cn.itcast.elec.container.ServiceProvider;
import cn.itcast.elec.domain.ElecUser;
import cn.itcast.elec.service.IElecLogService;
import cn.itcast.elec.service.IElecSystemDDlService;
import cn.itcast.elec.service.IElecUserService;
import cn.itcast.elec.service.impl.ElecSystemDDlServiceImpl;
import cn.itcast.elec.web.form.ElecSystemDDlForm;
import cn.itcast.elec.web.form.ElecUserForm;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

@SuppressWarnings("serial")
public class ElecUserAction extends BaseAction implements ModelDriven<ElecUserForm>{
	//���ò˵�ģ���ҵ���
	private ElecUserForm elecUserForm = new ElecUserForm();
	//�����û���¼ģ���ҵ���
	private IElecUserService elecUserService = (IElecUserService) ServiceProvider.getService(IElecUserService.SERVICE_NAME);
	//���������ֵ��ҵ���
	private IElecSystemDDlService elecSystemDDlService = (IElecSystemDDlService) ServiceProvider.getService(IElecSystemDDlService.SERVICE_NAME);
	//������־�����ҵ���
	private IElecLogService elecLogService = (IElecLogService) ServiceProvider.getService(IElecLogService.SERVICE_NAME);
	
	@Override
	public ElecUserForm getModel() {
		// TODO Auto-generated method stub
		return elecUserForm;
	}

	/**
	 * @Name:home
	 * @Description : ��ѯ�û��б���Ϣ
	 * @author ������
	 * @version :V1.0.0(�汾��)
	 * @Create Date ��2015-08-06
	 * @Parameters: null
	 * @return: String home ��ת�� UserIndex.jsp
	 */
	public String home(){
		List<ElecUserForm> list = elecUserService.findELecUserList(elecUserForm);
		request.setAttribute("userList", list);
		return "home";
	}
	
	/**
	 * @Name: add
	 * @Description : ��ת������û���ҳ��
	 * @author ������
	 * @version :V1.0.0(�汾��)
	 * @Create Date ��2015-08-06
	 * @Parameters: null
	 * @return: String home ��ת�� UserAdd.jsp
	 */
	public String add(){
		//��ʼ�������ֵ�
		this.initSystemDDl();
		
		return "add";
	}
	/**
	 * @Name: initSystemDDl
	 * @Description : ��ʼ�������ͱ༭�û�ҳ����ʹ�õ������ֵ�
	 * @author ������
	 * @version :V1.0.0(�汾��)
	 * @Create Date ��2015-08-06
	 * @Parameters: null
	 * @return: null
	 */	
	private void initSystemDDl() {
		/**
		 *ʹ���������ͽ��в�ѯ����ȡ��Ӧ���������µ��������ź�����������
		 *��ѯ�Ա�������λ���Ƿ���ְ 
		 */
		List<ElecSystemDDlForm> sexList = elecSystemDDlService.findElecSystemDDLListByKeyWord("�Ա�");
		List<ElecSystemDDlForm> jctList = elecSystemDDlService.findElecSystemDDLListByKeyWord("������λ");
		List<ElecSystemDDlForm> isDutyList = elecSystemDDlService.findElecSystemDDLListByKeyWord("�Ƿ���ְ");
		request.setAttribute("sexList", sexList);
		request.setAttribute("jctList", jctList);
		request.setAttribute("isdutyList", isDutyList);
	}

	/**
	 * @Name: save
	 * @Description : �����û���Ϣ
	 * @author ������
	 * @version :V1.0.0(�汾��)
	 * @Create Date ��2015-08-06
	 * @Parameters: null
	 * @return: String save ��ת�� UserIndex.jsp
	 */
	public String save(){
		elecUserService.saveElecUser(elecUserForm);
		//2015-08-11 �޸ģ����������޸ĵ���Ϣ�������־��  begin
		ElecUser elecUser = (ElecUser)request.getSession().getAttribute("globle_user");
		if(elecUserForm.getUserID() != null && !elecUserForm.getUserID().equals("")){
			//�޸ı���
			elecLogService.saveElecLog(request, "�û�����"+elecUser.getUserName()+" �޸��û���"+elecUserForm.getUserName()+"������Ϣ");
		}else{
			//��������
			elecLogService.saveElecLog(request, "�û�����"+elecUser.getUserName()+" �����û���"+elecUserForm.getUserName()+"������Ϣ");
		}
		// end
		String roleflag = request.getParameter("roleflag");
		if(roleflag != null && roleflag.equals("1")){
			return edit();
		}
		return "list";
	}
	
	/**
	 * @Name: edit
	 * @Description : �༭�û���Ϣ
	 * @author ������
	 * @version :V1.0.0(�汾��)
	 * @Create Date ��2015-08-06
	 * @Parameters: null
	 * @return: String edit ��ת�� UserEdit.jsp
	 */
	public String edit(){
		elecUserForm = elecUserService.findELecUser(elecUserForm);
		//ʹ��ֵջ����ʽ����elecUserForm����
//		ActionContext.getContext().getValueStack().push(elecUserForm);
		
		/**
		 * ʹ��ViewFlag�ֶ�
		 * �жϵ�ǰ�û��������Ǳ༭ҳ�� ������ϸҳ��
		 * ���viewflag==null:˵����ǰ�������Ǳ༭ҳ��
		 * ���viewflag==1��˵����ǰ����������ϸҳ��
		 */
		String viewflag = elecUserForm.getViewflag();
		request.setAttribute("viewflag", viewflag);
		
		/**
		 * 2015-08-10�޸�
		 * �жϵ����ࡰ�û���������
		 	*  �����жϵ�ǰ�����߾��еĽ�ɫ�Ƿ��� ϵͳ����Ա �� �߼�����Ա�ı�ʶ��
		 	* 		�����ǰ��������ϵͳ����Ա���߼�����Ա���� ��� ���û����� ʱ��
		 	* 		����ת��userIndex.jsp�����Բ鿴�û��б���Ϣ��
		 	* 		�����ǰ�����߲���ϵͳ����Ա���߼�����Ա��ʱ�򣬵�� ���û����� ʱ��
		 	* 		����ת��userEdit.jsp�����ԶԵ�ǰ��¼�˽��б༭������
		 */
		String roleflag = elecUserForm.getRoleflag();
		request.setAttribute("roleflag", roleflag);
		   
		this.initSystemDDl();
		return "edit";
	}
	
	/**
	 * @Name: delete
	 * @Description : ɾ���û���Ϣ
	 * @author ������
	 * @version :V1.0.0(�汾��)
	 * @Create Date ��2015-08-07
	 * @Parameters: null
	 * @return: String edit ��ת�� UserIndex.jsp
	 */
	public String delete(){
		elecUserService.deleteElecUser(elecUserForm);
		return "delete";
	}
}
