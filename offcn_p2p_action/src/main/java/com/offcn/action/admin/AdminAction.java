package com.offcn.action.admin;

import java.io.IOException;

import javax.swing.plaf.basic.BasicSliderUI.ActionScroller;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.offcn.domain.AdminBean;
import com.offcn.service.AdminService;
import com.opensymphony.xwork2.ActionSupport;

@Controller
@Namespace("/account")
public class AdminAction extends  ActionSupport {
   
	  @Autowired
	  private AdminService   adminService;
	  
	
	@Action("login")
	  public void login(){
		  String username=ServletActionContext.getRequest().getParameter("username");
		  String password=ServletActionContext.getRequest().getParameter("password");
		  
		  AdminBean  ab=   adminService.login(username, password);
		  if(ab!=null){
			  try {   														//{status:1}
				  //{status:1}
				ServletActionContext.getResponse().getWriter().write("{\"status\":\"1\"}");
			  return  ;
			  } catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			  
			  
		  }
//		  System.out.println(username+"  "+password);
//		 if(username.equals("admin") && password.equals("admin")){
//			  
//			  try {
//				  ServletActionContext.getResponse().getWriter().write("{\"status\":\"1\"}");
//				return ;
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		  }
		  
	  }
}