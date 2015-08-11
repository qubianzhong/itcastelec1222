package cn.itcast.elec.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.elec.dao.IElecRolePopedomDao;
import cn.itcast.elec.dao.IElecUserRoleDao;
import cn.itcast.elec.domain.ElecRolePopedom;
import cn.itcast.elec.domain.ElecUserRole;
import cn.itcast.elec.service.IElecRoleService;
import cn.itcast.elec.util.XMLObject;
import cn.itcast.elec.web.form.ElecRoleForm;
import cn.itcast.elec.web.form.ElecUserForm;

/**
 * SpringĬ��Transactional������������������׳��������������⣬�����ݻع� 
 * ���ﴦ�����������Exception���⣬�����ݲ������
 */
@Transactional(readOnly=true)
@Service(IElecRoleService.SERVICE_NAME)  //������ཻ��spring����ȥ����
public class ElecRoleServiceImpl implements IElecRoleService{

	@Resource(name=IElecUserRoleDao.SERVICE_NAME)
	private IElecUserRoleDao elecUserRoleDao;
	
	@Resource(name = IElecRolePopedomDao.SERVICE_NAME)
	private IElecRolePopedomDao elecRolePopedomDao;

	/**
	 * @Name: readXml
	 * @Description :   ��Function.xml�ļ��в�ѯϵͳ�����еĹ���Ȩ��
	 * 					��ŵ�XMLObject������
	 * @author ������
	 * @version :V1.0.0(�汾��)
	 * @Create Date ��2015-08-08
	 * @Parameters: null
	 * @return:List<ElecUserForm> �û���Ϣ���������
	 */
	@Override
	public List<XMLObject> readXml() {
		//File f = new File("H:\\apache-tomcat-7.0.55\\me-webapps\\itcast1222elec\\WEB-INF\\classes\\Function.xml");
		ServletContext servletContext = ServletActionContext.getServletContext();
		String realpath = servletContext.getRealPath("/WEB-INF/classes/Function.xml");
		File f = new File(realpath);
		List<XMLObject> xmlList = new ArrayList<XMLObject>();
		//ʹ��dom4j��ȡ�����ļ�
		try {
			SAXReader reader = new SAXReader();
			Document document = reader.read(realpath);
			Element element = document.getRootElement();
			XMLObject xmlObject = null;
			/**
			 * Function��Ӧ�����ļ��е�FunctionԪ�ؽڵ�
			 * FunctionCode��Ӧ�����ļ���FunctionԪ�ؽڵ��µ�FunctionCodeԪ�ؽڵ�
			 * FunctionName��Ӧ�����ļ���FunctionԪ�ؽڵ��µ�FunctionNameԪ�ؽڵ�
			 * ParentCode��Ӧ�����ļ���FunctionԪ�ؽڵ��µ�ParentCodeԪ�ؽڵ�
			 * ParentName��Ӧ�����ļ���FunctionԪ�ؽڵ��µ�ParentNameԪ�ؽڵ�
			 */
			for(Iterator<Element> iter = element.elementIterator("Function");iter.hasNext();){
				Element xmlElement = iter.next();
				xmlObject = new XMLObject();
				xmlObject.setCode(xmlElement.elementText("FunctionCode"));
				xmlObject.setName(xmlElement.elementText("FunctionName"));
				xmlObject.setParentCode(xmlElement.elementText("ParentCode"));
				xmlObject.setParentName(xmlElement.elementText("ParentName"));
				xmlList.add(xmlObject);
			}
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return xmlList;
	}

	/**
	 * @Name: readEditXml
	 * @Description :   ʹ�ý�ɫID��ѯ�ý�ɫ�¾��е�Ȩ�ޣ�����ϵͳ�����е�Ȩ�޽���ƥ��
	 * @author ������
	 * @version :V1.0.0(�汾��)
	 * @Create Date ��2015-08-08 ���������ڣ�
	 * @Parameters: String roleID ��ɫID
	 * @return: List<XMLObject> Ȩ�޼��ϣ�ƥ����ɣ�
	 */
	@Override
	public List<XMLObject> readEditXml(String roleID) {
		// ʹ��roleID��ѯ�ý�ɫ�¾��е�Ȩ��
		ElecRolePopedom elecRolePopedom = elecRolePopedomDao.findObjectById(roleID);
		String popedom = "";
		if(elecRolePopedom != null){
			popedom = elecRolePopedom.getPopedomcode();
		}
		//��ϵͳ�����е�Ȩ�޽���ƥ��
		List<XMLObject> list = this.readXmlByPopedom(popedom);
		return list;
	}

	/**
	 * @Name: readXmlByPopedom
	 * @Description :  ��ȡ�����ļ�����ȡϵͳ�е�����Ȩ�ޣ��뵱ǰ�����е�Ȩ�޽���ƥ��
	 * 				   ���ƥ�䲻�ɹ���   flag = 0����ʾ�ý�ɫ������Ȩ�ޣ���ҳ����Ȩ�޸�ѡ�򲻱�ѡ��
	 *               ���ƥ��ɹ���flag = 1����ʾ�ý�ɫ���д�Ȩ�ޣ���ҳ����Ȩ�޸�ѡ��ѡ��
	 * @author ������
	 * @version :V1.0.0(�汾��)
	 * @Create Date ��2015-08-08 ���������ڣ�
	 * @Parameters: String popedom ��ɫID
	 * @return: List<XMLObject> Ȩ�޼��ϣ�ƥ����ɣ�
	 */
	private List<XMLObject> readXmlByPopedom(String popedom) {
		List<XMLObject> list = new ArrayList<XMLObject>();
		List<XMLObject> xmlList = this.readXml();
		for(int i=0;xmlList != null && i<xmlList.size();i++){
			XMLObject object = xmlList.get(i);
			//��ʾ��ǰȨ�ޱ�ѡ��
			if(popedom.contains(object.getCode())){
				object.setFlag("1");
			}else{
				object.setFlag("0");
			}
			list.add(object);
		}
		return list;
	}

	/**
	 * @Name: findElecUserByRoleID
	 * @Description :  ��ѯ�û��б��ϣ���ȡϵͳ�е����е��û�������ý�ɫ���е��û�����ƥ��
	 * 				        ���ƥ�䲻�ɹ���   flag = 0����ʾ�ý�ɫ��ӵ�е��û�����ҳ�����û���ѡ�򲻱�ѡ��
	 *                 ���ƥ��ɹ���flag = 1����ʾ�ý�ɫӵ�д��û�����ҳ�����û���ѡ��ѡ��
	 * @author ������
	 * @version :V1.0.0(�汾��)
	 * @Create Date ��2015-08-08 ���������ڣ�
	 * @Parameters: String roleID ��ɫID
	 * @return: ist<ElecUserForm> �û����ϣ�ƥ����ɣ�
	 */
	@Override
	public List<ElecUserForm> findElecUserByRoleID(String roleID) {
		List<Object []> list = elecUserRoleDao.findElecUserListByRoleID(roleID); 
		List<ElecUserForm> formList = this.elecUserRoleObjectListToVOList(list);
		return formList;
	}

	/**
	 * @Name: elecUserRoleObjectListToVOList
	 * @Description :  ����ȡ�����û��б���Ϣ��Object����ת����VO����
	 * @author ������
	 * @version :V1.0.0(�汾��)
	 * @Create Date ��2015-08-08 ���������ڣ�
	 * @Parameters: List<Object[]> list PO���󼯺�
	 * @return: ist<ElecUserForm> VO���󼯺ϣ�ƥ����ɣ�
	 */
	private List<ElecUserForm> elecUserRoleObjectListToVOList(
			List<Object[]> list) {
		List<ElecUserForm> formList = new ArrayList<ElecUserForm>();
		ElecUserForm elecUserForm = null;
		for(int i=0;list != null && i<list.size();i++){
			Object [] objects = list.get(i);
			elecUserForm = new ElecUserForm();
			elecUserForm.setFlag(objects[0].toString());
			elecUserForm.setUserID(objects[1].toString());
			elecUserForm.setUserName(objects[2].toString());
			elecUserForm.setLogonName(objects[3].toString());
			formList.add(elecUserForm);
		}
		return formList;
	}

	/**
	 * @Name: saveRole
	 * @Description :  �����ɫ��Ȩ�޵Ĺ�����  && �����û��ͽ�ɫ�Ĺ�����
	 * @author ������
	 * @version :V1.0.0(�汾��)
	 * @Create Date ��2015-08-08 ���������ڣ�
	 * @Parameters: ElecRoleForm elecRoleForm VO���󣬴�Ž�ɫID��Ȩ��code���顢�û�ID����
	 * @return: null
	 */
	@Override
	@Transactional(isolation=Isolation.DEFAULT,propagation=Propagation.REQUIRED,readOnly=false)
	public void saveRole(ElecRoleForm elecRoleForm) {
		// �����ɫ��Ȩ�޵Ĺ�����
		this.saveRolePopedom(elecRoleForm);
		// �����û��ͽ�ɫ�Ĺ�����
		this.saveUserRole(elecRoleForm);
		
	}

	/**
	 * @Name: saveUserRole
	 * @Description :  �����û��ͽ�ɫ�Ĺ�����
	 * @author ������
	 * @version :V1.0.0(�汾��)
	 * @Create Date ��2015-08-08 ���������ڣ�
	 * @Parameters: ElecRoleForm elecRoleForm VO���󣬴�Ž�ɫID��Ȩ��code���顢�û�ID����
	 * @return: null
	 */
	private void saveUserRole(ElecRoleForm elecRoleForm) {
		//��ɫID
		String roleid = elecRoleForm.getRoleid();
		//�û�ID����
		String [] selectuser = elecRoleForm.getSelectuser();
		/**
		 * ��roleID��Ϊ��������ѯ�û��ͽ�ɫ�Ĺ�������ȡ�û��ͽ�ɫ�����ļ��϶���
		 */
		String hqlWhere = " and o.roleID = ? ";
		Object [] params = {roleid};
		List<ElecUserRole> entities = elecUserRoleDao.findCollectionByConditionNoPage(hqlWhere, params, null);
		/**
		 * ��roleIDΪ������ɾ���û��ͽ�ɫ�Ĺ�����
		 */
		elecUserRoleDao.deleteObjectByCollection(entities);
		//�����û��ͽ�ɫ�Ĺ�����
		List<ElecUserRole> list = new ArrayList<ElecUserRole>();
		for(int i=0;selectuser != null && i<selectuser.length;i++){
			String userID = selectuser[i];
			ElecUserRole elecUserRole = new ElecUserRole();
			elecUserRole.setRoleID(roleid);
			elecUserRole.setUserID(userID);
			list.add(elecUserRole);
//			elecUserRoleDao.save(elecUserRole);
		}
		elecUserRoleDao.saveObjectByCollection(list);
	}

	/**
	 * @Name: saveRolePopedom
	 * @Description :   �����ɫ��Ȩ�޵Ĺ�����
	 * @author ������
	 * @version :V1.0.0(�汾��)
	 * @Create Date ��2015-08-08 ���������ڣ�
	 * @Parameters: ElecRoleForm elecRoleForm VO���󣬴�Ž�ɫID��Ȩ��code���顢�û�ID����
	 * @return: null
	 */
	private void saveRolePopedom(ElecRoleForm elecRoleForm) {
		// ��ɫID
		String roleid = elecRoleForm.getRoleid();
		//Ȩ��code����
		String [] selectoper = elecRoleForm.getSelectoper();
		StringBuffer popedom = new StringBuffer("");
		for(int i = 0;selectoper != null && i<selectoper.length;i++){
			popedom.append(selectoper[i]);
		}
		//ʹ�ý�ɫID��ѯ��ɫ��Ȩ�޵Ĺ�����
		ElecRolePopedom elecRolePopedom = elecRolePopedomDao.findObjectById(roleid);
		//˵����ɫ��Ȩ�޹������д��ڸý�ɫ�ļ�¼����ʱִ��update�Ĳ���
		if(elecRolePopedom != null){
			elecRolePopedom.setPopedomcode(popedom.toString());
			elecRolePopedomDao.update(elecRolePopedom);
		}
		//˵����ɫ��Ȩ�޹������в����ڸý�ɫ�ļ�¼����ʱִ��save�Ĳ���
		else{
			elecRolePopedom = new ElecRolePopedom();
			elecRolePopedom.setRoleID(roleid);
			elecRolePopedom.setPopedomcode(popedom.toString());
			elecRolePopedomDao.save(elecRolePopedom);
		}
	}	
	
}
