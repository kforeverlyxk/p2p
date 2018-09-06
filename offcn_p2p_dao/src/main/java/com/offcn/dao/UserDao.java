package com.offcn.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.offcn.domain.UserModel;

public interface UserDao  extends  JpaRepository<UserModel, Integer>{
	@Query("select a from UserModel a where a.username=?1")
	public 	UserModel findByUsername(String username);
	
	
  @Query("select a from UserModel a where a.phone=?1")
	public UserModel findByPhone(String phone);

	@Query("select a from UserModel a where a.username=?1 and a.password=?2")
	public UserModel login(String username, String password);
	@Query("select a from UserModel a where a.id=?1")
	public UserModel findByUserid(int userid);
	@Modifying
	@Query("update UserModel a set a.phone=?1,a.phoneStatus=1 where a.id=?2")
	public void updateophome(String phone, int id);
	@Modifying
	@Query("update UserModel a set a.email=?1 where a.id=?2")
	public void updateemial(String emial, int id);
	@Modifying
	@Query("update UserModel a set a.emailStatus=1 where a.id=?1")
	public void updateemialStatici(int id);
	@Modifying
	@Query("update UserModel a set a.realName=?1,a.identity=?2,a.realNameStatus=1 where a.id=?3")
	public void updateReanme(String rname, String identity, int id);
	

}
