package com.offcn.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.offcn.domain.product.Product;

public interface ProductService {

	
	  List<Product> findAll();
      Product  findOne(Long proId);
      void  update(Product  product);
      void delete(long  proid);
      void add(Product  p);
}

