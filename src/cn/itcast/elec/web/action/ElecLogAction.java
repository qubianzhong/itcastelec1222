package cn.itcast.elec.web.action;

import java.util.List;

import cn.itcast.elec.container.ServiceProvider;
import cn.itcast.elec.service.IElecLogService;
import cn.itcast.elec.web.form.ElecLogForm;

import com.opensymphony.xwork2.ModelDriven;

@SuppressWarnings("serial")
public class ElecLogAction extends BaseAction implements ModelDriven<ElecLogForm>{

	private ElecLogForm elecLogForm = new ElecLogForm();
	
	private IElecLogService elecLogService = (IElecLogService) ServiceProvider.getService(IElecLogService.SERVICE_NAME);
	
	@Override
	public ElecLogForm getModel() {
		return elecLogForm;
	}

	/**
	 * @Name: home
	 * @Description : ��ѯ��־�б���Ϣ 
	 * @author: ������
	 * @version : V1.0.0 �汾
	 * @Create Date �� 2015-08-11
	 * @Parameters: null
	 * @return: String home ��ת��logIndex.jsp
	 */
	public String home(){
		List<ElecLogForm> list = elecLogService.findElecLogListByCondition(elecLogForm);
		request.setAttribute("logList", list);
		return "home";
	}
	
	/**
	 * @Name: delete
	 * @Description : ɾ����ѯ�õ�����־�б���Ϣ 
	 * @author: ������
	 * @version : V1.0.0 �汾
	 * @Create Date �� 2015-08-16
	 * @Parameters: null
	 * @return: String delete �ض���logIndex.jsp
	 */
	public String delete(){
		//�����ַ�ʽ
		String logid [] = request.getParameterValues("logID");
		elecLogService.deleteElecLogByLogIDs(elecLogForm);
		return "delete";
	}
}
