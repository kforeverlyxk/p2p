package com.offcn.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.offcn.dao.BankCardInfoDao;
import com.offcn.domain.BankCardInfo;
import com.offcn.service.BankCardService;
@Transactional
@Service
public class BankCardServiceImp   implements  BankCardService{

	@Autowired
	private  BankCardInfoDao bankCardInfoDao;
	public BankCardInfo finyByUserId(int userId) {
		// TODO Auto-generated method stub
		return bankCardInfoDao.findByUserId(userId);
	}
	public void addBankCard(BankCardInfo bankCardInfo) {
		     bankCardInfoDao.save(bankCardInfo);
		
	}
	

}
