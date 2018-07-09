package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.sun.istack.internal.FinalArrayList;

import bean.Admin;

@Repository // ������Զ�Ӧ���ǳ־ò�(һ��ΪDao��)��˵������spring��������Ӧ�İ��µ�����Ҳ����һ��"S"
public class Admindao {

	@Autowired // �Զ�ע�룬����Ҫ��ֵ����Ϊ��spring�����ļ����Ѿ����ù�
	private HibernateTemplate template;

	
	private ExecuteDao dao = new ExecuteDao();
	/**
	 * �û�ע�ᣬ����������һ���µļ�¼
	 * 
	 * @param user
	 */
	public void addAdmin(Admin admin) {
		// �����ݿ������һ�����ݣ�һ�仰�Ϳ��Ը㶨
		template.save(admin);
		
		
		
		
	}

	public List find() {
		//String sql = "select a.* from Admin a where a.Account = ? and a.Password = ? and a.Id = ?";
		
		String sql  = "sel_Admin_Login(?,?)";
		//List<Admin> queryWithSql = queryWithSql(sql,Admin.class,"33","44",3);
		List<Admin> queryWithSql = dao.queryWithCun(template,sql,Admin.class,1,"22","33");
		String sql1  = "add_Goods(?,?,?,?,?,?,?,?)";
		Integer queryWithCunCount = dao.update(template, sql1, 1, 1007,1,"����","���",0.01,"����",5,1);
		System.out.println(queryWithCunCount);
		return queryWithSql;
	}
	
	/**
	 * ��ѯ����
	 * @param sql
	 * @param stat �в�����1 û�д�0
	 * @param objects �в�����������û�в���
	 * @return
	 */
	public Integer queryWithCunCount(String sql,int stat,Object...objects) {
		List  list = template.executeFind(new HibernateCallback() {
			public List doInHibernate(Session session) {
				String updSQL = updSQL(stat, sql);
				Query query = session.createSQLQuery(updSQL);
				bindParameters(query, objects);
				
				
				List<Integer> list = query.list();
				return list;
			}
		});
		
		return (Integer) list.get(0);
	}
	

	/**
	 * ʹ��sql�����в�ѯ����
	 * 
	 * @param sql
	 * @return
	 */
	public <T> List<T> queryWithSql(String sql,Class<T> clz,Object...objects) {
		List  list = template.executeFind(new HibernateCallback() {
			public List doInHibernate(Session session) {
				Query query = session.createSQLQuery(sql).addEntity("a",  clz);
				
				bindParameters(query, objects);
				
				
				List list = query.list();
				return list;
			}
		});
		return list;
	}
	/**
	 * ��ѯ
	 * @param sql
	 * @param clz ��
	 * @param stat
	 * @param objects
	 * @return
	 */
	public <T> List<T> queryWithCun(String sql,Class<T> clz,int stat,Object...objects) {
		List  list = template.executeFind(new HibernateCallback() {
			public List doInHibernate(Session session) {
				String updSQL = updSQL(stat, sql);
				Query query = session.createSQLQuery(updSQL).addEntity(clz);
				bindParameters(query, objects);
				
				
				List list = query.list();
				return list;
			}
		});
		return list;
	}
	
	protected String updSQL(int stat,String sql){
		if (stat != 0) {
			sql = "{ call " + sql + " }";
		} 
		
		return sql;
	}
	protected void bindParameters(Query query,Object...objects){
		if(objects!=null){
			for (int i = 0; i < objects.length; i++) {
				query.setParameter(i, objects[i]);
			}
		}
	}
	
	
}
