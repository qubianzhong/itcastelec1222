package cn.itcast.elec.container;

import org.apache.commons.lang.StringUtils;

public class ServiceProvider {
	private static ServiceProviderCode spc;
	
	//���������ļ�beans.xml�ļ�
	static{
		spc = new ServiceProviderCode();
		spc.load("beans.xml");
	}
	
	/**
	 * @param serviceName ��ȡ��ǰ���������
	 * @return ���ص�ǰ����
	 */
	public static Object getService(String serviceName){
		
		if(StringUtils.isBlank(serviceName)){
			throw new RuntimeException("��ǰ���������Ʋ�����");
		}
		Object object = null;
		if(spc.ac.containsBean(serviceName)){
			object = spc.ac.getBean(serviceName);
		}
		if(object == null){
			throw new RuntimeException("���ڷ������ơ� "+serviceName+"�� �µķ���ڵ㲻����");
		}
		
		return object;
	}
}
