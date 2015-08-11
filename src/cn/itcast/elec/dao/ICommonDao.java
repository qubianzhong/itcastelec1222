package cn.itcast.elec.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;

import cn.itcast.elec.domain.ElecSystemDDl;
import cn.itcast.elec.domain.ElecText;

/**
 * @version V1.0.0
 * @author ������
 * @create Date 2015-07-30
 * @purpost �ṩ������DAO��Ľӿ�
 * @param <T> ���Ͷ���
 * ddf
 */
public interface ICommonDao<T> {
	void save(T t);
	void update(T t);
	T findObjectById(Serializable id);
	void deleteObjectByIds(Serializable... ids);
	void deleteObjectByCollection(Collection<T> entities);
	List<T> findCollectionByConditionNoPage(String hqlWhere,
			Object[] params, LinkedHashMap<String, String> orderby);
	void saveObjectByCollection(Collection<T> entities);
}
