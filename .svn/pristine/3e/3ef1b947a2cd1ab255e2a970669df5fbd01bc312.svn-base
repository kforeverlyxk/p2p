package com.offcn.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.offcn.dao.ProductEaringRateDao;
import com.offcn.dao.UserAccountDao;
import com.offcn.domain.UserAccountModel;
import com.offcn.domain.product.ProductEarningRate;
import com.offcn.service.UserAccountService;

@Service
@Transactional
public class UserAccountServiceImo   implements  UserAccountService{
  @Autowired
  private  UserAccountDao  userAccountDao;
  @Autowired
	private ProductEaringRateDao  productEaringRateDao;
	
	public void add(int userid) {
		UserAccountModel  ac=new UserAccountModel();
		 ac.setUserId(userid);
		 userAccountDao.save(ac);
		
	}

	
	public ProductEarningRate selectYearInterest(int pid, int mounth) {
		return productEaringRateDao.getEarningRateByPIdAndMonth(pid, mounth);
	}


	public void setMoneybyuserid(int uid, double money) {
		userAccountDao.updateBanacleByUserId(uid,money);
		
	}


	public UserAccountModel findbyUserId(int userid) {
		
		return userAccountDao.findByuser(userid);
	}

	

}
