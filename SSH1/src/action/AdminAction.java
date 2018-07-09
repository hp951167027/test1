package action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import bean.Admin;
import service.AdminService;

@Controller//���ڱ�ע���Ʋ����  
@Namespace("/user")//urlǰ׺  
@Scope("prototype")//ActionĬ���ǵ�������ʵ�ʿ����У�һ���Ƕ�������Ϊһ��һ��Action���ܻ��Ӧ�����ͬ������  
@ParentPackage("json-default")//�̳��ض���package��Ĭ���ǡ�struts-default������˿���ʡ�Բ�д  
@Results({  
    @Result(name="registSuccess",location="/msg.jsp"),
    @Result(name = "finda",type="json", params={"root","find"})  
}) 
public class AdminAction {
	
	private List find = new ArrayList();
	

	


	public List<Admin> getFind() {
		return find;
	}
	public void setFind(List<Admin> find) {
		this.find = find;
	}
	@Autowired//�Զ�ע��  
	private AdminService service;
	
	//strutsĬ�����ء�.action�Լ������κκ�׺��  
    @Action(value="regist")//���ʣ�/user/regist.action ��  /user/regist  
    public String regist(){  
          
      //��ȡrequest  
      HttpServletRequest request = ServletActionContext.getRequest();  
         
      //��ȡ���ύ������  
      String account =  request.getParameter("account");  
      String password = request.getParameter("password");  
      //��װuserBean  
      Admin user = new Admin();  
      user.setAccount(account);
      user.setPassword(password);  
        
      //����service��ķ����������ݿ�������һ����¼  
      service.addAdmin(user);
        
      //����ʾ��Ϣ����request���У�����ǰ̨��ʾ   	q
      request.setAttribute("msg", "��ϲ����ע��ɹ���<br>ע������"+account);  
        
        return "registSuccess";  
    }  
    @Action(value="find")
    public String find(){
    	find = service.find();
    	
    	//��ȡrequest  
//        HttpServletRequest request = ServletActionContext.getRequest();  
//        request.setAttribute("list", find);  
        
        return "finda";
    }

  
}
