package com.offcn.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.offcn.domain.product.Product;

public interface ProductDao   extends  JpaRepository<Product, Long>{
// @Query("delete from Product a where  a.proId=?1")
//	public  void delete(long  id);
	
}
