package com.offcn.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.offcn.domain.BankCardInfo;

public interface BankCardInfoDao  extends  JpaRepository<BankCardInfo,Integer> {

	BankCardInfo findByUserId(int userId);

}
