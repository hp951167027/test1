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

@Controller//用于标注控制层组件  
@Namespace("/user")//url前缀  
@Scope("prototype")//Action默认是单例，但实际开发中，一般是多例，因为一般一个Action可能会对应多个不同的请求  
@ParentPackage("json-default")//继承特定的package，默认是“struts-default”，因此可以省略不写  
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
	@Autowired//自动注入  
	private AdminService service;
	
	//struts默认拦截“.action以及不加任何后缀”  
    @Action(value="regist")//访问：/user/regist.action 或  /user/regist  
    public String regist(){  
          
      //获取request  
      HttpServletRequest request = ServletActionContext.getRequest();  
         
      //获取表单提交的数据  
      String account =  request.getParameter("account");  
      String password = request.getParameter("password");  
      //封装userBean  
      Admin user = new Admin();  
      user.setAccount(account);
      user.setPassword(password);  
        
      //调用service层的方法，向数据库中增加一条记录  
      service.addAdmin(user);
        
      //将提示信息存入request域中，用以前台显示   	q
      request.setAttribute("msg", "恭喜您，注册成功！<br>注册名："+account);  
        
        return "registSuccess";  
    }  
    @Action(value="find")
    public String find(){
    	find = service.find();
    	
    	//获取request  
//        HttpServletRequest request = ServletActionContext.getRequest();  
//        request.setAttribute("list", find);  
        
        return "finda";
    }

  
}
