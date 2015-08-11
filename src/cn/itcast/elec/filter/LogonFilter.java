package cn.itcast.elec.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.elec.domain.ElecUser;

public class LogonFilter implements Filter {

	List<String> list = new ArrayList<String>();
	
	//��Ҫ����ϵͳҳ���пɷ��е����ӣ��ŵ�List<String>�ļ��϶�����
	public void init(FilterConfig arg0) throws ServletException {
		
		list.add("/index.jsp");
		list.add("/image.jsp");
		list.add("/system/elecMenuAction_home.do");
		
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
			FilterChain chain) throws IOException, ServletException {
		/**1����ȡҳ���еķ��ʵ�·������
		 * 	   *�붨�����Ҫ���е����ӽ��бȶ�
		 * 			*ҳ���з��ʵ������붨����Ҫ���е�����һ�£����ҳ���еķ���·��������Ҫ����
		 * 			*��֮�������У����ص���¼ҳ��
		 *2����session(globle_user)�����л�ȡ��ǰ��¼���û�
		 *		*�����ȡ�ĵ�¼�û��Ķ���Ϊ�գ�����Ҫ����
		 *		*��֮�������У����ص���¼ҳ��
		 */
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		//1����ȡҳ���еķ��ʵ�·������
		String path = request.getServletPath();
		if(list != null && list.contains(path)){
			//���ҳ���л�ȡ�ķ��������ڶ���Ŀɷ��е�����һ�£�����
			chain.doFilter(request, response);
			return ;
		}
		//2����session(globle_user)�����л�ȡ��ǰ��¼���û�
		ElecUser elecUser = (ElecUser) request.getSession().getAttribute("globle_user");
		if(elecUser != null){
			//�����session�л�ȡ���û�����Ϊ�գ������
			chain.doFilter(request, response);
			return ;
		}
		//�������������1��2�����ص���¼ҳ��
		response.sendRedirect(request.getContextPath()+"/");
		
	}
	
	@Override
	public void destroy() {
		
	}

	

}
