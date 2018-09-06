package com.offcn.service;

import com.offcn.domain.UserAccountModel;
import com.offcn.domain.product.ProductEarningRate;

public interface AccoutService {

	public  UserAccountModel findByUserid(int userid);
	public void  updatemoeny(double total,double banabce,int userid);
	
	public ProductEarningRate selectYearInterest(int pid, int mounth);
}
