package com.offcn.service.imp;



import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.offcn.dao.IAccountLogDao;
import com.offcn.dao.IFundingNotMatchedDao;
import com.offcn.dao.IProductAccountDao;
import com.offcn.dao.UserAccountDao;
import com.offcn.domain.UserAccountModel;
import com.offcn.domain.accountLog.AccountLog;
import com.offcn.domain.product.ProductAccount;
import com.offcn.domain.productAcount.FundingNotMatchedModel;
import com.offcn.service.IProductAccountService;


@Transactional
@Service
public class ProductAccountService implements IProductAccountService{

	private Logger logger = Logger.getLogger(ProductAccountService.class);

	@Autowired
	private IProductAccountDao productAccountDao;
	
	@Autowired
	private UserAccountDao  accountDao;
	
	@Autowired
	private IAccountLogDao accountLogDao;
	
	@Autowired
	private IFundingNotMatchedDao matchDao;
	
	
	@Transactional
	public int addProductAccount(ProductAccount pa, UserAccountModel ua,
			AccountLog al, FundingNotMatchedModel fnm) {

		int i = accountDao.updateNewInvestmentUserAccount(ua.getInverstmentW(),
				ua.getBalance(), ua.getRecyclingInterest(),
				ua.getAddCapitalTotal(), ua.getCapitalTotal(), ua.getUserId());
		if (i == 0) {
			return 0;
		}
		productAccountDao.save(pa);
		int pId = pa.getpId();
		al.setpId(pId);
		fnm.setfInvestRecordId(pId); // 投资记录ID
		accountLogDao.save(al);
		matchDao.save(fnm);
		return 1;
	}


	public List<ProductAccount> getall() {
		// TODO Auto-generated method stub
		return productAccountDao.findAll();
	}

}
