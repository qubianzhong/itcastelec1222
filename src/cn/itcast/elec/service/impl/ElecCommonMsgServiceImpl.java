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
import cn.itcast.elec.dao.IElecTextDao;
import cn.itcast.elec.domain.ElecCommonMsg;
import cn.itcast.elec.service.IElecCommonMsgService;
import cn.itcast.elec.web.form.ElecCommonMsgForm;

@Transactional(readOnly=true)
/**
 * SpringĬ��Transactional������������������׳��������������⣬�����ݻع� 
 * ���ﴦ�����������Exception���⣬�����ݲ������
 */
@Service(IElecCommonMsgService.SERVICE_NAME)  //������ཻ��spring����ȥ����
public class ElecCommonMsgServiceImpl implements IElecCommonMsgService{

	@Resource(name=IElecCommonMsgDao.SERVICE_NAME)
	private IElecCommonMsgDao elecCommonMsgDao;

	/**
	 * @Name:findElecCommonMsgList
	 * @Description : ��ѯ���еĴ�������
	 * @author ������
	 * @version :V1.0.0(�汾��)
	 * @Create Date ��2015-08-03
	 * @Parameters: null
	 * @return:List<ElecCommonMsgForm> �������˽�����б�
	 */
	@Override
	public List<ElecCommonMsgForm> findElecCommonMsgList() {
		String hqlWhere = "";
		Object [] params = null;
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put(" o.createDate", "desc");
		List<ElecCommonMsg> list = elecCommonMsgDao.findCollectionByConditionNoPage(hqlWhere, params, orderby);
		List<ElecCommonMsgForm> formList = this.elecCommonMsgPOListToVOList(list);
		return formList;
	}

	/**
	 * @Name:elecCommonMsgPOListToVOList
	 * @Description : ���������˵Ľ����Ϣ����PO����ת��ΪVO����
	 * @author ������
	 * @version :V1.0.0(�汾��)
	 * @Create Date ��2015-08-03
	 * @Parameters: List<ElecCommonMsg> list PO��������
	 * @return:List<ElecCommonMsgForm> VO��������
	 */
	private List<ElecCommonMsgForm> elecCommonMsgPOListToVOList(
			List<ElecCommonMsg> list) {
		
		List<ElecCommonMsgForm> formlist = new ArrayList<ElecCommonMsgForm>();
		ElecCommonMsgForm elecCommonMsgForm = null;
		for(int i=0;list !=null && i<list.size();i++){
			ElecCommonMsg elecCommonMsg = list.get(i);
			elecCommonMsgForm = new ElecCommonMsgForm();
			elecCommonMsgForm.setComID(elecCommonMsg.getComID());
			elecCommonMsgForm.setStationRun(elecCommonMsg.getStationRun());
			elecCommonMsgForm.setDevRun(elecCommonMsg.getDevRun());
			elecCommonMsgForm.setCreateDate(String.valueOf(elecCommonMsg.getCreateDate()!=null?elecCommonMsg.getCreateDate():""));
			formlist.add(elecCommonMsgForm);
		}
		return formlist;
	}

	/**
	 * @Name:saveElecCommonMsg
	 * @Description : �������������Ϣ
	 * @author ������
	 * @version :V1.0.0(�汾��)
	 * @Create Date ��2015-08-03
	 * @Parameters: ElecCommonMsgForm elecCommonMsgForm VO����ҳ�洫�ݵĲ���ֵ
	 * @return: String save �ض���actingIndex.jsp
	 */
	@Override
	@Transactional(isolation=Isolation.DEFAULT,propagation=Propagation.REQUIRED,readOnly=false)
	public void saveElecCommonMsg(ElecCommonMsgForm elecCommonMsgForm) {
		//VO����ת����PO����
		ElecCommonMsg elecCommonMsg = this.elecCommonMsgVOToPO(elecCommonMsgForm);
		elecCommonMsgDao.save(elecCommonMsg);
		
	}

	/**
	 * @Name:elecCommonMsgVOToPO
	 * @Description : ҳ�洫�ݵĴ���������Ϣ��VO����ת����PO����
	 * @author ������
	 * @version :V1.0.0(�汾��)
	 * @Create Date ��2015-08-03
	 * @Parameters: ElecCommonMsgForm elecCommonMsgForm VO����ҳ�洫�ݵĲ���ֵ
	 * @return: ElecCommonMsg  PO����
	 */
	private ElecCommonMsg elecCommonMsgVOToPO(
			ElecCommonMsgForm elecCommonMsgForm) {
		ElecCommonMsg elecCommonMsg = new ElecCommonMsg();
		if(elecCommonMsgForm != null){
			elecCommonMsg.setStationRun(elecCommonMsgForm.getStationRun());
			elecCommonMsg.setDevRun(elecCommonMsgForm.getDevRun());
			elecCommonMsg.setCreateDate(new Date());
		}
		return elecCommonMsg;
	}

	/**
	 * @Name:findElecCommonMsgListByCurrentDate
	 * @Description : ͨ����ǰ���ڲ�ѯ���������б�
	 * @author ������
	 * @version :V1.0.0(�汾��)
	 * @Create Date ��2015-08-03
	 * @Parameters: null
	 * @return: List<ElecCommonMsgForm> ���������б�
	 */
	@Override
	public List<ElecCommonMsgForm> findElecCommonMsgListByCurrentDate() {
		//��ȡ��ǰ����yyyy-MM-dd
		java.sql.Date date = new java.sql.Date(new java.util.Date().getTime());
		String currentDate = date.toString();
		List<Object[]> list = elecCommonMsgDao.findElecCommonMsgListByCurrentDate(currentDate);
		List<ElecCommonMsgForm> formList = this.elecCommonMsgObjectListToVOList(list);
		return formList;
	}

	/**
	 * @Name:elecCommonMsgObjectListToVOList
	 * @Description : ���������˲�ѯ�Ľ����Object[]ת����VO����
	 * @author ������
	 * @version :V1.0.0(�汾��)
	 * @Create Date ��2015-08-03
	 * @Parameters: List<Object[]> list ���Object[]�������
	 * @return: List<ElecCommonMsgForm> VO����
	 */
	private List<ElecCommonMsgForm> elecCommonMsgObjectListToVOList(
			List<Object[]> list) {
		List<ElecCommonMsgForm> formList = new ArrayList<ElecCommonMsgForm>();
		ElecCommonMsgForm elecCommonMsgForm = null;
		for(int i=0;list != null && i<list.size();i++){
			Object[] object = list.get(i);
			elecCommonMsgForm = new ElecCommonMsgForm();
			elecCommonMsgForm.setStationRun(object[0].toString());
			elecCommonMsgForm.setDevRun(object[1].toString());
			formList.add(elecCommonMsgForm);
		}
		return formList;
	}
	
	
	
	

}
