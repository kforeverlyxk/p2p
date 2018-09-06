package com.offcn.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.offcn.dao.ProductEaringRateDao;
import com.offcn.domain.product.ProductEarningRate;
import com.offcn.service.ProductEarningRateService;
import com.offcn.service.ProductService;
@Service
public class ProductEaringRateServiceImp  implements ProductEarningRateService{

	@Autowired
private  ProductEaringRateDao  productEaringRateDao;
	public List<ProductEarningRate> findbyRateProid(int proid) {
		// TODO Auto-generated method stub
		return productEaringRateDao.findByproductId(proid);
//		
	}
	
	

}
