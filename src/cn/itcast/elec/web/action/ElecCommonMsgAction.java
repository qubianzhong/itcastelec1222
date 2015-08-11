package cn.itcast.elec.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import cn.itcast.elec.container.ServiceProvider;
import cn.itcast.elec.service.IElecCommonMsgService;
import cn.itcast.elec.web.form.ElecCommonMsgForm;

import com.opensymphony.xwork2.ModelDriven;

@SuppressWarnings("serial")
public class ElecCommonMsgAction extends BaseAction implements ModelDriven<ElecCommonMsgForm> {

	private ElecCommonMsgForm elecCommonMsgForm = new ElecCommonMsgForm();
	private IElecCommonMsgService elecCommonMsgService = (IElecCommonMsgService) ServiceProvider.getService(IElecCommonMsgService.SERVICE_NAME);

	@Override
	public ElecCommonMsgForm getModel() {
		return elecCommonMsgForm;
	}

	/**
	 * @Name:home
	 * @Description : ��ѯ���������б�
	 * @author ������
	 * @version :V1.0.0(�汾��)
	 * @Create Date ��2015-08-03
	 * @Parameters: ��
	 * @return:String home ��ת��actingIndex.jsp
	 */
	public String home(){
		List<ElecCommonMsgForm> list = elecCommonMsgService.findElecCommonMsgList();
		request.setAttribute("commonList", list);
		return "home";
	}

	/**
	 * @Name:save
	 * @Description : �������������Ϣ
	 * @author ������
	 * @version :V1.0.0(�汾��)
	 * @Create Date ��2015-08-03
	 * @Parameters: ��
	 * @return:String save �ض���actingIndex.jsp
	 */
	public String save(){
		elecCommonMsgService.saveElecCommonMsg(elecCommonMsgForm);
		return "save";
	}

	
}
