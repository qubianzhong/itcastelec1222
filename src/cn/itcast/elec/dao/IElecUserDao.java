package cn.itcast.elec.dao;

import java.util.List;

import cn.itcast.elec.domain.ElecSystemDDl;
import cn.itcast.elec.domain.ElecUser;

/**
 * @version V1.0.0
 * @author ������
 * @create Date 2015-08-06
 * @purpost �ṩ�û������DAO��Ľӿ�
 * @param <ElecUser> �û���PO����
 */
public interface IElecUserDao extends ICommonDao<ElecUser> {
	public final static String SERVICE_NAME = "cn.itcast.elec.dao.impl.ElecUserDaoImpl";

	List<Object> findElecPopedomByLogonName(String name);

	List<Object[]> findElecRoleByLogonName(String name);

}
