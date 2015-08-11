package cn.itcast.elec.service.impl;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.elec.dao.IElecSystemDDlDao;
import cn.itcast.elec.dao.IElecUserDao;
import cn.itcast.elec.domain.ElecUser;
import cn.itcast.elec.service.IElecUserService;
import cn.itcast.elec.util.MD5keyBean;
import cn.itcast.elec.util.StringHelper;
import cn.itcast.elec.web.form.ElecUserForm;

/**
 * SpringĬ��Transactional������������������׳��������������⣬�����ݻع� 
 * ���ﴦ�����������Exception���⣬�����ݲ������
 */
@Transactional(readOnly=true)
@Service(IElecUserService.SERVICE_NAME)  //������ཻ��spring����ȥ����
public class ElecUserImpl implements IElecUserService{

	@Resource(name=IElecUserDao.SERVICE_NAME)
	private IElecUserDao elecUserDao;

	@Resource(name = IElecSystemDDlDao.SERVICE_NAME)
	private IElecSystemDDlDao elecSystemDDlDao;

	/**
	 * @Name: findELecUserList
	 * @Description :   ��ѯ�û��б���Ϣ
	 * 					�ж��û������Ƿ�Ϊ�գ������Ϊ�գ������û�������֯��ѯ����
	 * @author ������
	 * @version :V1.0.0(�汾��)
	 * @Create Date ��2015-08-06
	 * @Parameters: null
	 * @return:List<ElecUserForm> �û���Ϣ���������
	 */
	@Override
	public List<ElecUserForm> findELecUserList(ElecUserForm elecUserForm) {
		//��֯��ѯ����
		String hqlWhere = "";
		List<String> paramsList = new ArrayList<String>();
		if(elecUserForm != null && elecUserForm.getUserName() != null && !elecUserForm.getUserName().equals("")){
			hqlWhere += " and o.userName like ?";
			paramsList.add("%" + elecUserForm.getUserName() + "%");
		}
		Object[] params = paramsList.toArray();
		//��֯����
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("o.onDutyDate", "desc");
		List<ElecUser> list = elecUserDao.findCollectionByConditionNoPage(hqlWhere, params, orderby);
		List<ElecUserForm> formList = this.elecUserPOListToVOList(list);

		return formList;
	}

	/**
	 * @Name: elecUserPOListToVOList
	 * @Description :   ��ȡ���û��б��е�ֵ��PO����ת��ΪVO����
	 * @author ������
	 * @version :V1.0.0(�汾��)
	 * @Create Date ��2015-08-06
	 * @Parameters: List<ElecUser> list ���PO����
	 * @return:List<ElecUserForm> ���VO����
	 */
	private List<ElecUserForm> elecUserPOListToVOList(List<ElecUser> list) {
		List<ElecUserForm> formList = new ArrayList<ElecUserForm>();
		ElecUserForm elecUserForm = null;
		for(int i=0; list != null && i<list.size();i++){
			elecUserForm = new ElecUserForm();
			ElecUser elecUser = list.get(i);
			elecUserForm.setUserID(elecUser.getUserID());
			elecUserForm.setContactTel(elecUser.getContactTel());
			elecUserForm.setLogonName(elecUser.getLogonName());
			elecUserForm.setUserName(elecUser.getUserName());
			elecUserForm.setSexID(elecUser.getSexID() != null && !elecUser.getSexID().equals("") ? elecSystemDDlDao.findDDlName(elecUser.getSexID(),"�Ա�"):"");
			elecUserForm.setIsDuty(elecUser.getIsDuty() != null && !elecUser.getIsDuty().equals("") ? elecSystemDDlDao.findDDlName(elecUser.getIsDuty(),"�Ƿ���ְ"):"");
			formList.add(elecUserForm);
		}
		return formList;
	}

	/**
	 * @Name: saveElecUser
	 * @Description :   �����û���Ϣ
	 * @author ������
	 * @version :V1.0.0(�汾��)
	 * @Create Date ��2015-08-06
	 * @Parameters: ElecUserForm elecUserForm  VO�������ڴ���û���Ϣ
	 * @return:null
	 */
	@Override
	@Transactional(isolation=Isolation.DEFAULT,propagation=Propagation.REQUIRED,readOnly=false)
	public void saveElecUser(ElecUserForm elecUserForm) {
		//VO����ת��ΪPO����
		ElecUser elecUser = this.elecUserVOToPO(elecUserForm);
		if(elecUserForm!=null && elecUserForm.getUserID()==null){
			elecUserDao.save(elecUser);
		}else{
			elecUserDao.update(elecUser);
		}
	}

	/**
	 * @Name: elecUserVOToPO
	 * @Description :   ���û���Ϣ��VO����ת����PO����
	 * @author ������
	 * @version :V1.0.0(�汾��)
	 * @Create Date ��2015-08-06
	 * @Parameters: ElecUserForm elecUserForm  VO�������ڴ���û���Ϣ
	 * @return: ElecUser
	 */
	private ElecUser elecUserVOToPO(ElecUserForm elecUserForm) {
		ElecUser elecUser = new ElecUser();
		if(elecUserForm != null){
			if(elecUserForm != null && !elecUserForm.equals("")){
				elecUser.setUserID(elecUserForm.getUserID());
				if(elecUserForm.getOffDutyDate()!=null && !elecUserForm.getOffDutyDate().equals("")){
					elecUser.setOffDutyDate(StringHelper.stringConvertDate(elecUserForm.getOffDutyDate()));
				}
			}
			elecUser.setJctID(elecUserForm.getJctID());
			elecUser.setUserName(elecUserForm.getUserName());
			elecUser.setLogonName(elecUserForm.getLogonName());
			//2015-08-09 �޸ģ�ʹ��MD5�����������
			String password = elecUserForm.getLogonPwd();
			String md5password = "";
			//2015-08-09 �޸ģ���ʼ������ 000000
			if(password == null || "".equals(password)){
				password = "000000";
			}
			String md5flag = elecUserForm.getMd5flag();
			//�޸�ʱ2������һ�£�����Ҫ�����������
			if(md5flag != null && md5flag.equals("1")){
				md5password = password;
			}
			//���������������Ǵ����޸ģ��޸��˵�ǰ���룩������Ҫ�����������
			else{
				MD5keyBean md5 = new MD5keyBean();
				md5password = md5.getkeyBeanofStr(password);
			}
			elecUser.setLogonPwd(md5password);
			//end
			//�ж��Ƿ���д�Ա𣬼��Ƿ�Ϊ��
			if(elecUserForm.getSexID() !=null && !elecUserForm.getSexID().equals(""))
				elecUser.setSexID(Integer.valueOf(elecUserForm.getSexID()));
			//�ж��Ƿ���д�������ڣ����Ƿ�Ϊ��
			if(elecUserForm.getBirthday() !=null && !elecUserForm.getBirthday().equals(""))
				elecUser.setBirthday(StringHelper.stringConvertDate(elecUserForm.getBirthday()));
			elecUser.setAddress(elecUserForm.getAddress());
			elecUser.setContactTel(elecUserForm.getContactTel());
			elecUser.setEmail(elecUserForm.getEmail());
			elecUser.setMobile(elecUserForm.getMobile());
			elecUser.setIsDuty(Integer.valueOf(elecUserForm.getIsDuty()));
			if(elecUserForm.getOnDutyDate() != null && !elecUserForm.getOnDutyDate().equals("")){
				elecUser.setOnDutyDate(StringHelper.stringConvertDate(elecUserForm.getOnDutyDate()));
			}
			elecUser.setRemark(elecUserForm.getRemark());
		}
		return elecUser;
	}

	/**
	 * @Name: findELecUser
	 * @Description :  ʹ���û�ID���в�ѯ����ȡ�û�����ϸ��Ϣ
	 * @author ������
	 * @version :V1.0.0(�汾��)
	 * @Create Date ��2015-08-06
	 * @Parameters: ElecUserForm elecUserForm  VO�������ڴ���û�ID
	 * @return: ElecUserForm VO�������û�����ϸ��Ϣ
	 */
	@Override
	public ElecUserForm findELecUser(ElecUserForm elecUserForm) {
		String userID = elecUserForm.getUserID();
		ElecUser elecUser = elecUserDao.findObjectById(userID);
		//PO����ת����PO����
		elecUserForm = this.elecUserPOToVO(elecUser,elecUserForm);
		return elecUserForm;
	}

	/**
	 * @Name: elecUserPOToVO
	 * @Description : ��ȡ�û�����ϸ��Ϣ,��PO����ת����VO����
	 * @author ������
	 * @version :V1.0.0(�汾��)
	 * @Create Date ��2015-08-06
	 * @Parameters: ElecUser elecUser  PO�������û���ϸ��Ϣ
	 * @return: ElecUserForm VO�������û�����ϸ��Ϣ
	 */
	private ElecUserForm elecUserPOToVO(ElecUser elecUser,ElecUserForm elecUserForm) {
		//		ElecUserForm elecUserForm = new ElecUserForm();
		if(elecUser != null){
			elecUserForm.setJctID(elecUser.getJctID());
			elecUserForm.setUserName(elecUser.getUserName());
			elecUserForm.setLogonName(elecUser.getLogonName());
			elecUserForm.setLogonPwd(elecUser.getLogonPwd());
			elecUserForm.setSexID(String.valueOf(elecUser.getSexID()));
			elecUserForm.setBirthday(String.valueOf(elecUser.getBirthday() !=null && !elecUser.getBirthday().equals("")?elecUser.getBirthday():""));
			elecUserForm.setAddress(elecUser.getAddress());
			elecUserForm.setContactTel(elecUser.getContactTel());
			elecUserForm.setEmail(elecUser.getEmail());
			elecUserForm.setMobile(elecUser.getMobile());
			elecUserForm.setIsDuty(String.valueOf(elecUser.getIsDuty()));
			elecUserForm.setOnDutyDate(String.valueOf(elecUser.getOnDutyDate() !=null && !elecUser.getOnDutyDate().equals("")?elecUser.getOnDutyDate():""));
			elecUserForm.setRemark(elecUser.getRemark());
		}
		return elecUserForm;
	}

	/**
	 * @Name: deleteElecUser
	 * @Description : ��ҳ���л�ȡUserID��ֵ��ͨ��UserID��ɾ���û���Ϣ��
	 * @author ������
	 * @version :V1.0.0(�汾��)
	 * @Create Date ��2015-08-07
	 * @Parameters: ElecUserForm elecUserForm  VO�������û�ID
	 * @return: null
	 */
	@Override
	@Transactional(isolation=Isolation.DEFAULT,propagation=Propagation.REQUIRED,readOnly=false)
	public void deleteElecUser(ElecUserForm elecUserForm) {
		String userID = elecUserForm.getUserID();
		elecUserDao.deleteObjectByIds(userID);
	}

	/**
	 * @Name: checkLogonName
	 * @Description : ͨ��ʹ�õ�¼����ѯ���ݿ⣬��Ϊ�����жϵ�ǰ��¼�������ݿ����Ƿ���ڡ�
	 * @author ������
	 * @version :V1.0.0(�汾��)
	 * @Create Date ��2015-08-07
	 * @Parameters: String logonName  ��¼��
	 * @return: String 		checkflag �����ֵΪ1��˵����ǰ��¼�������ݿ������ظ���¼�����ܽ��б���
	 *	 					checkflag : ���ֻΪ2��˵����ǰ��¼�������ݿ��������ظ�ֵ�����Խ��б���
	 */
	@Override
	public String checkLogonName(String logonName) {
		String hqlWhere = " and o.logonName = ?";
		Object [] params = {logonName};
		List<ElecUser> list = elecUserDao.findCollectionByConditionNoPage(hqlWhere, params, null);
		String checkflag = "";
		if(list !=null && list.size()>0){
			checkflag = "1";
		}else{
			checkflag = "2";
		}
		return checkflag;
	}
	/**
	 * @Name: findElecUserByLogonName
	 * @Description : ͨ��ʹ�õ�¼����ȡ�û�����ϸ��Ϣ��������ҳ��¼��У��
	 * @author ������
	 * @version :V1.0.0(�汾��)
	 * @Create Date ��2015-08-10
	 * @Parameters: String name  ��¼��
	 * @return: ElecUser 	����û�����ϸ��Ϣ	
	 */
	@Override
	public ElecUser findElecUserByLogonName(String name) {
		String hqlWhere = " and o.logonName = ?";
		Object [] params = {name};
		List<ElecUser> list = elecUserDao.findCollectionByConditionNoPage(hqlWhere, params, null);
		ElecUser elecUser = null;
		if(list != null && list.size()>0){
			elecUser = list.get(0);
		}
		return elecUser;
	}

	/**
	 * @Name: findElecPopedomByLogonName
	 * @Description : ʹ�õ�¼����ȡ��ǰ�û������е�Ȩ��
	 * @author ������
	 * @version :V1.0.0(�汾��)
	 * @Create Date ��2015-08-10
	 * @Parameters: String name  ��¼��
	 * @return: String 	�û���Ÿ��û����е�Ȩ��	
	 */
	@Override
	public String findElecPopedomByLogonName(String name) {
		List<Object> list = elecUserDao.findElecPopedomByLogonName(name);
		StringBuffer buffer = new StringBuffer("");
		for(int i = 0;list != null && i<list.size();i++){
			Object object = list.get(i);
			buffer.append(object.toString());
		}
		return buffer.toString();
	}

	/**
	 * @Name: findElecRoleByLogonName
	 * @Description : ʹ�õ�¼����ȡ��ǰ�û������еĽ�ɫ
	 * @author ������
	 * @version :V1.0.0(�汾��)
	 * @Create Date ��2015-08-10
	 * @Parameters: String name  ��¼��
	 * @return: Hashtable<String, String> 	��Ž�ɫ�ļ���	
	 */
	@Override
	public Hashtable<String, String> findElecRoleByLogonName(String name) {
		List<Object []> list = elecUserDao.findElecRoleByLogonName(name);
		Hashtable<String, String> ht = null;
		if(list != null && list.size()>0){
			ht = new Hashtable<String, String>();
			for(int i=0;i<list.size();i++){
				Object[] object = list.get(i);
				ht.put(object[0].toString(), object[1].toString());
			}
		}
		return ht;
	}



}
