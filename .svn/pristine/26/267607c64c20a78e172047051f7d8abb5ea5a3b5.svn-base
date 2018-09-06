package com.offcn.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.offcn.domain.matchManagement.WeigthRule;

public interface IWeigthRuleDao extends JpaRepository<WeigthRule, Integer> {
	
	@Query(value=" select rule from WeigthRule rule where rule.weigthType=?1 ")
	public WeigthRule getRuleByType(int type);
}
