package com.offcn.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.offcn.dao.CreditorDao;
import com.offcn.domain.creditor.CreditorModel;
import com.offcn.service.CreditorService;
@Service
@Transactional
public class CreditorServiceImp  implements CreditorService{

	@Autowired
	CreditorDao  creditorDao;
	public void addCreditor(CreditorModel creditorModel) {
		creditorDao.save(creditorModel);
		
	}
	

}
