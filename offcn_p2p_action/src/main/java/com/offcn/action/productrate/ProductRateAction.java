package com.offcn.action.productrate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.offcn.action.common.BaseAction;
import com.offcn.cache.BaseCacheService;
import com.offcn.domain.UserAccountModel;
import com.offcn.domain.product.ProductEarningRate;
import com.offcn.service.AccoutService;
import com.offcn.service.ProductEarningRateService;
import com.offcn.utils.Response;

@Controller
@Namespace("/productRate")
public class ProductRateAction extends  BaseAction  {
	
	@Autowired
	private BaseCacheService baseCacheService;
	@Autowired
	private ProductEarningRateService  productEarningRateService;
  @Autowired
	private  AccoutService accoutService;
	@Action("yearInterest")
	public  void yearInterest(){
		//根据产品编号获取到产品的利率
    String  pid=this.getRequest().getParameter("pid");
  
	List<ProductEarningRate> list=	productEarningRateService.findbyRateProid(Integer.parseInt(pid));
	   List<Map<String, Object>>li=new ArrayList<Map<String,Object>>();
	   for(ProductEarningRate  p:list){
		   Map<String, Object>  o=new HashMap<String, Object>();
		   o.put("endMonth", p.getMonth());
		   o.put("incomeRate", p.getIncomeRate());
		   li.add(o);
	   }
	    System.out.println(list.size());
	   try {
		this.getResponse().getWriter().write(Response.build().setStatus("1").setData(li).toJSON());
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	}
	
	/*
	 * 判断账户余额的 
	 *根据余额判断是否可以购买产品
	 */
	@Action("checkAccount")
	 public  void checkAccount(){
		
		String  account=this.getRequest().getParameter("account");
	String  token= this.getRequest().getHeader("token");
	  Map<String, Object> map= baseCacheService.getHmap(token);
	 Integer  userid=(Integer) map.get("id");
	 //根据用户id获取到对应的账户信息
	  UserAccountModel uu= accoutService.findByUserid(userid);
	  double ban=uu.getBalance();
	  double banss=Double.parseDouble(account);
	  if(ban>banss){
		  try {
			this.getResponse().getWriter().write(Response.build().setStatus("1").toJSON());
		  return ;
		  } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  
	  }
	
	  
	  try {
		this.getResponse().getWriter().write(Response.build().setStatus("17").toJSON());
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}

	
	
}
