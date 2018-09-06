package com.offcn.action.verifcat;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.offcn.action.common.BaseAction;
import com.offcn.cache.BaseCacheService;
import com.offcn.service.EmailService;
import com.offcn.service.UserService;
import com.offcn.utils.EmailUtils;
import com.offcn.utils.Response;
import com.offcn.utils.SecretUtil;
import com.offcn.utils.SmsBase;
@Controller
@Namespace("/verification")
public class VerificateAction extends  BaseAction {
	@Autowired
	private  UserService  userService;
	@Autowired
	private BaseCacheService baseCacheService;
	@Autowired
	private EmailService  emailService;
	/*
	 * 认证用户名
	 */
	   @Action("verifiRealName")
	   public  void  verifiRealName(){
		   
		// 从缓冲中获取到用户的id缓冲区
		String  token=	this.getRequest().getHeader("token");
	    Map<String, Object> map= baseCacheService.getHmap(token);
	    int id=(Integer)map.get("id");
	  
	    // 模拟认证
	String  rname=	this.getRequest().getParameter("realName");
	String identity=this.getRequest().getParameter("identity");
	//用来存储所有人的身份证信息   
	Map<String,String> map1=new  HashMap<String, String>();
	map1.put("120123456789012312", "刘猛");
    map1.put("120123456789012313", "张三");
    map1.put("120123456789012314", "网二");
    map1.put("120123456789012315", "码子");
    map1.put("120123456789012316", "赵六");
    map1.put("120123456789012317", "田七");
    if(!map1.containsKey(identity)){
    	
    	try {
			this.getResponse().getWriter().write(Response.build().setStatus("1111").toJSON());
		 return;
    	} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    
    }
    for(String  co:map1.keySet()){
    	if(co.equals(identity) &&rname.equals(map1.get(co)) ){
    		try {
    			
    			//修改数据库的内容   真实名称  第二个身份证号  认证状态
    			userService.updateReanme(rname, identity, id);
				this.getResponse().getWriter().write(Response.build().setStatus("1").toJSON());
			  return;
    		
    		} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		
    	}
    }
    
    try {
		this.getResponse().getWriter().write(Response.build().setStatus("0").toJSON());
	  return;
    
    } catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    
	   }
	
	
	/*
	 * 激活邮箱
	 *emailactivation
	 */
	@Action("/emailactivation")
	  public  void  emailactivation(){
		
		this.getResponse().setCharacterEncoding("utf-8");
		  //获取到id
		String  us=this.getRequest().getParameter("us");//加密数据
		//解密
	try {
		String userid=	SecretUtil.decode(us);
		//根据id修改用户当中的邮箱以及邮箱的状态
		userService.updateemilaStatic(Integer.parseInt(userid));
		this.getResponse().getWriter().write("邮箱验证成功");
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  }
	/*
	 * 发送邮件
	 */
	@Action("auth")
	public  void auth(){
		/*
		 * 获取收到输入的内容
		 */
		String  userid=this.getRequest().getParameter("userId");
		String  username=this.getRequest().getParameter("username");
		String  email=this.getRequest().getParameter("email");
		try{
		String enc=SecretUtil.encrypt(userid);
		String content=EmailUtils.getMailCapacity(email, enc, username);
		//连接+用户id  连接（点击激活）
		emailService.sendEmail(email, "金融验证", content);
		userService.updateemila(email,Integer.parseInt(userid));
		}catch(Exception e){
			e.printStackTrace();
		}
		try {
			this.getResponse().getWriter().write(Response.build().setStatus("1").toJSON());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*
	 * 接受验证码
	 */
	@Action("validateSMS")
	public  void  validateSMS(){
		
		//获取输入的数据
		 String  phone=   this.getRequest().getParameter("phone");
		 //验证码 输入
		 String phoneCode=this.getRequest().getParameter("code");
		String code= baseCacheService.get(phone);//刚刚发送的
		
		
		System.out.println(code+"    "+phoneCode);
		if(!code.equals(phoneCode)){
			try {
				this.getResponse().getWriter().write(Response.build().setStatus("27").toJSON());
			 return  ;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	  try {
		this.getResponse().getWriter().write(Response.build().setStatus("1").toJSON());
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}	
		
	}
	
	/*
	 * 发送验证码
	 */
	@Action("sendMessage")
	  public void sendMessage(){
	String  phone=	this.getRequest().getParameter("phone");
	//吉信通
	String  num=RandomStringUtils.randomNumeric(6);// 随机产生六位的
	try {
		//发送短信
		SmsBase.SendSms(phone, "你的验证码是:"+num);
		//存储到redis当中
		System.out.println("产生的验证码撒：  "+num);
		baseCacheService.set(phone, num);
		baseCacheService.expire(phone, 5*60);
		this.getResponse().getWriter().write(Response.build().setStatus("1").toJSON());
		 return ;
	} catch (UnsupportedEncodingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}

}
