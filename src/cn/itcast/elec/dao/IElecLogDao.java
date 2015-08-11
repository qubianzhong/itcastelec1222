package cn.itcast.elec.dao;

import java.util.List;

import cn.itcast.elec.domain.ElecLog;
import cn.itcast.elec.domain.ElecSystemDDl;
import cn.itcast.elec.domain.ElecUser;

/**
 * @version V1.0.0
 * @author ������
 * @create Date 2015-08-11
 * @purpost �ṩ��־�����DAO��Ľӿ�
 * @param <ElecLog> Log��PO����
 */
public interface IElecLogDao extends ICommonDao<ElecLog> {
	public final static String SERVICE_NAME = "cn.itcast.elec.dao.impl.ElecLogDaoImpl";

}
