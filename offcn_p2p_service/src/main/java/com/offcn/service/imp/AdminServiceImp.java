package com.offcn.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.offcn.dao.AdminDao;
import com.offcn.domain.AdminBean;
import com.offcn.service.AdminService;

@Service
public class AdminServiceImp implements AdminService {
     @Autowired
	private AdminDao  adminDao;
	public AdminBean login(String username, String password) {
		
		return adminDao.login(username, password);
	}

}
