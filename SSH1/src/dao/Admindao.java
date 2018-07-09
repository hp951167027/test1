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

@Repository // 这个属性对应的是持久层(一般为Dao层)，说明交给spring管理，而对应的包下的类名也会有一个"S"
public class Admindao {

	@Autowired // 自动注入，不需要设值，因为在spring配置文件中已经配置过
	private HibernateTemplate template;

	
	private ExecuteDao dao = new ExecuteDao();
	/**
	 * 用户注册，即向表中添加一条新的记录
	 * 
	 * @param user
	 */
	public void addAdmin(Admin admin) {
		// 往数据库中添加一条数据，一句话就可以搞定
		template.save(admin);
		
		
		
		
	}

	public List find() {
		//String sql = "select a.* from Admin a where a.Account = ? and a.Password = ? and a.Id = ?";
		
		String sql  = "sel_Admin_Login(?,?)";
		//List<Admin> queryWithSql = queryWithSql(sql,Admin.class,"33","44",3);
		List<Admin> queryWithSql = dao.queryWithCun(template,sql,Admin.class,1,"22","33");
		String sql1  = "add_Goods(?,?,?,?,?,?,?,?)";
		Integer queryWithCunCount = dao.update(template, sql1, 1, 1007,1,"名称","规格",0.01,"介绍",5,1);
		System.out.println(queryWithCunCount);
		return queryWithSql;
	}
	
	/**
	 * 查询数量
	 * @param sql
	 * @param stat 有参数传1 没有传0
	 * @param objects 有参数传参数，没有不传
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
	 * 使用sql语句进行查询操作
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
	 * 查询
	 * @param sql
	 * @param clz 类
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
