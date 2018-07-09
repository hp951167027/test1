package dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
@SuppressWarnings("all")
public class ExecuteDao {
	
	
	
	/**
	 * 修改
	 * @param template
	 * @param sql
	 * @param stat
	 * @param objects
	 * @return
	 */
	public Integer update(HibernateTemplate template,String sql,int stat,Object...objects){
		return (Integer) template.execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {
				String updSQL = updSQL(stat, sql);
				
				Query query = session.createSQLQuery(updSQL);
				bindParameters(query, objects);
				
				
				int executeUpdate = query.executeUpdate();
				
				return executeUpdate;
			}
		});
		
	
		
	}

	/**
	 * 查询数量
	 * @param sql
	 * @param stat 有参数传1 没有传0
	 * @param objects 有参数传参数，没有不传
	 * @return
	 */
	public Integer queryWithCunCount(HibernateTemplate template,String sql,int stat,Object...objects) {
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
	 * 使用sql语句进行查询操作
	 * 
	 * @param sql
	 * @return
	 */
	public <T> List<T> queryWithSql(HibernateTemplate template,String sql,Class<T> clz,Object...objects) {
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
	 * 查询
	 * @param sql
	 * @param clz 类
	 * @param stat
	 * @param objects
	 * @return
	 */
	public <T> List<T> queryWithCun(HibernateTemplate template,String sql,Class<T> clz,int stat,Object...objects) {
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
