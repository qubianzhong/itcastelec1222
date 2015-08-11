package cn.itcast.elec.web.action;

import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;

import cn.itcast.elec.container.ServiceProvider;
import cn.itcast.elec.domain.ElecText;
import cn.itcast.elec.service.IElecTextService;
import cn.itcast.elec.util.StringHelper;
import cn.itcast.elec.web.form.ElecTextForm;

import com.opensymphony.xwork2.ModelDriven;

public class ElecTextAction extends BaseAction implements ModelDriven<ElecTextForm> {

	private ElecTextForm elecTextForm = new ElecTextForm();
	private HttpServletRequest request = null;
	private IElecTextService elecTextService = (IElecTextService) ServiceProvider.getService(IElecTextService.SERVICE_NAME);

	@Override
	public ElecTextForm getModel() {
		return elecTextForm;
	}
	/**
	 * @Name:save
	 * @Description : 
	 * @author ������
	 * @version :V1.0.0(�汾��)
	 * @Create Date ��
	 * @Parameters:
	 * @return:
	 */
	
	public String save(){
		//VO����ת����PO����
		
		//ʵ����PO����
		ElecText elecText = new ElecText();
		//��������
		elecText.setTextName(elecTextForm.getTextName());
		//��������
		elecText.setTextDate(StringHelper.stringConvertDate(elecTextForm.getTextDate()));
		//���Ա�ע
		elecText.setTextRemark(elecTextForm.getTextRemark());

		elecTextService.saveElecText(elecText);
		return "save";
	}

}
