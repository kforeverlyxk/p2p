package com.offcn.action.account;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.activemq.console.Main;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.offcn.action.common.BaseAction;
import com.offcn.cache.BaseCacheService;
import com.offcn.domain.UserAccountModel;
import com.offcn.service.AccoutService;
import com.offcn.utils.Response;


@Controller
@Namespace("/account")
public class AccontAction  extends  BaseAction {
@Autowired
private BaseCacheService  baseCacheService;
	
@Autowired
private AccoutService  accoutService;

@Action("accountHomepage")
public  void accountHomepage(){
    
	
	
		//1.得到用户信息
	String  token=	this.getRequest().getHeader("token");
		 //从redis当中获取用户信息
	  Map<String, Object> map= baseCacheService.getHmap(token);
	  //获取用户的id
	   int  userid=(Integer)  map.get("id");
		// 根据用户的id获取账户信息
	     UserAccountModel  um= accoutService.findByUserid(userid);
		// 存储起来  应用到浏览器
	     Map<String, Object> map1=new  HashMap<String, Object>();
	     map1.put("u_total", um.getTotal());
	     map1.put("u_balance", um.getBalance());
	     map1.put("u_interest_a", um.getInterestA());
	     List<Object> list=new ArrayList<Object>();
	     list.add(map1);
	     try {
			this.getResponse().getWriter().write(Response.build().setStatus("1").setData(list).toJSON());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	     
	}




   
}
