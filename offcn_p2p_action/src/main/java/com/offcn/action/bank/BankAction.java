package com.offcn.action.bank;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.offcn.action.common.BaseAction;
import com.offcn.cache.BaseCacheService;
import com.offcn.domain.Bank;
import com.offcn.domain.BankCardInfo;
import com.offcn.domain.City;
import com.offcn.domain.UserAccountModel;
import com.offcn.domain.UserModel;
import com.offcn.service.AccoutService;
import com.offcn.service.BankCardService;
import com.offcn.service.BankService;
import com.offcn.service.CityService;
import com.offcn.service.UserService;
import com.offcn.utils.FrontStatusConstants;
import com.offcn.utils.Response;
import com.opensymphony.xwork2.ModelDriven;
@Controller
@Namespace("/bankCardInfo")
public class BankAction  extends  BaseAction implements  ModelDriven<BankCardInfo>{
@Autowired
private  BaseCacheService  baseCacheService;
@Autowired
private BankCardService  bankCardService;
@Autowired
private  BankService  bankService;
@Autowired
 private  CityService    cityService;
@Autowired
private  UserService   userService;

private  BankCardInfo bankCardInfo=new  BankCardInfo();
@Autowired
private  AccoutService  accoutService;
/*
 * 充值成功页面
 */
@Action("success")
 public  void accsuccess(){
	String  moeney= this.getRequest().getParameter("total_amount");
	System.out.println(moeney);
	//获取到要充值的
	
 Integer  userid=Integer.parseInt(baseCacheService.get("ids"));
 System.out.println(userid);
 //根据用户id获取到账户信息
  UserAccountModel  us=accoutService.findByUserid(userid);
 double dd= us.getBalance();
  double ban=dd+Double.parseDouble(moeney);
 double to=us.getTotal()+Double.parseDouble(moeney);
 us.setTotal(to);
 us.setBalance(ban);
 System.out.println("heeeeeeeeeeeeee ");
 accoutService.updatemoeny(to, ban, userid);
 //根据用户id进行修改数据
	try {
		this.getResponse().sendRedirect("http://localhost:8080/offcn_p2p_home/index.html");
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	 
 } 

/*
 * 绑定银行卡
 */

@Action("addBankCardInfo")
    public  void addBankCardInfo(){
	//获取到用户的id
	  String token= this.getRequest().getHeader("token");
	//从redis当中进行获取到数据
	Integer  userid=(Integer)  baseCacheService.getHmap(token).get("id");
	//设置银行卡的用户信息
	bankCardInfo.setUserId(userid);
	//吧银行卡信息写入到数据库当中
	  bankCardService.addBankCard(bankCardInfo);
	  try {
		this.getResponse().getWriter().write(Response.build().setStatus("1").toJSON());
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}

/*
 * 获取对应的城市
 */
@Action("findCity")
  public   void findCity(){
	this.getResponse().setCharacterEncoding("utf-8");
	  String  cityAreaNum= this.getRequest().getParameter("cityAreaNum");
	 List<City> list= cityService.findByParentCityAreaNum(cityAreaNum);
	 try {
		this.getResponse().getWriter().write(Response.build().setStatus("1").setData(list).toJSON());
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
  }
 /*
  * findUserInfo   
  */
	@Action("findUserInfo")
	public  void findUserInfo(){
	   this.getResponse().setCharacterEncoding("utf-8");
	   String  username=this.getRequest().getParameter("username");
	   //得到当前用户的id
	   String  token= this.getRequest().getHeader("token");

    Map<String, Object> map=baseCacheService.getHmap(token);
      int   userid=(Integer)map.get("id");
      UserModel  user= userService.findByUserid(userid);
      try {
		this.getResponse().getWriter().write(Response.build().setStatus("1").setData(user).toJSON());
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}


/*
   * 获取省份
   */
	@Action("findProvince")
	public   void findProvince(){
		this.getResponse().setCharacterEncoding("utf-8");
	List<City> list=	cityService.findPrivers();
	System.out.println(list+"呵呵呵");
		try {
			this.getResponse().getWriter().write(Response.build().setStatus("1").setData(list).toJSON());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

/*
    * 查询所有的银行长信息
    * 
    */
   

@Action("findAllBanks")
  public   void findAllBanks(){
	this.getResponse().setCharacterEncoding("utf-8");
	  //调用service进行查询
	List<Bank>  list=bankService.findAll();
	System.out.println(list.size());
	try {
		this.getResponse().getWriter().write(Response.build().setStatus("1").setData(list).toJSON());
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
  }
	@Action("findBankInfoByUsername")
	public  void findBankInfoByUsername(){
		
	String  username=	this.getRequest().getParameter("username");
	// 获取到用户的id
	  String  token=this.getRequest().getHeader("token");
	  Map<String, Object>  map=  baseCacheService.getHmap(token);
	  int   userid=(Integer)map.get("id");
	  
	  //根据当前用户的id获取到到对应的银行哈信息、
	 BankCardInfo  bc= bankCardService.finyByUserId(userid);
	 System.out.println(bc+"aaaaaaaaaaaaaaaaaa");
	 if(bc== null){
		try {
			this.getResponse().getWriter().write(Response.build().setStatus(FrontStatusConstants.BREAK_DOWN).toJSON());
		  return ;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 }
		System.out.println("用户当前下的所有银行信息");
		try {
			this.getResponse().getWriter().write(Response.build().setStatus(FrontStatusConstants.SUCCESS).setData(bc).toJSON());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	 
	}
	public BankCardInfo getModel() {
		
		return bankCardInfo;
	}
}
