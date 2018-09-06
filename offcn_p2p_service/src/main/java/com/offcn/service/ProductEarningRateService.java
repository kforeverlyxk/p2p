package com.offcn.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.offcn.domain.product.ProductEarningRate;


public interface ProductEarningRateService {

	public List<ProductEarningRate> findbyRateProid(int proid);
}
