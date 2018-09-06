package com.offcn.dao;

/**
 * 
 **/
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.offcn.domain.AdminBean;

public interface AdminDao extends  JpaRepository<AdminBean, Integer> {
		@Query("select a  from  AdminBean a  where a.username=?1 and a.password=?2")	
		public AdminBean login (String username,String password);
		
	}
