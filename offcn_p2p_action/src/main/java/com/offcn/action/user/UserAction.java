package com.offcn.action.user;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.offcn.action.common.BaseAction;
import com.offcn.cache.BaseCacheService;
import com.offcn.domain.UserModel;
import com.offcn.service.AccoutService;
import com.offcn.service.UserAccountService;
import com.offcn.service.UserService;
import com.offcn.utils.CommomUtil;
import com.offcn.utils.FrontStatusConstants;
import com.offcn.utils.ImageUtil;
import com.offcn.utils.MD5Util;
import com.offcn.utils.Response;
import com.opensymphony.xwork2.ModelDriven;

@Controller
@Namespace("/user")
public class UserAction extends BaseAction implements ModelDriven<UserModel> {

	@Autowired
	private UserAccountService userAccountService;
	@Autowired
	private BaseCacheService baseCacheService;
	@Autowired
	private UserService userService;
	private UserModel userModel = new UserModel();
	@Autowired
	private AccoutService accoutService;
	/*
	 * 绑定或者是修改手机
	 */

	@Action("addPhone")
	public void addPhone() {
		// 获取输入的数据
		String phone = this.getRequest().getParameter("phone");
		// 验证码 输入
		String phoneCode = this.getRequest().getParameter("phoneCode");
		String code = baseCacheService.get(phone);// 刚刚发送的
		if (code.equals("") || code == null) {
			try {
				this.getResponse().getWriter().write(Response.build().setStatus("26").toJSON());

				return;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		System.out.println(code + "    " + phoneCode);
		if (!code.equals(phoneCode)) {
			try {
				this.getResponse().getWriter().write(Response.build().setStatus("27").toJSON());
				return;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		// 获取到用户信息
		String token = this.getRequest().getHeader("token");
		Map<String, Object> map = baseCacheService.getHmap(token);
		int id = (Integer) map.get("id");

		// 直接修改数据库当中的数据
		userService.updatephome(phone, id);
		try {
			this.getResponse().getWriter().write(Response.build().setStatus("1").toJSON());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/*
	 * 获取到用户的状态信息 展现在认证页面上
	 */
	@Action("userSecureDetailed")
	public void userSecureDetailed() {
		// 得到tokent
		String token = this.getRequest().getHeader("token");

		// 从rediss当中获取数据
		Map<String, Object> map = baseCacheService.getHmap(token);
		int userid = (Integer) map.get("id");
		// 在根据用户的id获取到对应的对象
		UserModel uu = userService.findByUserid(userid);
		Map<String, Object> o = new HashMap<String, Object>();
		o.put("phoneStatus", uu.getPhoneStatus());
		o.put("realNameStatus", uu.getRealNameStatus());
		o.put("payPwdStatus", uu.getPayPwdStatus());
		o.put("emailStatus", uu.getEmailStatus());
		o.put("username", uu.getUsername());
		o.put("phone", uu.getPhone());
		o.put("passwordstatus", uu.getPassword());
		List<Object> list = new ArrayList<Object>();
		list.add(o);
		try {
			this.getResponse().getWriter().write(Response.build().setStatus("1").setData(list).toJSON());
		} catch (IOException e) {
			
			e.printStackTrace();
		}

	}

	/*
	 * 获取用户的安全等级
	 */
	@Action("userSecure")
	public void userSecure() {
		// 得到tokent
		String token = this.getRequest().getHeader("token");

		// 从rediss当中获取数据
		Map<String, Object> map = baseCacheService.getHmap(token);
		int userid = (Integer) map.get("id");
		// 在根据用户的id获取到对应的对象
		UserModel uu = userService.findByUserid(userid);
		Map<String, Object> o = new HashMap<String, Object>();
		o.put("phoneStatus", uu.getPhoneStatus());
		o.put("realNameStatus", uu.getRealNameStatus());
		o.put("payPwdStatus", uu.getPayPwdStatus());
		o.put("emailStatus", uu.getEmailStatus());
		o.put("username", uu.getUsername());
		List<Object> list = new ArrayList<Object>();
		list.add(o);
		try {
			this.getResponse().getWriter().write(Response.build().setStatus("1").setData(list).toJSON());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*
	 * 退出的操作
	 */
	@Action("logout")
	public void logout() {
		// 得到token
		String token = this.getRequest().getHeader("token");
		// 从redisz当中删除数据
		Map<String, Object> map = baseCacheService.getHmap(token);
		if (map == null || map.size() == 0) {
			try {
				this.getResponse().getWriter()
						.write(Response.build().setStatus(FrontStatusConstants.NULL_TOKEN).toJSON());
				return;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		baseCacheService.del(token);
		try {
			this.getResponse().getWriter().write(Response.build().setStatus(FrontStatusConstants.SUCCESS).toJSON());
			return;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*
	 * 登录的过程
	 * 
	 */
	@Action("login")
	public void signIn() {
		// 获取到user对象
		// 获取到验证码
		String uuid = this.getRequest().getParameter("signUuid");
		String signCode = this.getRequest().getParameter("signCode");

		// 验证验证码
		String sinuuid = baseCacheService.get(uuid);
		if (!signCode.equalsIgnoreCase(sinuuid)) {

			// 相应验证码不争取
			try {
				this.getResponse().getWriter()
						.write(Response.build().setStatus(FrontStatusConstants.INPUT_ERROR_OF_VALIDATE_CARD).toJSON());
				return;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		String pwd = MD5Util.md5(userModel.getUsername() + userModel.getPassword().toLowerCase());

		String username = userModel.getUsername();
		if (CommomUtil.isMobile(username)) {
			UserModel um = userService.findByPhone(username);
			username = um.getUsername();
		}

		System.out.println(username);
		UserModel um = userService.login(username, pwd);
		if (um != null) {
			// 可以登录成功
			String tokend = generateUserToken(um);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("userName", um.getUsername());
			map.put("id", um.getId());
			try {
				baseCacheService.set("ids", Integer.toString(um.getId()));

				this.getResponse().getWriter().write(Response.build().setStatus(FrontStatusConstants.SUCCESS)
						.setData(map).setToken(tokend).toJSON());
				return;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {

			try {
				this.getResponse().getWriter()
						.write(Response.build().setStatus(FrontStatusConstants.ERROR_OF_USERNAME_PASSWORD).toJSON());
				return;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/*
	 * 注册
	 */
	@Action("signup")
	public void signup() {
		// 获取到数据啦
		//用户获取到
		// 加密一下用户名 用户名和密码一起进行密码小写 MD5
		String passowd = MD5Util.md5(userModel.getUsername() + userModel.getPassword().toLowerCase());
		// 吧获取到的数据写入到数据库当中 调用service
		userModel.setPassword(passowd);
		;
		boolean bn = userService.add(userModel);
		if (bn) {
			// 开账户
			// 添加一个账户信息 根据当前用户信息的id
			userAccountService.add(userModel.getId());
			// 吧usermodel转成哈希值 存储到redis当中

			String tolent = generateUserToken(userModel);

			Map<String, Object> map = new HashMap<String, Object>();

			map.put("id", userModel.getId());
			try {
				this.getResponse().getWriter().write(Response.build().setStatus(FrontStatusConstants.SUCCESS)
						.setData(map).setToken(tolent).toJSON());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	// 校验验证码
	@Action("codeValidate")
	public void codeValidate() {
		// 获取到数据
		String uuid = this.getRequest().getParameter("signUuid");
		// 从redis获取获取到对应的验证码
		String uuidcode = baseCacheService.get(uuid);
		// 输入
		String code = this.getRequest().getParameter("signCode");
		System.out.println(uuidcode + " " + code);

		try {
			if (uuidcode.equalsIgnoreCase(code)) {
				System.out.println("hhehe");
				this.getResponse().getWriter().write(Response.build().setStatus(FrontStatusConstants.SUCCESS).toJSON());
				return;
			} else {

				this.getResponse().getWriter()
						.write(Response.build().setStatus(FrontStatusConstants.INPUT_ERROR_OF_VALIDATE_CARD).toJSON());
				return;
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}

	}

	@Action("validatePhone")
	public void validatePhone() {
		// String phone= this.getRequest().getParameter("phone") ;
		// UserModel user=userService.findByPhone(phone);
		try {
			// if (user == null) {

			this.getResponse().getWriter().write(Response.build().setStatus(FrontStatusConstants.SUCCESS).toJSON());
			return;
			// } else {
			// this.getResponse().getWriter().write(Response.build().setStatus(FrontStatusConstants.SUCCESS).toJSON());
			// return;
			// }
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	// 验证用户名
	@Action("validateUserName")
	public void validateUserName() {
		String username = this.getRequest().getParameter("username");
		UserModel user = userService.findByUsername(username);

		try {
			if (user == null) {

				this.getResponse().getWriter().write(Response.build().setStatus(FrontStatusConstants.SUCCESS).toJSON());
				return;
			} else {
				this.getResponse().getWriter().write(Response.build().setStatus("70").toJSON());
				return;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Action("uuid")
	public void uuid() {
		String uuid = UUID.randomUUID().toString();
		// 把产生的uuid存储到reids当中
		baseCacheService.set(uuid, uuid);

		baseCacheService.expire(uuid, 10 * 60);
		try {
			this.getResponse().getWriter().write(Response.build().
					setStatus("1").setUuid(uuid).toJSON());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Action("validateCode")
	public void validateCode() {
		// 获取到请求是过来的uuid
		String tokenUuid = this.getRequest().getParameter("tokenUuid");
		// 获取到redis当中的uuid
		String uuid = baseCacheService.get(tokenUuid);

		if (StringUtils.isEmpty(uuid)) {
			try {
				this.getResponse().getWriter().write(Response.build().setStatus("-999").toJSON());
				return;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		// 随机产生图片式验证码
		String str = ImageUtil.getRundomStr();// 产生验证码数据
		baseCacheService.del(uuid);
		baseCacheService.set(uuid, str);
		baseCacheService.expire(uuid, 10 * 60);

		try {
			ImageUtil.getImage(str, this.getResponse().getOutputStream());// 把产生的验证码图片写出到浏览器上
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public UserModel getModel() {
		// TODO Auto-generated method stub
		return userModel;
	}

	
	
	
	public String generateUserToken(UserModel userModel) {

		try {

			String token = UUID.randomUUID().toString();
			;
			// 将用户信息存储到map中。
			Map<String, Object> tokenMap = new HashMap<String, Object>();
			tokenMap.put("id", userModel.getId());
			tokenMap.put("userName", userModel.getUsername());
			tokenMap.put("phone", userModel.getPhone());
			tokenMap.put("userType", userModel.getUserType());
			tokenMap.put("payPwdStatus", userModel.getPayPwdStatus());
			tokenMap.put("emailStatus", userModel.getEmailStatus());
			tokenMap.put("realName", userModel.getRealName());
			tokenMap.put("identity", userModel.getIdentity());
			tokenMap.put("realNameStatus", userModel.getRealNameStatus());
			tokenMap.put("payPhoneStatus", userModel.getPhoneStatus());

			baseCacheService.del(token);
			baseCacheService.setHmap(token, tokenMap); // 将信息存储到redis中
			baseCacheService.expire(token, 30 * 60);

			return token;
		} catch (Exception e) {
			e.printStackTrace();

			return Response.build().setStatus("-9999").toJSON();
		}
	}

}
