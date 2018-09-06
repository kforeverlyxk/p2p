package com.offcn.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.offcn.dao.IWeigthRuleDao;
import com.offcn.domain.matchManagement.WeigthRule;
import com.offcn.service.IWeigthRuleService;

@Service
public class WeightRuleService implements IWeigthRuleService{

	@Autowired
	private IWeigthRuleDao ruleDao;
		
	
	public WeigthRule getRuleByType(int type) {
		return ruleDao.getRuleByType(type);
	}
}
