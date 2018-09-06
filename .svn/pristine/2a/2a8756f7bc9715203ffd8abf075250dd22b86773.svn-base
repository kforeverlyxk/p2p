package com.offcn.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.offcn.domain.UserAccountModel;

public interface UserAccountDao  extends  JpaRepository<UserAccountModel,Integer> {
@Query("select a from  UserAccountModel a where  a.userId=?1")
	UserAccountModel findByuser(int userid);

@Modifying
@Query("update UserAccountModel a set a.total=?1, a.balance=?2  where a.userId=?3")
void updateuserid(double total,double banlacne,int userid);


@Modifying
@Query(value="update UserAccountModel account set account.inverstmentW = ?1, account.balance = ?2, "
		+ " account.recyclingInterest = ?3, account.addCapitalTotal = ?4, account.capitalTotal = ?5 "
		+ " where account.userId = ?6 and account.balance > ?2 ")
public int updateNewInvestmentUserAccount(double inverstmentW, double balance, double recyclingInterest, double addCapitalTotal, double capitalTotal, int userId);
@Modifying
@Query("update UserAccountModel u set u.balance=?2 where u.userId=?1")
void updateBanacleByUserId(int uid, double money);


}
