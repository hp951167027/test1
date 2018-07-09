package bean;

import java.sql.Date;

import javax.persistence.Id;
import javax.persistence.Table;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

@Entity//映射数据库表  
@Table(name="Admin")//不加这个注解，默认对应的是user表  
public class Admin {

	
	@Id//对应t_user表中的主键  
	@GeneratedValue
    private int id;//用户ID  
    
    private String account;//用户名  
      
    private String password;//密码  
    
    

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
    
    
    
}
