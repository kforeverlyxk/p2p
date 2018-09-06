package com.offcn.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.offcn.dao.UserDao;
import com.offcn.domain.UserModel;
import com.offcn.service.UserService;
@Transactional
@Service
public class UserServiceImp   implements UserService{
   
	@Autowired
	private UserDao userDao;
	
	public UserModel findByUsername(String username) {
		return userDao.findByUsername(username);
	}

	public UserModel findByPhone(String phone) {
		
		return userDao.findByPhone(phone);
	}

	public boolean add(UserModel user) {
		  UserModel  usermodel=  userDao.save(user);
		return   usermodel!=null ;
	}

	public UserModel login(String username, String password) {
		
		return userDao.login(username,password);
	}

	public UserModel findByUserid(int userid) {
		
		return userDao.findByUserid(userid);
	}

	public void updatephome(String phone, int id) {
		userDao.updateophome(phone, id);
		
	}

	public void updateemila(String emial, int id) {
		    userDao.updateemial(emial,id);
		
	}

	public void updateemilaStatic(int parseInt) {
		 userDao.updateemialStatici(parseInt);
		
	}

	public void updateReanme(String rname, String identity, int id) {
		   userDao.updateReanme(rname,identity,id);
		
	}

	
	
	
}
