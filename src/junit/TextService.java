package junit;

import java.util.Date;
import java.util.List;

import org.junit.Test;

import cn.itcast.elec.container.ServiceProvider;
import cn.itcast.elec.domain.ElecText;
import cn.itcast.elec.service.IElecTextService;
import cn.itcast.elec.web.form.ElecTextForm;

public class TextService {

	@Test
	public void saveElecText(){
		IElecTextService elecTextService = (IElecTextService) ServiceProvider.getService(IElecTextService.SERVICE_NAME);
		//ʵ����PO���󣬸�ֵ��ִ�б���
		ElecText elecText = new ElecText();
		elecText.setTextDate(new Date());
		elecText.setTextName("����Service����123");
		elecText.setTextRemark("����Service��ע123");
		elecTextService.saveElecText(elecText);
	}
	/**
	 * ͨ����ѯ��������ѯ������б���
	 * ģ��Action��
	 */
	@Test
	public void findCollection(){
		IElecTextService elecTextService = (IElecTextService) ServiceProvider.getService(IElecTextService.SERVICE_NAME);
		//ʵ����PO���󣬸�ֵ��ִ�б���
		ElecTextForm elecTextForm = new ElecTextForm();
		elecTextForm.setTextName("ll");
		elecTextForm.setTextRemark("ha");
		List<ElecText> list = elecTextService.findCollectionByConditionNoPage(elecTextForm);
	}
	
	@Test
	public void test(){
	    //�õ���ļ�д����
	    System.out.println(ElecTextForm.class.getSimpleName());
	 
	   //�õ������ȫ·��
	   System.out.println(ElecTextForm.class);
	 
	   //�õ��������ģ��ʾ����Ҳ����Class
	   System.out.println(ElecTextForm.class.getClass());
	 
	   //�õ�Class�������
	   System.out.println(ElecTextForm.class.getClass().getName());
	 } 
	
}
