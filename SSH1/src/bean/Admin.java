package bean;

import java.sql.Date;

import javax.persistence.Id;
import javax.persistence.Table;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

@Entity//ӳ�����ݿ��  
@Table(name="Admin")//�������ע�⣬Ĭ�϶�Ӧ����user��  
public class Admin {

	
	@Id//��Ӧt_user���е�����  
	@GeneratedValue
    private int id;//�û�ID  
    
    private String account;//�û���  
      
    private String password;//����  
    
    

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
