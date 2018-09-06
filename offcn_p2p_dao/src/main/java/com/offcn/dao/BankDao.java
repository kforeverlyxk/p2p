package com.offcn.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.offcn.domain.Bank;

public interface   BankDao   extends  JpaRepository<Bank, Integer>{

}
