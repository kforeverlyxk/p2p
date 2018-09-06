package com.offcn.action.pay;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.xml.resolver.apps.resolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.offcn.action.common.BaseAction;
import com.offcn.cache.BaseCacheService;
import com.offcn.domain.UserAccountModel;
import com.offcn.service.UserAccountService;
import com.offcn.utils.Response;
@Controller
@Namespace("pay")
public class ExtractsAction extends  BaseAction{
	@Autowired
	   private  UserAccountService  userAccountService;
	@Autowired
	private  BaseCacheService baseCacheService;
	
    @Action("/topay")     
	public  void  pay(){
        	 String  moeny=this.getRequest().getParameter("money");
        	 String  token =this.getRequest().getParameter("token");
        	
             int userid=(Integer)( baseCacheService.getHmap(token).get("id"));
           //获取到原来的金钱说
        	 UserAccountModel  ac=userAccountService.findbyUserId(userid);
               
             // 获取到输入的内容  把获取的金钱写入到账户表中
             userAccountService.setMoneybyuserid(userid, ac.getBalance()+Double.parseDouble(moeny));
           
             try {
				this.getResponse().sendRedirect("http://localhost:8080/offcn_p2p_home/index.html");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
             
    }
}
