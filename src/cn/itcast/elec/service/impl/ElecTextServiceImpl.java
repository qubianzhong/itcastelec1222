package cn.itcast.elec.service.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.elec.dao.IElecTextDao;
import cn.itcast.elec.domain.ElecText;
import cn.itcast.elec.service.IElecTextService;
import cn.itcast.elec.web.form.ElecTextForm;

@Transactional(readOnly=true)
/**
 * SpringĬ��Transactional������������������׳��������������⣬�����ݻع� 
 * ���ﴦ�����������Exception���⣬�����ݲ������
 */
@Service(IElecTextService.SERVICE_NAME)  //������ཻ��spring����ȥ����
public class ElecTextServiceImpl implements IElecTextService{

	@Resource(name=IElecTextDao.SERVICE_NAME)
	private IElecTextDao elecTextDao;
	
	
	/**
	 * @Name:saveElecText
	 * @Description : ͨ������һ�����������б������
	 * @author ������
	 * @version :V1.0.0(�汾��)
	 * @Create Date ��2015-08-02
	 * @Parameters:ElecText elecText ʵ��
	 * @return:���� null
	 */
	@Override
	@Transactional(readOnly=false,isolation=Isolation.DEFAULT,propagation=Propagation.REQUIRED)
	public void saveElecText(ElecText elecText) {
		elecTextDao.save(elecText);
	}

	/**
	 * @Name:findCollectionByConditionNoPage
	 * @Description : ʹ�ò�ѯ������ѯ�б�ļ��ϣ�����ҳ��
	 * @author ������
	 * @version :V1.0.0(�汾��)
	 * @Create Date ��2015-08-02
	 * @Parameters: ElecTextForm elecTextForm VO����
	 * @return:List<ElecText> �б���
	 */
	@Override
	public List<ElecText> findCollectionByConditionNoPage(ElecTextForm elecTextForm) {
		/**
		 * ��֯HQL����where����
		 * 
		 * select * from elec_text o where 1=1		//����DAO��
		 * where o.textName like "%hello%"			//����Service��
		 * and o.textRemark like "%haha%"
		 * order by o.textDate desc , o.textName asc;
		 */
		String hqlWhere = "";
		List<String> paramsList = new ArrayList<String>();
		if(elecTextForm != null && StringUtils.isNotBlank(elecTextForm.getTextName())){
			hqlWhere += " and o.textName like ?";
			paramsList.add("%"+elecTextForm.getTextName()+"%");
		}
		if(elecTextForm != null && StringUtils.isNotBlank(elecTextForm.getTextRemark())){
			hqlWhere += " and o.textRemark like ?";
			paramsList.add("%"+elecTextForm.getTextRemark()+"%");
		}
		//��list���ϱ��һ������
		Object [] params = paramsList.toArray();
		/**
		 * ��֯�������
		 * 			order by o.textDate desc , o.textName asc;
		 */
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("o.textDate", "desc");
		orderby.put("o.textName", "asc");
		//��ѯ�б�
		List<ElecText> list = elecTextDao.findCollectionByConditionNoPage(hqlWhere,params,orderby);
		for(int i=0;list != null && i<list.size();i++){
			ElecText elecText = list.get(i);
			System.out.println(elecText.getTextName()+"      "+elecText.getTextRemark());
		}
		return list;
	}

}
