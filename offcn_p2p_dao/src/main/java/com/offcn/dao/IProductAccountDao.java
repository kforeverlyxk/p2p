package com.offcn.dao;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.offcn.domain.product.ProductAccount;



public interface IProductAccountDao  extends JpaRepository<ProductAccount, Integer>, JpaSpecificationExecutor<ProductAccount> {

}
