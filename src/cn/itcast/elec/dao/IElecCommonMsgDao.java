package cn.itcast.elec.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;

import cn.itcast.elec.domain.ElecCommonMsg;
import cn.itcast.elec.domain.ElecText;

/**
 * @version V1.0.0
 * @author ������
 * @create Date 2015-07-30
 * @purpost �ṩ������DAO��Ľӿ�
 * @param <T> ���Ͷ���
 * ddf
 */
public interface IElecCommonMsgDao extends ICommonDao<ElecCommonMsg> {
	public final static String SERVICE_NAME = "cn.itcast.elec.dao.impl.ElecCommonMsgDaoImpl";

	List<Object[]> findElecCommonMsgListByCurrentDate(String currentDate);
}
