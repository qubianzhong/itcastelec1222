package cn.itcast.elec.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.elec.dao.IElecCommonMsgDao;
import cn.itcast.elec.dao.IElecSystemDDlDao;
import cn.itcast.elec.dao.IElecTextDao;
import cn.itcast.elec.domain.ElecCommonMsg;
import cn.itcast.elec.domain.ElecSystemDDl;
import cn.itcast.elec.service.IElecCommonMsgService;
import cn.itcast.elec.service.IElecSystemDDlService;
import cn.itcast.elec.web.form.ElecCommonMsgForm;
import cn.itcast.elec.web.form.ElecSystemDDlForm;

/**
 * SpringĬ��Transactional������������������׳��������������⣬�����ݻع� 
 * ���ﴦ�����������Exception���⣬�����ݲ������
 */
@Transactional(readOnly=true)
@Service(IElecSystemDDlService.SERVICE_NAME)  //������ཻ��spring����ȥ����
public class ElecSystemDDlServiceImpl implements IElecSystemDDlService{

	@Resource(name=IElecSystemDDlDao.SERVICE_NAME)
	private IElecSystemDDlDao elecSystemDDlDao;


	/**
	 * @Name:findKeyWord
	 * @Description : ��ѯ�������͹ؼ��֣���ȥ���ظ�ֵ
	 * @author ������
	 * @version :V1.0.0(�汾��)
	 * @Create Date ��2015-08-04
	 * @Parameters: null
	 * @return:List<ElecSystemDDlForm> ���������б�
	 */
	@Override
	public List<ElecSystemDDlForm> findKeyWord() {
		List<Object> list = elecSystemDDlDao.findKeyWord();
		List<ElecSystemDDlForm> formList = this.elecSystemDDlObjectToVO(list);
		return formList;
	}

	/**
	 * @Name:elecSystemDDlObjectToVO
	 * @Description : ����ѯ�����������б���Object ����ת����VO����
	 * @author ������
	 * @version :V1.0.0(�汾��)
	 * @Create Date ��2015-08-04
	 * @Parameters: List<Object> list Object����
	 * @return:List<ElecSystemDDlForm> VO����
	 */
	private List<ElecSystemDDlForm> elecSystemDDlObjectToVO(List<Object> list) {
		List<ElecSystemDDlForm> formList = new ArrayList<ElecSystemDDlForm>();
		ElecSystemDDlForm elecSystemDDlForm = null;
		for(int i = 0 ;list != null && i<list.size();i++){
			Object object = list.get(i);
			elecSystemDDlForm = new ElecSystemDDlForm();
			elecSystemDDlForm.setKeyword(object.toString());
			formList.add(elecSystemDDlForm);
		}
		return formList;
	}

	/**
	 * @Name:findElecSystemDDLListByKeyWord
	 * @Description : ����ѡ���������ͣ���ѯ��Ӧ��������
	 * @author ������
	 * @version :V1.0.0(�汾��)
	 * @Create Date ��2015-08-04
	 * @Parameters: String keyWord VO������
	 * @return:List<ElecSystemDDlForm> ��Ӧ�������VO����
	 */
	@Override
	@Transactional(isolation=Isolation.DEFAULT,propagation=Propagation.REQUIRED,readOnly=false)
	public List<ElecSystemDDlForm> findElecSystemDDLListByKeyWord(String keyword) {
		List<ElecSystemDDl> list = this.findSystemDDlListByCondition(keyword);
		List<ElecSystemDDlForm> formList = this.elecSystemDDlPOListToVOList(list);
		return formList;
	}

	/**
	 * @Name: findSystemDDlListByCondition
	 * @Description : ����ѡ���������ͣ���ѯ��Ӧ��������,��ȡ������ļ����б�
	 * @author ������
	 * @version :V1.0.0(�汾��)
	 * @Create Date ��2015-08-06
	 * @Parameters: String keyword ��������
	 * @return:List<ElecSystemDDl> ��Ӧ�������VO����
	 */
	private List<ElecSystemDDl> findSystemDDlListByCondition(String keyword) {
		String hqlWhere = " and o.keyword = ?";
		Object [] params = {keyword};
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("o.ddlCode", "asc");
		List<ElecSystemDDl> list = elecSystemDDlDao.findCollectionByConditionNoPage(hqlWhere, params, orderby);
		return list;
	}

	/**
	 * @Name: elecSystemDDlPOListToVOList
	 * @Description : ������ļ����б��PO����ת��ΪVO����
	 * @author ������
	 * @version :V1.0.0(�汾��)
	 * @Create Date ��2015-08-04
	 * @Parameters: List<ElecSystemDDl> list ���PO����
	 * @return:List<ElecSystemDDlForm> ��Ӧ�������VO����
	 */
	private List<ElecSystemDDlForm> elecSystemDDlPOListToVOList(
			List<ElecSystemDDl> list) {
		List<ElecSystemDDlForm> formList = new ArrayList<ElecSystemDDlForm>();
		ElecSystemDDlForm elecSystemDDlForm = null;
		for(int i = 0;list !=null && i<list.size();i++){
			ElecSystemDDl elecSystemDDl = list.get(i);
			elecSystemDDlForm = new ElecSystemDDlForm();
			elecSystemDDlForm.setSeqID(String.valueOf(elecSystemDDl.getSeqID()));
			elecSystemDDlForm.setKeyword(elecSystemDDl.getKeyword());
			elecSystemDDlForm.setDdlCode(String.valueOf(elecSystemDDl.getDdlCode()));
			elecSystemDDlForm.setDdlName(elecSystemDDl.getDdlName());
			formList.add(elecSystemDDlForm);
		}
		return formList;
	}

	/**
	 * @Name: saveElecSystemDDl
	 * @Description : ���������ֵ䣬����ʱ�����������ͣ��������š�����������
	 * @author ������
	 * @version :V1.0.0(�汾��)
	 * @Create Date ��2015-08-04
	 * @Parameters: ElecSystemDDlForm elecSystemDDlForm ���ҳ�洫�ݵı�ֵ
	 * @return:null
	 */
	@Override
	@Transactional(isolation=Isolation.DEFAULT,propagation=Propagation.REQUIRED,readOnly=false)
	public void saveElecSystemDDl(ElecSystemDDlForm elecSystemDDlForm) {
		//��ȡҳ�洫�ݵı�ֵ
		//��ȡ��������
		String keyword = elecSystemDDlForm.getKeywordname();
		//��ȡ�ж������������������ͻ�����ԭ�е����������б༭�ı�ʶ
		String typeflag = elecSystemDDlForm.getTypeflag();
		//��ȡ��Ҫ����������������
		String [] itemname = elecSystemDDlForm.getItemname();
		//����������������͵Ĳ�������ʱtypeflag==new
		
		System.out.println(keyword+"..."+typeflag+"..."+itemname[0]+"...");
		
		if(typeflag != null && typeflag.equals("new")){
			this.saveSystemDDlWithParams(keyword,itemname);
		}
		//�����Ǳ�ʾ��ԭ�е����������н����޸ĺͱ༭����ʱtypeflag==and
		else{
			List<ElecSystemDDl> list = findSystemDDlListByCondition(keyword);
			elecSystemDDlDao.deleteObjectByCollection(list);
			//���������ֵ�
			this.saveSystemDDlWithParams(keyword, itemname);
		}
		
	}

	/**
	 * @Name: saveSystemDDlWithParams
	 * @Description : ͨ��ҳ�洫�ݵĲ������������ֵ�
	 * @author ������
	 * @version :V1.0.0(�汾��)
	 * @Create Date ��2015-08-04
	 * @Parameters: String keyWord    ��������
	 * 				String[] itemname ����������
	 * @return:null
	 */
	@Transactional(isolation=Isolation.DEFAULT,propagation=Propagation.REQUIRED,readOnly=false)
	private void saveSystemDDlWithParams(String keyWord, String[] itemname) {
		List<ElecSystemDDl> list = new ArrayList<ElecSystemDDl>();
		for(int i = 0; itemname !=null && i<itemname.length; i++){
			ElecSystemDDl elecSystemDDl = new ElecSystemDDl();
			elecSystemDDl.setKeyword(keyWord);
			elecSystemDDl.setDdlName(itemname[i]);
			elecSystemDDl.setDdlCode(new Integer(i+1));
			list.add(elecSystemDDl);
//			elecSystemDDlDao.save(elecSystemDDl);
		}
		elecSystemDDlDao.saveObjectByCollection(list);
	}


}
