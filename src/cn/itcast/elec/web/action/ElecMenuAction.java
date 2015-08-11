package cn.itcast.elec.web.action;

import java.io.UnsupportedEncodingException;
import java.util.Hashtable;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.itcast.elec.container.ServiceProvider;
import cn.itcast.elec.domain.ElecUser;
import cn.itcast.elec.service.IElecCommonMsgService;
import cn.itcast.elec.service.IElecLogService;
import cn.itcast.elec.service.IElecUserService;
import cn.itcast.elec.service.impl.ElecLogServiceImpl;
import cn.itcast.elec.util.LogonUtils;
import cn.itcast.elec.util.MD5keyBean;
import cn.itcast.elec.web.form.ElecCommonMsgForm;
import cn.itcast.elec.web.form.ElecMenuForm;

import com.opensymphony.xwork2.ModelDriven;

@SuppressWarnings("serial")
public class ElecMenuAction extends BaseAction implements ModelDriven<ElecMenuForm>{
	//���ò˵�ģ���ҵ���
	private ElecMenuForm elecMenuForm = new ElecMenuForm();
	//�����û���¼ģ���ҵ���
	private IElecUserService elecUserService = (IElecUserService)ServiceProvider.getService(IElecUserService.SERVICE_NAME);
	//���ô������˵�ҵ���
	private IElecCommonMsgService elecCommonMsgService = (IElecCommonMsgService) ServiceProvider.getService(IElecCommonMsgService.SERVICE_NAME);
	//������־�����ҵ���
	private IElecLogService elecLogService = (IElecLogService) ServiceProvider.getService(IElecLogService.SERVICE_NAME);
	//ʹ��log4j
	private Log log = LogFactory.getLog(ElecMenuAction.class);
	
	@Override
	public ElecMenuForm getModel() {
		// TODO Auto-generated method stub
		return elecMenuForm;
	}

	/**
	 * @Name: home
	 * @Description :   �ӵ�¼ҳ���ȡ��¼�������룬��֤�Ƿ�Ϸ�
	 * 					����Ϸ�������֤�ɹ�����ת��home.jsp
	 * 					������Ϸ�������֤ʧ�ܣ����˵�index.jsp
	 * @author ������
	 * @version :V1.0.0(�汾��)
	 * @Create Date ��2015-08-10
	 * @Parameters: null
	 * @return: String home 
							��֤�ɹ�����ת��home.jsp
							��֤ʧ�ܣ����˵�index.jsp
	 */
	public String home(){
		//2015-08-11,�����֤��Ĺ��� begin
		boolean flag = LogonUtils.checkNumber(request);
		if(!flag){
			this.addFieldError("error", "��֤��Ϊ�ջ�������");
			return "error";
		}
		//end
		
		//��ȡ��ǰ��¼��������
		String name = elecMenuForm.getName();
		String password = elecMenuForm.getPassword();
		MD5keyBean md5 = new MD5keyBean();
		String md5password = md5.getkeyBeanofStr(password);
		//ʹ�õ�¼����ѯ���ݿ⣬��ȡ�û�����ϸ��Ϣ
		ElecUser elecUser = elecUserService.findElecUserByLogonName(name);
		if(elecUser == null){
			this.addFieldError("error", "����ǰ�ĵ�¼�������ڣ�");
			return "error";
		}    
		if(password == null || password.equals("") || !elecUser.getLogonPwd().equals(md5password)){
			this.addFieldError("error", "����ǰ�������������򲻴���");
			return "error";
		}
		request.getSession().setAttribute("globle_user", elecUser);
		//��ȡ��ǰ��¼�������е�Ȩ��
		String popedom = elecUserService.findElecPopedomByLogonName(name);
		if(popedom == null || "".equals(popedom)){
			this.addFieldError("error", "��ǰ�ĵ�¼��û�з���Ȩ�ޣ����ڹ���Ա��ϵ��");
			return "error";
		}
		request.getSession().setAttribute("globle_popedom", popedom);
		//��ȡ��ǰ��¼�������еĽ�ɫ
		Hashtable<String, String> ht = elecUserService.findElecRoleByLogonName(name);
		if(ht == null){
			this.addFieldError("error", "��ǰ��¼��û�з����ɫ�����ڹ���Ա��ϵ");
			return "error";
		}
		request.getSession().setAttribute("globle_role", ht);
		//2015-08-11����Ӽ�ס�ҵĹ��ܣ���ס��ǰ�������û���������  begin
		try {
			LogonUtils.remeberMeByCookie(request,response);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//end
		//2015-08-11,�����־����ģ�飬ά��ϵͳ�İ�ȫ����    begin
		/*ʹ��log4j
		 *	java.sql.Date date = new java.sql.Date(new java.util.Date().getTime());
		 *	String d = date.toString();
		 *	log.info("��¼����"+elecUser.getUserName()+"����¼ϵͳ��ʱ���ǣ�" + d);
		 */
		//ʹ�����ݿ��ģʽ����
		elecLogService.saveElecLog(request,"��¼ģ�顾"+elecUser.getUserName()+"����¼ϵͳ");
		//   end
		
		return "home";
	}
	
	public String title(){
		
		return "title";
	}
	
	public String left(){
		
		return "left";
	}
	
	public String change1(){
		
		return "change1";
	}
	
	public String loading(){
		
		return "loading";
	}
	
	public String alermJX(){
		
		return "alermJX";
	}
	
	/**
	 * @Name:alermSB
	 * @Description : ��ѯ������豸�������
	 * @author ������
	 * @version :V1.0.0(�汾��)
	 * @Create Date ��2015-08-03
	 * @Parameters: ��
	 * @return:String alermSB  ��ת��alermSB.jsp
	 */
	public String alermSB(){
		List<ElecCommonMsgForm> list = elecCommonMsgService.findElecCommonMsgListByCurrentDate();
		request.setAttribute("commonList", list);
		return "alermSB";
	}
	
	public String alermXZ(){
		
		return "alermXZ";
	}
	
	public String alermYS(){
		
		return "alermYS";
	}
	
	/**
	 * @Name:alermZD
	 * @Description : ��ѯ�����վ���������
	 * @author ������
	 * @version :V1.0.0(�汾��)
	 * @Create Date ��2015-08-03
	 * @Parameters: ��
	 * @return:String alermZD  ��ת��alermZD.jsp
	 */
	public String alermZD(){
		List<ElecCommonMsgForm> list = elecCommonMsgService.findElecCommonMsgListByCurrentDate();
		request.setAttribute("commonList", list);
		return "alermZD";
	}
	
	/**
	 * @Name: logout
	 * @Description : ���»��˵���ҳ
	 * @author ������
	 * @version :V1.0.0(�汾��)
	 * @Create Date ��2015-08-10
	 * @Parameters: ��
	 * @return:String logout  ��ת��index.jsp
	 */
	public String logout(){
		//���session
		request.getSession().invalidate();
		return "logout";
	}
	
}
