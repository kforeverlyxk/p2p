package com.offcn.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.offcn.dao.BankDao;
import com.offcn.domain.Bank;
import com.offcn.service.BankService;
@Service
@Transactional
public class BankServiceImp  implements  BankService{
  @Autowired
  private BankDao   bankDao;
	public List<Bank> findAll() {
		
		return bankDao.findAll();
	}

}
