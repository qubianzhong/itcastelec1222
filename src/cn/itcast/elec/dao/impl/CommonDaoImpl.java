package cn.itcast.elec.dao.impl;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.elec.dao.ICommonDao;
import cn.itcast.elec.util.GenericSuperClass;
/**
 * 
 * @author ������
 * @create Date 2015-07-30
 * @param <T> ���Ͷ���
 * @return null
 * @describle ʵ�����һ������Ĺ��ܣ��̳���HibernateDaoSupport�࣬ʵ����ICommonDaoImpl�ӿ�
 */
public class CommonDaoImpl<T> extends HibernateDaoSupport implements ICommonDao<T> {

	//����ת��
	@SuppressWarnings("rawtypes")
	private Class entity = (Class)GenericSuperClass.getClass(this.getClass());

	/**
	 * @Name:save
	 * @Description : �������
	 * @author ������
	 * @version :V1.0.0(�汾��)
	 * @Create Date ��2015-08-02
	 * @Parameters: T t ����
	 * @return:���� null
	 */
	@Override
	@Transactional(isolation=Isolation.DEFAULT,propagation=Propagation.REQUIRED,readOnly=false)
	public void save(T entity) {
		this.getHibernateTemplate().save(entity);
	}

	/**
	 * @Name:setSessionFactoryDi
	 * @Description : ��ȡSessionFactory
	 * @author ������
	 * @version :V1.0.0(�汾��)
	 * @Create Date ��2015-08-02
	 * @Parameters: SessionFactoryDi sessionFactoryDi �Ự����
	 * @return:���� null
	 */
	@Resource(name="sessionFactory")
	public final void setSessionFactoryDi(SessionFactory sessionFactory){
		super.setSessionFactory(sessionFactory);
	}

	/**
	 * @Name:update
	 * @Description : ���¶���
	 * @author ������
	 * @version :V1.0.0(�汾��)
	 * @Create Date ��2015-08-02
	 * @Parameters: T t ����
	 * @return:���� null
	 */
	@Override
	public void update(T t) {
		this.getHibernateTemplate().update(t);
	}

	/**
	 * @Name:findObjectById
	 * @Description : ����һ������
	 * @author ������
	 * @version :V1.0.0(�汾��)
	 * @Create Date ��2015-08-02
	 * @Parameters: T t ����
	 * @return:���� T 
	 */
	@Override
	public T findObjectById(Serializable id) {
		return (T) this.getHibernateTemplate().get(entity, id);
	}

	/**
	 * @Name:deleteObjectByIds
	 * @Description : ͨ��id��������ɾ��һ������
	 * @author ������
	 * @version :V1.0.0(�汾��)
	 * @Create Date ��2015-08-02
	 * @Parameters: T t ����
	 * @return:���� null
	 */
	@Override
	public void deleteObjectByIds(Serializable... ids) {
		for(int i=0;ids!=null && i<ids.length;i++){
			Serializable id = ids[i];
			Object object = (Object)this.getHibernateTemplate().get(entity, id);
			this.getHibernateTemplate().delete(object);
		}
	}
	/**
	 * @Name:deleteObjectByCollection
	 * @Description : ͨ��������ɾ��һ������
	 * @author ������
	 * @version :V1.0.0(�汾��)
	 * @Create Date ��2015-08-02
	 * @Parameters: Collection<T> list ���϶���
	 * @return:���� null
	 */
	@Override
	public void deleteObjectByCollection(Collection<T> entities) {
		this.getHibernateTemplate().deleteAll(entities);
	}
	/**
	 * @Name:findCollectionByConditionNoPage
	 * @Description : ͨ��������ɾ��һ������
	 * @author ������
	 * @version :V1.0.0(�汾��)
	 * @Create Date ��2015-08-02
	 * @Parameters: String hqlWhere,hql����where����
					Object[] params, where�����Ĳ�ѯ����
					LinkedHashMap<String, String> orderby,orderby��������
	 * @return: List<T>  ������б�
	 */
	@Override
	public List<T> findCollectionByConditionNoPage(String hqlWhere,
			final Object[] params, LinkedHashMap<String, String> orderby) {
		
		/**
		 * ��֯HQL����where����
		 * 
		 * select * from elec_text o where 1=1		//����DAO��
		 * where o.textName like ?			//����Service��
		 * and o.textRemark like ?
		 * order by o.textDate desc , o.textName asc;
		 */
		String hql = "from "+entity.getSimpleName()+" o where 1=1 ";
		//��֯��������
		String hqlOrderBy = this.orderByCondition(orderby);
		hql = hql + hqlWhere + hqlOrderBy;
		final String finalHql = hql;
		List<T> list = (List<T>)this.getHibernateTemplate().execute(new HibernateCallback() {
			@Override
			public Object doInHibernate(Session session) throws HibernateException,
					SQLException {
				Query query = session.createQuery(finalHql);
				setParams(query,params);
				
				return query.list();
			}
		});
		return list;
	}
	/**
	 * @Name:setParams
	 * @Description : ��where�����еĲ������ò���ֵ
	 * @author ������
	 * @version :V1.0.0(�汾��)
	 * @Create Date ��2015-08-02
	 * @Parameters: Object[] params ����ֵ
	 * @return: null
	 */
	private void setParams(Query query,Object[] params) {
		// TODO Auto-generated method stub
		for(int i=0;params != null && i<params.length;i++){
			query.setParameter(i, params[i]);
		}
	}

	/**
	 * @Name:orderByCondition
	 * @Description : ��֯��������
	 * @author ������
	 * @version :V1.0.0(�汾��)
	 * @Create Date ��2015-08-02
	 * @Parameters: LinkedHashMap<String, String> orderby orderby ��������
	 * @return: String  ���������ַ���
	 */
	private String orderByCondition(LinkedHashMap<String, String> orderby) {
		StringBuffer buffer = new StringBuffer("");
		
		if(orderby != null){
			buffer.append(" order by");
			for(Map.Entry<String, String> map : orderby.entrySet()){
				buffer.append(" " + map.getKey() + " " + map.getValue() + ",");
			}
			buffer.deleteCharAt(buffer.length()-1);
		}
		return buffer.toString();
	}

	/**
	 * @Name: saveObjectByCollection
	 * @Description : ʹ�ü��ϵ���ʽ������������
	 * @author ������
	 * @version :V1.0.0(�汾��)
	 * @Create Date ��2015-08-06
	 * @Parameters: Collection<T> entities ���϶���
	 * @return: null
	 * 
	 */
	@Override
	public void saveObjectByCollection(Collection<T> entities) {
		this.getHibernateTemplate().saveOrUpdateAll(entities);
	}

	

}
