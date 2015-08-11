package cn.itcast.elec.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.elec.container.ServiceProvider;
import cn.itcast.elec.service.IElecUserService;

public class CheckLogonName extends HttpServlet {

IElecUserService elecUserService = (IElecUserService) ServiceProvider.getService(IElecUserService.SERVICE_NAME);
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html��charset=utf-8");
		PrintWriter out = response.getWriter();
		String logonName = request.getParameter("logonName");
		/**
		 * checkflag ���жϵ�ǰ��¼�������ݿ����Ƿ���ֵ
		 * checkflag �����ֵΪ1��˵����ǰ��¼�������ݿ������ظ���¼�����ܽ��б���
		 * checkflag : ���ֻΪ2��˵����ǰ��¼�������ݿ��������ظ�ֵ�����Խ��б���
		 */
		String checkflag = elecUserService.checkLogonName(logonName);
		out.println(checkflag);
		out.flush();
		out.close();

	}
}
