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
import cn.itcast.elec.dao.IElecUserRoleDao;
import cn.itcast.elec.domain.ElecUserRole;

@Repository(IElecUserRoleDao.SERVICE_NAME)
public class ElecUserRoleDaoImpl extends CommonDaoImpl<ElecUserRole> implements IElecUserRoleDao {

	/**
	 * @Name: findElecUserListByRoleID
	 * @Description :  ��ѯ�û��б��ϣ���ȡϵͳ�е����е��û�������ý�ɫӵ�е��û�����ƥ��
	 * @author ������
	 * @version :V1.0.0(�汾��)
	 * @Create Date ��2015-08-08 ���������ڣ�
	 * @Parameters: String roleID ��ɫID
	 * @return: ist<Object[]> �û����ϣ�ƥ����ɣ�
	 */
	@Override
	public List<Object[]> findElecUserListByRoleID(final String roleID) {
		final String sql = 	"select distinct case elec_user_role.RoleID " +
						"when ? then '1' else '0' end as flag, " +
						"elec_user.UserID as userID, " +
						"elec_user.UserName as userName, " +
						"elec_user.LogonName as logonName " +
						"from elec_user " +
						"left outer join elec_user_role " +
						"on elec_user.UserID = elec_user_role.UserID "+
						"and elec_user_role.RoleID = ? " +
						"where elec_user.IsDuty = '1'";
		List<Object []> list = (List<Object[]>) this.getHibernateTemplate().execute(new HibernateCallback() {
			
			@Override
			public Object doInHibernate(Session session) throws HibernateException,
					SQLException {
				Query query = session.createSQLQuery(sql)
								.addScalar("flag",Hibernate.STRING)
								.addScalar("userID", Hibernate.STRING)
								.addScalar("userName", Hibernate.STRING)
								.addScalar("logonName", Hibernate.STRING);
				query.setString(0, roleID);
				query.setString(1, roleID);
				return query.list();
			}
		});
		return list;
	}
	
}
