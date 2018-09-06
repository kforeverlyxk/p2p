package com.offcn.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.offcn.dao.ProductEaringRateDao;
import com.offcn.dao.UserAccountDao;
import com.offcn.domain.UserAccountModel;
import com.offcn.domain.product.ProductEarningRate;
import com.offcn.service.AccoutService;
@Transactional
@Service
public class AccountServiceImp implements AccoutService {
  @Autowired
	private UserAccountDao  userAccountDao;
  @Autowired
  private  ProductEaringRateDao productEaringRateDao;
	public UserAccountModel findByUserid(int userid) {
		// TODO Auto-generated method stub
		return userAccountDao.findByuser(userid);
	}
	public void updatemoeny(double total,double bancle,int userid) {
		userAccountDao.updateuserid(total,bancle,userid);
	}
	
	
	
	public ProductEarningRate selectYearInterest(int pid, int mounth) {
		return productEaringRateDao.getEarningRateByPIdAndMonth(pid, mounth);
	}

}
