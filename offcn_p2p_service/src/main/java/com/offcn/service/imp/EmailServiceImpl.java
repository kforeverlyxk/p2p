package com.offcn.service.imp;
import java.net.InetAddress;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.offcn.service.EmailService;



@Service
public class EmailServiceImpl   implements  EmailService{

	@Autowired
	private  JavaMailSender javaMailSender;
	
	public void sendEmail(String emil, String title, String content)  {
		//1.创建一个邮件信息
		MimeMessage  mm=javaMailSender.createMimeMessage();
		//设置信息
		MimeMultipart mp=new MimeMultipart();
		 
		MimeBodyPart mbp1= new MimeBodyPart(); 
		String htmlText = "<b> This is formatted</b>"+
		"<font color=\"red\"  size =\"5\" face=\"arial\" >This paragraph is in Arial, size 5</font>";
		try {
			mbp1.setContent(content,"text/html;charset=utf-8");
			mp.addBodyPart(mbp1);
			mm.setContent(mp);
			
			mm.setFrom(new  InternetAddress("ujava004@163.com"));
			mm.setSubject(title);
			   mm.addRecipient(Message.RecipientType.TO, new InternetAddress(emil));
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		 MimeMessageHelper  her=new MimeMessageHelper(mm);
//		 try {
//			her.setFrom("ujava004@163.com");//设置发送方
//			her.setSubject(title);
//			her.setText(content);
//			her.setTo(emil);
//		} catch (javax.mail.MessagingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
		 javaMailSender.send(mm);
		
	}


}
