package cn.itcast.elec.container;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ServiceProviderCode {

	protected static ApplicationContext ac;
	
	/**
	 * @author ������
	 * @param filename ����beans.xml�ļ���filename ���õ���beans.xml��
	 */
	public static void load(String filename){
		ac = new ClassPathXmlApplicationContext(filename);
	}
	
}
