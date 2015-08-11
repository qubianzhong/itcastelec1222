package cn.itcast.elec.util;

import java.lang.reflect.ParameterizedType;

/**
 * @Name:
 * @author Administrator
 *  
 */
public class GenericSuperClass {

	/**
	 * @Name:getClass
	 * @Description : ����ת����ת��Ϊ��Ӧ�Ķ���
	 * @author ������
	 * @version :V1.0.0(�汾��)
	 * @Create Date ��2015-08-02
	 * @Parameters: Class TClass ����
	 * @return:���ض���
	 */
	@SuppressWarnings("rawtypes")
	public static Class getClass(Class tClass){
		//����ת��
		ParameterizedType pt = (ParameterizedType) tClass.getGenericSuperclass();
		//����������ж�����������Ϳ�����������ֵ
		Class entity = (Class) pt.getActualTypeArguments()[0];
		return entity;
	}
}
