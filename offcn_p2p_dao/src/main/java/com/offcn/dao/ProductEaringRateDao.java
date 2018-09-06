package com.offcn.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.offcn.domain.product.ProductEarningRate;

public interface ProductEaringRateDao  extends  JpaRepository<ProductEarningRate, Integer>{

	//根据产品的id
	@Query("select a from ProductEarningRate  a where a.productId=?1")
	 public List<ProductEarningRate> findByproductId(int productId);
	 @Modifying
	 @Query("delete from ProductEarningRate pro where pro.productId=?1" )
	 public  void delete(int productId);
	 
	 @Query("select per from ProductEarningRate per where per.productId=?1 and per.month=?2")
	public ProductEarningRate getEarningRateByPIdAndMonth(int pid, int mounth);

   
}
