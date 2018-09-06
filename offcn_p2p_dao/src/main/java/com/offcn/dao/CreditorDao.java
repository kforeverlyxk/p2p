package com.offcn.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.offcn.domain.creditor.CreditorModel;

public interface CreditorDao  extends  JpaRepository<CreditorModel, Integer>{

}
