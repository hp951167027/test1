package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bean.Admin;
import dao.Admindao;

@Service//这个属性对应的是业务层一般为Service层)，说明交给spring管理，而对应的包下的类名也会有一个"S"  
public class AdminService {
	
	@Autowired//同样是自动注入 
	private Admindao adminDao;
	
	
	public void addAdmin(Admin Admin){  
        //调用Dao层的addUser方法  
		adminDao.addAdmin(Admin);  
    } 
	
	public List find(){
		return adminDao.find();
	}

}
