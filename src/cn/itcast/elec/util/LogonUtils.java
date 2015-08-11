package cn.itcast.elec.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;

public class LogonUtils {

	/**
	 * @Name: checkNumber
	 * @Description :   ��ҳ��¼ʱ�������֤��Ĺ��ܵģ�
	 * 							�Ƚ���֤���ֵ���������ֵ�Ƿ����
	 * @author ������
	 * @version :V1.0.0(�汾��)
	 * @Create Date ��2015-08-11
	 * @Parameters: HttpServletRequest request  ����
	 * @return: boolean     true����֤�ɹ�
	 * 						false����֤ʧ��
	 */
	public static boolean checkNumber(HttpServletRequest request) {
		// ��session�л�ȡ��֤�����ֵ
		HttpSession session = request.getSession(false);
		if(session == null){
			return false;
		}
		String check_number_key = (String)session.getAttribute("CHECK_NUMBER_KEY");
		if(StringUtils.isBlank(check_number_key)){
			return false;
		}
		//�ӵ�½ҳ���ȡ��֤���ֵ
		String checkNumber = request.getParameter("checkNumber");
		if(StringUtils.isBlank(checkNumber)){
			return false;
		}
		
		return check_number_key.equalsIgnoreCase(checkNumber);  
	}

	/**
	 * @Name: remeberMeByCookie
	 * @Description :   ��ҳ��¼��ס�ҵĹ���
	 * @author ������
	 * @version :V1.0.0(�汾��)
	 * @Create Date ��2015-08-11
	 * @Parameters: HttpServletRequest   request     ����
	 * 				HttpServletResponse  response    ����
	 * @return: null
	 * @throws UnsupportedEncodingException 
	 */
	public static void remeberMeByCookie(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		// ��ȡҳ���еĵ�¼��������
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		//����Cookie�д��ڵ������ַ�
		String codeName = URLEncoder.encode(name, "utf-8");
		String codePassword = URLEncoder.encode(password, "utf-8");
		//��������Cookie���ֱ��ŵ�¼��������
		Cookie nameCookie = new Cookie("name", codeName);
		Cookie passwordCookie = new Cookie("password", codePassword);
		//����Cookie����Ч·������Ч·������Ϊ��Ŀ�ĸ���·��
		nameCookie.setPath(request.getContextPath()+"/");
		passwordCookie.setPath(request.getContextPath()+"/");
		/**
		 * ��ҳ���л�ȡ��ס�ҵĸ�ѡ���ֵ��
		 *	     �����ֵ������Cookie����Чʱ��
		 *	     ���û��ֵ�����Cookie����Чʱ��
		*/
		String remeberMe = request.getParameter("remeberMe");
		//����Cookie����Чʱ��
		if(remeberMe != null && remeberMe.equals("yes")){
			nameCookie.setMaxAge(7*24*60*60);
			passwordCookie.setMaxAge(7*24*60*60);
		}
		//���Cookie����Чʱ��
		else{
			nameCookie.setMaxAge(0);
			passwordCookie.setMaxAge(0);
		}
		//��2��Cookie�����ŵ�response������
		response.addCookie(nameCookie);
		response.addCookie(passwordCookie);
	}

}
