package cn.itcast.elec.web.action;

import java.util.List;

import cn.itcast.elec.container.ServiceProvider;
import cn.itcast.elec.service.IElecSystemDDlService;
import cn.itcast.elec.web.form.ElecSystemDDlForm;

import com.opensymphony.xwork2.ModelDriven;

@SuppressWarnings("serial")
public class ElecSystemDDlAction extends BaseAction implements ModelDriven<ElecSystemDDlForm>{

	private ElecSystemDDlForm elecSystemDDlForm = new ElecSystemDDlForm();
	
	private IElecSystemDDlService elecSystemDDlService = (IElecSystemDDlService) ServiceProvider.getService(IElecSystemDDlService.SERVICE_NAME);
	
	@Override
	public ElecSystemDDlForm getModel() {
		// TODO Auto-generated method stub
		return elecSystemDDlForm;
	}

	/**
	 * @Name:home
	 * @Description : ��ѯ�������ͣ���ȥ���ظ�ֵ 
	 * @author ������
	 * @version :V1.0.0(�汾��)
	 * @Create Date ��2015-08-04
	 * @Parameters: null
	 * @return: String home ��ת�� dictionaryIndex.jsp
	 */
	public String home(){
		List<ElecSystemDDlForm> list = elecSystemDDlService.findKeyWord();
		request.setAttribute("systemList", list);
		return "home";
	}
	
	/**
	 * @Name:edit
	 * @Description : ����ѡ���������ͣ���ת���༭���������͵�ҳ��
	 * @author ������
	 * @version :V1.0.0(�汾��)
	 * @Create Date ��2015-08-04
	 * @Parameters: null
	 * @return: String edit ��ת�� dictionaryEdit.jsp
	 */
	public String edit(){
		//��ȡ��������
		String keyWord = elecSystemDDlForm.getKeyword();
		List<ElecSystemDDlForm> list = elecSystemDDlService.findElecSystemDDLListByKeyWord(keyWord);
		request.setAttribute("systemList", list);
		return "edit";
	}
	
	/**
	 * @Name:save
	 * @Description : ���������ֵ�
	 * @author ������
	 * @version :V1.0.0(�汾��)
	 * @Create Date ��2015-08-04
	 * @Parameters: null
	 * @return: String save �ض��� dictionaryIndex.jsp
	 */
	public String save(){
		System.out.println("////////////////////////");
		elecSystemDDlService.saveElecSystemDDl(elecSystemDDlForm);
		return "save";
	}
}
