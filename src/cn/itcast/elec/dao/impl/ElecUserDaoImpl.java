package cn.itcast.elec.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import cn.itcast.elec.dao.IElecUserDao;
import cn.itcast.elec.domain.ElecUser;

@Repository(IElecUserDao.SERVICE_NAME)
public class ElecUserDaoImpl extends CommonDaoImpl<ElecUser> implements IElecUserDao {

	
	/**
	 * @Name: findElecPopedomByLogonName
	 * @Description : ʹ�õ�¼����ȡ��ǰ�û������е�Ȩ��,��ѯ���ݿ��
	 * 											elec_user
	 * 											elec_user_role
	 * 											elec_role_popedom
	 * @author ������
	 * @version :V1.0.0(�汾��)
	 * @Create Date ��2015-08-10
	 * @Parameters: String name  ��¼��
	 * @return: List<Object> 	�û���Ÿ��û����е�Ȩ��	
	 */
	@Override
	public List<Object> findElecPopedomByLogonName(final String name) {
		final String sql = "select a.popedomcode as popedom from elec_role_popedom a " +
					 "left outer join elec_user_role b on a.RoleID = b.RoleID " +
					 "inner join elec_user c on b.UserID = c.UserID " +
					 "and c.logonName = ? " +
					 "where c.isDuty = '1' ";
		List<Object> list = (List<Object>)this.getHibernateTemplate().execute(new HibernateCallback() {
			
			@Override
			public Object doInHibernate(Session session) throws HibernateException,
					SQLException {
				Query query = session.createSQLQuery(sql)
									  .addScalar("popedom", Hibernate.STRING);
				query.setParameter(0, name);
				return query.list();
			}
		});
		return list;
	}

	/**
	 * @Name: findElecRoleByLogonName
	 * @Description : ʹ�õ�¼����ȡ��ǰ�û������е�Ȩ��,��ѯ���ݿ��
	 * 											elec_user
	 * 											elec_user_role
	 * 											elec_systemddl
	 * @author ������
	 * @version :V1.0.0(�汾��)
	 * @Create Date ��2015-08-10
	 * @Parameters: String name  ��¼��
	 * @return: List<Object> 	��Ÿ��û����еĽ�ɫ����	
	 */
	@Override
	public List<Object []> findElecRoleByLogonName(final String name) {
		final String sql = "select b.ddlCode as ddlCode,b.ddlName as ddlName from elec_user_role a " +
					"left outer join elec_systemddl b on a.RoleID = b.ddlCode " +
					"and b.keyword = '��ɫ����' "+
					"inner join elec_user c on a.UserID = c.UserID " +
					"and c.logonName = ? " +
					"where c.isDuty = '1' ";
		List<Object []> list = (List<Object []>) this.getHibernateTemplate().execute(new HibernateCallback() {
			
			@Override
			public Object doInHibernate(Session session) throws HibernateException,
					SQLException {
				Query query = session.createSQLQuery(sql)
									.addScalar("ddlCode", Hibernate.STRING)
									.addScalar("ddlName", Hibernate.STRING);
				query.setParameter(0, name);
				return query.list();
			}
		});
		return list;
	}
	
}
