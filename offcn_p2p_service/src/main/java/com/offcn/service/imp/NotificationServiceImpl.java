package com.offcn.service.imp;


import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.offcn.dao.UserDao;
import com.offcn.domain.UserModel;
import com.offcn.service.INotificationService;



@Service
public class NotificationServiceImpl implements INotificationService {

	public void sendAllTypeOfMessages(Integer userId, String zc) {
		// TODO Auto-generated method stub
		
	}

	public void sendMessage(String userId, String operationType, String messageblob) {
		// TODO Auto-generated method stub
		
	}
//	private static Logger logger = Logger.getLogger(NotificationServiceImpl.class);
//
//	@Autowired
//	private UserDao userDao;
//
//
//	@Autowired
//	private IUserMessageService messageService;
//
//	@Autowired
//	private SMSMessageServcie sMSMessageServcie;
//	
//	@Autowired
//	private EmailMessageService emailMessageService;
//
//	@Override
//	public void sendAllTypeOfMessages(Integer userId, String messagesContent) {
//		// 查询用户信息
//		UserModel user = userDao.findOne(userId);
//		// 判断用户是否存在
//		if (null == user)
//			throw new RuntimeException("没有此用户");
//
//		
//
//		// 发送短信消息
//		if (1 == user.getPhoneStatus()) {
//			if (StringUtils.isNotBlank(user.getPhone())) {
//				sMSMessageServcie.sendSMS(user.getPhone(), messagesContent);
//			
//			}
//		}
//
//		// 发送站内信
//		messageService.insertMessages(String.valueOf(userId), messagesContent);
//		logger.debug("成功将短信信息，邮件消息压入消息队列，等待发送；成功储存站内信到数据库");
//
//	}
//
//	@Override
//	public void sendMessage(String userId, String operationType, String messageblob) {
//		String email = null;
//		String phone = null;
//		//1.通过用户id 查询 用户的手机号 ,邮箱
//		UserModel user = userDao.findOne(Integer.parseInt(userId));
//		if(null != user){
//			email = user.getEmail();
//			phone = user.getPhone();
//		}
//		messageService.insertMessages(userId, messageblob);
//		emailMessageService.sendMail("邮件通知", email, messageblob);
//		sMSMessageServcie.sendSMS(phone, messageblob);
//	}

}
