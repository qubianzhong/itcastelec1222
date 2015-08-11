package cn.itcast.elec.dao;

import java.util.List;

import cn.itcast.elec.domain.ElecUserRole;

/**
 * @version V1.0.0
 * @author ������
 * @create Date 2015-08-08
 * @purpost �ṩ�û������DAO��Ľӿ�
 * @param <ElecUserRole> �û���PO����
 */
public interface IElecUserRoleDao extends ICommonDao<ElecUserRole> {
	public final static String SERVICE_NAME = "cn.itcast.elec.dao.impl.ElecUserRoleDaoImpl";

	List<Object[]> findElecUserListByRoleID(String roleID);

}
