package cn.itcast.elec.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.elec.dao.IElecLogDao;
import cn.itcast.elec.domain.ElecLog;
import cn.itcast.elec.domain.ElecUser;
import cn.itcast.elec.service.IElecLogService;
import cn.itcast.elec.web.form.ElecLogForm;

/**
 * SpringĬ��Transactional������������������׳��������������⣬�����ݻع� 
 * ���ﴦ�����������Exception���⣬�����ݲ������
 */
@Transactional(readOnly=true)
@Service(IElecLogService.SERVICE_NAME)  //������ཻ��spring����ȥ����
public class ElecLogServiceImpl implements IElecLogService{

	@Resource(name=IElecLogDao.SERVICE_NAME)
	private IElecLogDao elecLogDao;

	/**
	 * @Name: saveElecLog
	 * @Description : ������־��Ϣ 
	 * @author �� ������
	 * @version :V1.0.0(�汾��)
	 * @Create Date ��2015-08-11
	 * @Parameters: HttpServletRequest request ���ڱ����û��������Ϣ
 					String string  ������ϸ
	 * @return: null
	 */
	@Override
	@Transactional(isolation=Isolation.DEFAULT,propagation=Propagation.REQUIRED,readOnly=false)
	public void saveElecLog(HttpServletRequest request, String details) {
		ElecLog elecLog = new ElecLog();
		elecLog.setIpAddress(request.getRemoteAddr());//IP��ַ

		ElecUser elecUser = (ElecUser) request.getSession().getAttribute("globle_user");
		elecLog.setOpeName(elecUser.getUserName());//������
		elecLog.setOpeTime(new Date());//����ʱ��
		elecLog.setDetails(details);
		elecLogDao.save(elecLog);
	}

	/**
	 * @Name: findElecLogListByCondition
	 * @Description : ʹ�ò�ѯ��������ѯ��־��Ϣ�б�
	 * @author �� ������
	 * @version :V1.0.0(�汾��)
	 * @Create Date ��2015-08-11
	 * @Parameters: ElecLogForm elecLogForm ���ڴ�Ų�������Ϣ
	 * @return: List<ElecLogForm> ��־��Ϣ�б�
	 */
	@Override
	public List<ElecLogForm> findElecLogListByCondition(ElecLogForm elecLogForm) {
		//��֯��ѯ�����������
		String hqlWhere = ""; 
		List<String> paramsList = new ArrayList<String>();
		if(elecLogForm != null && elecLogForm.getOpeName() != null && !elecLogForm.getOpeName().equals("")){
			hqlWhere += "and o.OpeName like ?";
			paramsList.add("%"+elecLogForm.getOpeName()+"%");
		}
		Object [] params = paramsList.toArray();
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("o.OpeName", "desc");
		List<ElecLog> list = elecLogDao.findCollectionByConditionNoPage(hqlWhere, params, orderby);
		//PO����Ľ����ת����VO����Ľ����
		List<ElecLogForm> formList = this.elecLogPOListToVOList(list);
		return formList;
	}

	/**
	 * @Name: elecLogPOListToVOList
	 * @Description : ��־��Ϣ�б��У�PO����Ľ����ת����VO����Ľ����
	 * @author �� ������
	 * @version :V1.0.0(�汾��)
	 * @Create Date ��2015-08-11
	 * @Parameters: List<ElecLog> list PO���󼯺�
	 * @return: List<ElecLogForm> VO���󼯺�
	 */
	private List<ElecLogForm> elecLogPOListToVOList(List<ElecLog> list) {
		List<ElecLogForm> formList = new ArrayList<ElecLogForm>();
		ElecLogForm elecLogForm = null;
		for(int i = 0;list !=null && i < list.size();i++){
			ElecLog elecLog = list.get(i);
			elecLogForm = new ElecLogForm();
			elecLogForm.setLogID(elecLog.getLogID());
			elecLogForm.setOpeName(elecLog.getOpeName());
			elecLogForm.setDetails(elecLog.getDetails());
			elecLogForm.setOpeTime(String.valueOf(elecLog.getOpeTime() != null ? elecLog.getOpeTime() : ""));
			elecLogForm.setIpAddress(elecLog.getIpAddress());
			formList.add(elecLogForm);
		}
		return formList;
	}

	/**
	 * @Name: deleteElecLogByLogIDs
	 * @Description : ʹ����־ID��ɾ����־�б���Ϣ
	 * @author �� ������
	 * @version :V1.0.0(�汾��)
	 * @Create Date ��2015-08-16
	 * @Parameters: ElecLogForm elecLogForm ���ڴ����־ID������
	 * @return: null
	 */
	@Override
	@Transactional(isolation=Isolation.DEFAULT,propagation=Propagation.REQUIRED,readOnly=false)
	public void deleteElecLogByLogIDs(ElecLogForm elecLogForm) {
		//��һ�ַ�ʽ
		// String [] logids = elecLogForm.getLogid();
		// elecLogDao.deleteObjectByIds(logids);
		//�ڶ��ַ�ʽ
		String logID = elecLogForm.getLogID();
		String [] logids = logID.split(",");
		for(int i = 0;logids != null && i<logids.length;i++){
			String logid = logids[i];
			logids[i] = logid.trim();
		}

		elecLogDao.deleteObjectByIds(logids);

	}
}
