package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bean.Admin;
import dao.Admindao;

@Service//������Զ�Ӧ����ҵ���һ��ΪService��)��˵������spring��������Ӧ�İ��µ�����Ҳ����һ��"S"  
public class AdminService {
	
	@Autowired//ͬ�����Զ�ע�� 
	private Admindao adminDao;
	
	
	public void addAdmin(Admin Admin){  
        //����Dao���addUser����  
		adminDao.addAdmin(Admin);  
    } 
	
	public List find(){
		return adminDao.find();
	}

}
