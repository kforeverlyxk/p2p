package com.offcn.service;

import java.sql.DriverManager;

import com.offcn.domain.UserAccountModel;
import com.offcn.domain.UserModel;


public interface UserService {

	public  UserModel findByUsername(String  username);
	public UserModel findByPhone(String  phone);
	public boolean add(UserModel user);
	public UserModel  login(String  username,String password);
	public UserModel findByUserid(int userid);
	public void updatephome(String phone, int id);
	
	public  void  updateemila(String  emial,int id);
	public void updateemilaStatic(int parseInt);
	public void updateReanme(String rname, String identity, int id);
}
