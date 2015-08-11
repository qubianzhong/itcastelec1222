package cn.itcast.elec.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import cn.itcast.elec.dao.IElecSystemDDlDao;
import cn.itcast.elec.domain.ElecSystemDDl;

/**
 * @version V1.0.0
 * @create Date 2015-07-30
 * @author ������
 *
 */
@Component(IElecSystemDDlDao.SERVICE_NAME)
public class ElecSystemDDlDaoImpl extends CommonDaoImpl<ElecSystemDDl> implements IElecSystemDDlDao {

	/**
	 * @Name:findKeyWord
	 * @Description : ��ѯ�������͹ؼ��֣���ȥ���ظ�ֵ
	 * @author ������
	 * @version :V1.0.0(�汾��)
	 * @Create Date ��2015-08-04
	 * @Parameters: null
	 * @return: List<ElecSystemDDl> ���������б�
	 */
	@Override
	public List<Object> findKeyWord() {
		String hql = "select distinct o.keyword from ElecSystemDDl o ";
		List<Object> list = this.getHibernateTemplate().find(hql);
		return list;
	}

	/**
	 * @Name: findDDlName
	 * @Description : ʹ���������ͺ�������ı�Ż�ȡ�����������
	 * @author ������
	 * @version :V1.0.0(�汾��)
	 * @Create Date ��2015-08-06
	 * @Parameters: String sexID ������ı��
	 * 				 String string ��������
	 * @return: String ����������
	 */
	@Override
	public String findDDlName(Integer ddlCode, String keyword) {
		String hql = "from ElecSystemDDl where keyword= '" + keyword + "' and ddlCode=" + ddlCode;
		List<ElecSystemDDl> list = this.getHibernateTemplate().find(hql);
		String ddlName = "";
		if(list != null && list.size()>0){
			ElecSystemDDl elecSystemDDl = list.get(0);
			ddlName = elecSystemDDl.getDdlName();
		}
		return ddlName;
	}

	
	
}
